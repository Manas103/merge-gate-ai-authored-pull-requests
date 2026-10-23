package com.manas.mergegate.ordering;

import java.util.ArrayList;
import java.util.List;

/** Reports the correct, strictly-increasing offset, but stores each record one slot further over
 * than it reports, so read(n) always comes back with what was appended at offset n-1 instead.
 * A second contrast case: the existing test's read(1) check catches this immediately, so it
 * should not count among patches only the new oracle catches. */
public final class OffByOneStorageIndex implements AppendLog {
    private final List<String> records = new ArrayList<>();
    private long nextOffset = 0;

    @Override
    public synchronized long append(String record) {
        long offset = nextOffset++;
        while (records.size() <= offset + 1) records.add(null);
        records.set((int) offset + 1, record); // stored one past where it is reported
        return offset;
    }

    @Override
    public synchronized String read(long offset) {
        if (offset < 0 || offset >= records.size()) return null;
        return records.get((int) offset);
    }
}
