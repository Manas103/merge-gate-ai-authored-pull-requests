import type { HoldoutSummary, PrSummary, Summary } from "./types";

async function getJson<T>(path: string): Promise<T> {
  const res = await fetch(path);
  if (!res.ok) {
    throw new Error(`${path} responded ${res.status}`);
  }
  return (await res.json()) as T;
}

export function fetchSummary(): Promise<Summary> {
  return getJson<Summary>("/api/summary");
}

export function fetchPrs(): Promise<PrSummary[]> {
  return getJson<PrSummary[]>("/api/prs");
}

export function fetchHoldout(): Promise<HoldoutSummary> {
  return getJson<HoldoutSummary>("/api/holdout");
}

/** Pure, independent of any network call: what the console shows in the block-rate
 * badge next to a PR list. Extracted so it can be unit-tested without fetch/DOM. */
export function blockRateLabel(flaggedCount: number, branchCount: number): string {
  if (branchCount === 0) {
    return "no tracked branches";
  }
  const pct = Math.round((flaggedCount / branchCount) * 1000) / 10;
  return `${flaggedCount} / ${branchCount} branches flagged (${pct}%)`;
}
