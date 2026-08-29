package com.manas.mergegate.gate;

/** One tracked branch from branch_manifest.json: a specific line inside a
 * specific PR unit's implementation class that the corpus generator marked
 * as one AI-authored decision branch. */
public record BranchDef(
        String prId,
        String branchId,
        String className,
        String fqcn,
        int lineNumber,
        String description,
        String constructionQuality) {
}
