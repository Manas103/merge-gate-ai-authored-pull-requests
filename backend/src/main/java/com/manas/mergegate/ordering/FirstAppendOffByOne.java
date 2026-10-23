package com.manas.mergegate.ordering;

import java.util.ArrayList;
import java.util.List;

/** The counter starts at 1 instead of 0, so every offset is shifted by one from the very first
 * call. Included as a contrast case: the existing three-record unit test catches this
 * immediately, so it should not count among patches the new oracle alone catches. */
public final class FirstAppendOffByOne implements AppendLog {
    private final List<String> records = new ArrayList<>();
    private long nextOffset = 1;

    @Override
    public synchronized long append(String record) {
        records.add(record);
        return nextOffset++;
    }

    @Override
    public synchronized String read(long offset) {
        int idx = (int) offset - 1;
        if (idx < 0 || idx >= records.size()) return null;
        return records.get(idx);
    }
}
