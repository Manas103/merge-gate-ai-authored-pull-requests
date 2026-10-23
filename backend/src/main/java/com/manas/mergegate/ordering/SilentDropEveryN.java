package com.manas.mergegate.ordering;

import java.util.ArrayList;
import java.util.List;

/** Every Nth record is never actually stored, but append() still returns an incrementing-looking
 * offset for it, as if a write path "optimized away" a write under some condition it misjudged.
 * Ordering looks fine; durability silently fails for those offsets. */
public final class SilentDropEveryN implements AppendLog {
    private final List<String> records = new ArrayList<>();
    private final int period;
    private long nextOffset = 0;
    private int count = 0;

    public SilentDropEveryN(int period) {
        this.period = period;
    }

    @Override
    public synchronized long append(String record) {
        count++;
        long offset = nextOffset++;
        if (count % period != 0) {
            while (records.size() <= offset) records.add(null);
            records.set((int) offset, record);
        }
        return offset;
    }

    @Override
    public synchronized String read(long offset) {
        if (offset < 0 || offset >= records.size()) return null;
        return records.get((int) offset);
    }
}
