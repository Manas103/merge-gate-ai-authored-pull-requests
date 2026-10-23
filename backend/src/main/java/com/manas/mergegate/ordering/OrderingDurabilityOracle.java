package com.manas.mergegate.ordering;

import java.util.*;
import java.util.function.Supplier;

/**
 * Property-based check: generate a random sequence of appends against a fresh AppendLog from the
 * patch under test, diffed against what a correct log must satisfy, rather than against
 * hand-picked example values (that is what makes this different from, and complementary to, the
 * existing PIT branch/mutant gate). Two properties, checked after a single-threaded sequence and
 * again under concurrent appenders:
 *   ordering:   the offsets returned, sorted, are exactly 0..N-1 with no gaps or repeats.
 *   durability: read(offset) for every offset returned still returns the exact record appended there.
 */
public final class OrderingDurabilityOracle {

    public record Verdict(boolean orderingHeld, boolean durabilityHeld, String firstFailure) {
        public boolean passed() { return orderingHeld && durabilityHeld; }
    }

    public static Verdict check(Supplier<AppendLog> factory, long seed, int sequentialOps, int concurrentThreads, int opsPerThread) {
        Verdict sequential = checkSequential(factory.get(), seed, sequentialOps);
        if (!sequential.passed()) return sequential;
        return checkConcurrent(factory.get(), concurrentThreads, opsPerThread);
    }

    private static Verdict checkSequential(AppendLog log, long seed, int ops) {
        Random r = new Random(seed);
        List<Long> offsets = new ArrayList<>();
        List<String> records = new ArrayList<>();
        for (int i = 0; i < ops; i++) {
            String record = "rec-" + i + "-" + r.nextInt(1_000_000);
            long offset = log.append(record);
            offsets.add(offset);
            records.add(record);
        }
        List<Long> sorted = new ArrayList<>(offsets);
        Collections.sort(sorted);
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i) != i) {
                return new Verdict(false, true, "ordering: expected offsets to be a permutation of 0.." + (ops - 1)
                        + " but sorted offsets were " + sorted.subList(0, Math.min(10, sorted.size())) + "...");
            }
        }
        for (int i = 0; i < ops; i++) {
            String got = log.read(offsets.get(i));
            if (!Objects.equals(got, records.get(i))) {
                return new Verdict(true, false, "durability: offset " + offsets.get(i) + " should read back \""
                        + records.get(i) + "\" but got \"" + got + "\"");
            }
        }
        return new Verdict(true, true, null);
    }

    private static Verdict checkConcurrent(AppendLog log, int threads, int opsPerThread) {
        List<Thread> workers = new ArrayList<>();
        List<long[]> perThreadOffsets = new ArrayList<>();
        List<String[]> perThreadRecords = new ArrayList<>();
        for (int t = 0; t < threads; t++) perThreadOffsets.add(new long[opsPerThread]);
        for (int t = 0; t < threads; t++) perThreadRecords.add(new String[opsPerThread]);
        for (int t = 0; t < threads; t++) {
            final int threadId = t;
            Thread th = new Thread(() -> {
                for (int i = 0; i < opsPerThread; i++) {
                    String record = "t" + threadId + "-" + i;
                    perThreadOffsets.get(threadId)[i] = log.append(record);
                    perThreadRecords.get(threadId)[i] = record;
                }
            });
            workers.add(th);
        }
        for (Thread th : workers) th.start();
        for (Thread th : workers) {
            try { th.join(10_000); } catch (InterruptedException ignored) { }
        }
        List<Long> allOffsets = new ArrayList<>();
        Map<Long, String> expectedByOffset = new HashMap<>();
        for (int t = 0; t < threads; t++) {
            for (int i = 0; i < opsPerThread; i++) {
                allOffsets.add(perThreadOffsets.get(t)[i]);
                expectedByOffset.put(perThreadOffsets.get(t)[i], perThreadRecords.get(t)[i]);
            }
        }
        int total = threads * opsPerThread;
        List<Long> sorted = new ArrayList<>(allOffsets);
        Collections.sort(sorted);
        Set<Long> distinct = new HashSet<>(allOffsets);
        if (distinct.size() != total || sorted.get(0) != 0 || sorted.get(sorted.size() - 1) != total - 1) {
            return new Verdict(false, true, "ordering under concurrency: " + total + " appends from " + threads
                    + " threads should produce a permutation of 0.." + (total - 1) + " with no duplicates, got "
                    + distinct.size() + " distinct offsets");
        }
        for (Map.Entry<Long, String> e : expectedByOffset.entrySet()) {
            String got = log.read(e.getKey());
            if (!Objects.equals(got, e.getValue())) {
                return new Verdict(true, false, "durability under concurrency: offset " + e.getKey()
                        + " should read back \"" + e.getValue() + "\" but got \"" + got + "\"");
            }
        }
        return new Verdict(true, true, null);
    }
}
