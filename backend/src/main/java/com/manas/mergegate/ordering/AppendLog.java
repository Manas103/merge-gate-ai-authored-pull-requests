package com.manas.mergegate.ordering;

/**
 * The two invariants a partitioned log store promises (see projects/partitioned-log-raft-metadata):
 * append() hands back strictly increasing offsets starting at 0 with no gaps or repeats, and
 * anything appended is durably readable back at that offset for as long as the log exists. This
 * interface and its patch corpus exist to give the mutation-and-invariant gate a second kind of
 * defect to find beyond PIT's line/branch mutants: a patch can be fully branch-covered and still
 * violate an invariant that no single branch assertion states.
 */
public interface AppendLog {
    long append(String record);

    String read(long offset);
}
