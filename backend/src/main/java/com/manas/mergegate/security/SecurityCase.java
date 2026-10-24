package com.manas.mergegate.security;

/** One case from security_corpus.json: a synthetic diff snippet or dependency name, hand-labeled
 * with whether the rule named by ruleType should fire on it. Independent of the PR/mutation
 * corpus, mirroring how runbook.AlertDef models the release gate's second rule. */
public record SecurityCase(String id, String ruleType, String snippet, boolean expectedFlag, String label) {
}
