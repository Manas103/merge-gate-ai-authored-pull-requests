package com.manas.mergegate.ordering;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/** Reads the current size, sleeps (standing in for real work done between deciding an offset and
 * committing it, such as a replication round trip), then stores at that offset: correct with one
 * writer, but a second concurrent writer that reads the same size during the sleep collides with
 * it. The sleep makes the race nearly certain under concurrency and invisible single-threaded. */
public final class DelayedWriteRaceLog implements AppendLog {
    private final Map<Long, String> records = new ConcurrentHashMap<>();
    private volatile long size = 0;
    private final int sleepMs;

    public DelayedWriteRaceLog(int sleepMs) {
        this.sleepMs = sleepMs;
    }

    @Override
    public long append(String record) {
        long offset = size;
        try { Thread.sleep(sleepMs); } catch (InterruptedException ignored) { }
        records.put(offset, record);
        size = offset + 1;
        return offset;
    }

    @Override
    public String read(long offset) {
        return records.get(offset);
    }
}
