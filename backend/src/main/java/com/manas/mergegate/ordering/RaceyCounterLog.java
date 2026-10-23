package com.manas.mergegate.ordering;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/** Reads the counter, does a little work, then writes counter+1: correct under one writer, but
 * two concurrent appenders can read the same value before either writes it back, handing out the
 * same offset twice. A single-threaded existing test never exercises two writers at once. */
public final class RaceyCounterLog implements AppendLog {
    private final Map<Long, String> records = new ConcurrentHashMap<>();
    private volatile long counter = 0;
    private final int sleepMicros;

    public RaceyCounterLog(int sleepMicros) {
        this.sleepMicros = sleepMicros;
    }

    @Override
    public long append(String record) {
        long offset = counter; // read
        long deadline = System.nanoTime() + sleepMicros * 1000L;
        while (System.nanoTime() < deadline) { /* busy-wait to widen the race window */ }
        records.put(offset, record);
        counter = offset + 1; // write-back, racing with any other thread's read above
        return offset;
    }

    @Override
    public String read(long offset) {
        return records.get(offset);
    }
}
