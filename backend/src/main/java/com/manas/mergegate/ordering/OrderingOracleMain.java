package com.manas.mergegate.ordering;

import java.util.List;

/** Prints the full ordering-oracle report; the holdout/mutation-gate reports have their own
 * Main entry points, this one is this feature's equivalent. */
public final class OrderingOracleMain {
    public static void main(String[] args) {
        List<OrderingOracleReport.Row> rows = OrderingOracleReport.run(42);
        int existingPassed = 0, oracleCaught = 0, both = 0;
        for (OrderingOracleReport.Row r : rows) {
            if (r.existingTestPassed()) existingPassed++;
            if (r.oracleCaughtIt()) oracleCaught++;
            if (r.existingTestPassed() && r.oracleCaughtIt()) both++;
            System.out.printf("%-32s %-24s existingPassed=%-5s oracleCaught=%-5s %s%n",
                    r.name(), r.bugKind(), r.existingTestPassed(), r.oracleCaughtIt(),
                    r.failureReason() == null ? "" : r.failureReason());
        }
        System.out.println();
        System.out.println("total patches: " + rows.size());
        System.out.println("passed existing unit test: " + existingPassed);
        System.out.println("caught by ordering/durability oracle: " + oracleCaught);
        System.out.println("passed existing unit test AND caught by oracle: " + both);
    }
}
