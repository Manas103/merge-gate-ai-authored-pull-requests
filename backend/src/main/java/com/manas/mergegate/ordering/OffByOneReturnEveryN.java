package com.manas.mergegate.ordering;

import java.util.ArrayList;
import java.util.List;

/** Stores correctly but every Nth append reports the wrong offset to the caller: an ordering bug
 * that a three-record happy-path test (N greater than 3) will never see. */
public final class OffByOneReturnEveryN implements AppendLog {
    private final List<String> records = new ArrayList<>();
    private final int period;
    private int count = 0;

    public OffByOneReturnEveryN(int period) {
        this.period = period;
    }

    @Override
    public synchronized long append(String record) {
        records.add(record);
        long realOffset = records.size() - 1;
        count++;
        if (count % period == 0) {
            return realOffset + 1; // reports a offset nothing was ever stored at
        }
        return realOffset;
    }

    @Override
    public synchronized String read(long offset) {
        if (offset < 0 || offset >= records.size()) return null;
        return records.get((int) offset);
    }
}
