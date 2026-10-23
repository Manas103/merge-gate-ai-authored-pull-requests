package com.manas.mergegate.ordering;

import java.util.ArrayList;
import java.util.List;

/** The obviously-correct implementation the property oracle is really checking patches against. */
public final class ReferenceAppendLog implements AppendLog {
    private final List<String> records = new ArrayList<>();

    @Override
    public synchronized long append(String record) {
        records.add(record);
        return records.size() - 1;
    }

    @Override
    public synchronized String read(long offset) {
        if (offset < 0 || offset >= records.size()) return null;
        return records.get((int) offset);
    }
}
