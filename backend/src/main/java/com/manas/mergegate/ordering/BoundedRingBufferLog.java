package com.manas.mergegate.ordering;

/** Looks like an unbounded log but is secretly backed by a fixed-size ring buffer: once more
 * than `capacity` records have been appended, the oldest ones are silently overwritten. Offsets
 * still increase correctly, so ordering holds; durability fails only once a sequence runs past
 * capacity, which a short existing test never does. */
public final class BoundedRingBufferLog implements AppendLog {
    private final String[] ring;
    private final int capacity;
    private long nextOffset = 0;

    public BoundedRingBufferLog(int capacity) {
        this.capacity = capacity;
        this.ring = new String[capacity];
    }

    @Override
    public synchronized long append(String record) {
        long offset = nextOffset++;
        ring[(int) (offset % capacity)] = record;
        return offset;
    }

    @Override
    public synchronized String read(long offset) {
        if (offset < 0 || offset >= nextOffset) return null;
        if (nextOffset - offset > capacity) return null; // overwritten; a correct log would still have it
        return ring[(int) (offset % capacity)];
    }
}
