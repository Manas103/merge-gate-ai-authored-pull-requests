package com.manas.mergegate.ordering;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/** Synchronizes append() on a fresh lock object created per call instead of a shared one, which
 * compiles fine, looks locked, and provides no mutual exclusion at all: a plausible copy-paste
 * mistake when refactoring a method to "add locking" for a review comment. */
public final class LostUpdateCounterLog implements AppendLog {
    private final Map<Long, String> records = new ConcurrentHashMap<>();
    private volatile long counter = 0;

    @Override
    public long append(String record) {
        Object lock = new Object(); // bug: a new, never-shared lock every call
        synchronized (lock) {
            long offset = counter;
            // stands in for real work done while "holding" the lock; widens the window so two
            // threads reliably read the same counter value before either writes it back, rather
            // than leaving it to how the OS happens to schedule a bare Thread.yield().
            try { Thread.sleep(1); } catch (InterruptedException ignored) { }
            records.put(offset, record);
            counter = offset + 1;
            return offset;
        }
    }

    @Override
    public String read(long offset) {
        return records.get(offset);
    }
}
