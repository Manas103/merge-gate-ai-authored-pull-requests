package com.manas.mergegate.ordering;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * A themed corpus of append-log patches, modeled on the kind of bug an AI agent plausibly
 * introduces while editing the log store's append path: most are correct on a short, three-record
 * happy-path check (the existing unit suite's level of coverage) and wrong only over a longer
 * sequence, under concurrency, or periodically, which is exactly the gap this oracle is for. A
 * few are wrong immediately and get caught by the existing test too, as a sanity check that the
 * corpus is not rigged to make the new oracle look good.
 */
public final class PatchCorpus {

    public record Patch(String name, String bugKind, Supplier<AppendLog> factory) {
    }

    /** The existing unit suite's level of scrutiny: three known records, checked by value. */
    public static boolean existingUnitTestPasses(AppendLog log) {
        long o0 = log.append("a");
        long o1 = log.append("b");
        long o2 = log.append("c");
        return o0 == 0 && o1 == 1 && o2 == 2 && "b".equals(log.read(1)) && "a".equals(log.read(0));
    }

    public static List<Patch> all() {
        List<Patch> patches = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            patches.add(new Patch("correct-" + i, "none", ReferenceAppendLog::new));
        }
        for (int period : new int[]{5, 7, 11, 17, 23}) {
            patches.add(new Patch("off-by-one-return-every-" + period, "periodic-offset-skip",
                    () -> new OffByOneReturnEveryN(period)));
        }
        for (int period : new int[]{4, 6, 9, 13, 19}) {
            patches.add(new Patch("silent-drop-every-" + period, "periodic-durability-loss",
                    () -> new SilentDropEveryN(period)));
        }
        for (int sleepMicros : new int[]{1, 2, 3, 5, 8}) {
            patches.add(new Patch("racey-counter-" + sleepMicros, "unsynchronized-counter",
                    () -> new RaceyCounterLog(sleepMicros)));
        }
        for (int capacity : new int[]{15, 20, 30, 50, 80}) {
            patches.add(new Patch("ring-buffer-overwrite-" + capacity, "bounded-overwrite",
                    () -> new BoundedRingBufferLog(capacity)));
        }
        for (int i = 0; i < 5; i++) {
            patches.add(new Patch("first-append-off-by-one-" + i, "immediate-offset-bug",
                    FirstAppendOffByOne::new));
        }
        for (int sleepMs : new int[]{1, 2, 3}) {
            patches.add(new Patch("delayed-write-race-" + sleepMs, "read-then-add-race",
                    () -> new DelayedWriteRaceLog(sleepMs)));
        }
        for (int i = 0; i < 4; i++) {
            patches.add(new Patch("storage-index-off-by-one-" + i, "immediate-storage-bug",
                    OffByOneStorageIndex::new));
        }
        for (int i = 0; i < 3; i++) {
            patches.add(new Patch("lost-update-counter-" + i, "lost-update-race",
                    LostUpdateCounterLog::new));
        }
        return patches;
    }
}
