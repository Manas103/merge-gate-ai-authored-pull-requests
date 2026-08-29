import { useEffect, useState } from "react";
import { fetchHoldout, fetchPrs, fetchSummary } from "./api";
import { blockRateLabel } from "./api";
import type { HoldoutSummary, PrSummary, Summary } from "./types";

/**
 * A read-only console over the merge gate's own REST API. It shows, per PR, whether the
 * gate would block the merge and why, the corpus-wide flagged-branch rate, and the
 * hand-labeled holdout's false/missed block counts. It writes nothing back; every number
 * shown here is the same JSON the gate persisted after a real PIT run (see
 * seed.CorpusDataLoader), not recomputed client-side.
 */
export default function App() {
  const [summary, setSummary] = useState<Summary | null>(null);
  const [prs, setPrs] = useState<PrSummary[]>([]);
  const [holdout, setHoldout] = useState<HoldoutSummary | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    Promise.all([fetchSummary(), fetchPrs(), fetchHoldout()])
      .then(([s, p, h]) => {
        setSummary(s);
        setPrs(p);
        setHoldout(h);
      })
      .catch((e: Error) => setError(e.message));
  }, []);

  if (error) {
    return <p role="alert">Failed to load: {error}</p>;
  }

  return (
    <main>
      <h1>Merge Gate for AI-Authored Pull Requests</h1>
      {summary && (
        <section aria-label="corpus summary">
          <p>
            {summary.totalPrs} PRs, {summary.totalBranches} tracked branches,{" "}
            {summary.flaggedBranches} flagged, {summary.blockedPrs} PRs blocked.
          </p>
        </section>
      )}
      {holdout && (
        <section aria-label="holdout results">
          <p>
            Holdout: {holdout.total} hand-labeled PRs, {holdout.falseBlocks} false blocks,{" "}
            {holdout.missedBlocks} missed blocks.
          </p>
        </section>
      )}
      <table aria-label="pull requests">
        <thead>
          <tr>
            <th>PR</th>
            <th>Source</th>
            <th>Description</th>
            <th>Branches</th>
            <th>Decision</th>
          </tr>
        </thead>
        <tbody>
          {prs.map((pr) => (
            <tr key={pr.prId}>
              <td>{pr.prId}</td>
              <td>{pr.source}</td>
              <td>{pr.description}</td>
              <td>{blockRateLabel(pr.flaggedCount, pr.branchCount)}</td>
              <td>{pr.blocked ? "BLOCKED" : "clear"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </main>
  );
}
