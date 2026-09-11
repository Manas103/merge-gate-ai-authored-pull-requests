import { render, screen, waitFor } from "@testing-library/react";
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import App from "./App";

describe("App", () => {
  beforeEach(() => {
    vi.stubGlobal(
      "fetch",
      vi.fn(async (input: string) => {
        if (input === "/api/summary") {
          return jsonResponse({
            totalPrs: 2, prsBySource: { CLAUDE_CODE: 1, SYNTHETIC_CURSOR: 1 },
            totalBranches: 5, coveredBranches: 4, flaggedBranches: 1, blockedPrs: 1,
          });
        }
        if (input === "/api/prs") {
          return jsonResponse([
            { prId: "pr0001", className: "Foo", source: "SYNTHETIC_CURSOR", description: "d",
              branchCount: 2, flaggedCount: 0, blocked: false },
            { prId: "pr0231", className: "ClaudeTask0231", source: "CLAUDE_CODE", description: "d2",
              branchCount: 3, flaggedCount: 1, blocked: true },
          ]);
        }
        if (input === "/api/holdout") {
          return jsonResponse({ total: 60, falseBlocks: 0, missedBlocks: 2, rows: [] });
        }
        if (input === "/api/runbook-coverage") {
          return jsonResponse({
            totalAlerts: 2, documentedAlerts: 1, undocumentedAlerts: 1,
            gaps: [{
              alertId: "ALERT-001", service: "payments-api", severity: "CRITICAL",
              description: "payment gateway timeout rate exceeds threshold", documented: false,
              reason: "no documented runbook step",
            }],
            alerts: [],
          });
        }
        throw new Error("unexpected fetch " + input);
      }),
    );
  });

  afterEach(() => {
    vi.unstubAllGlobals();
  });

  it("renders the corpus summary and the PR table once data loads", async () => {
    render(<App />);
    await waitFor(() => expect(screen.getByText(/2 PRs, 5 tracked branches/)).toBeInTheDocument());
    expect(screen.getByText("pr0231")).toBeInTheDocument();
    expect(screen.getByText("BLOCKED")).toBeInTheDocument();
    expect(screen.getByText(/60 hand-labeled PRs/)).toBeInTheDocument();
  });

  it("names the specific missing runbook step for a flagged alert, not just BLOCKED", async () => {
    render(<App />);
    await waitFor(() => expect(screen.getByText(/1 \/ 2 alerts have a documented runbook step/)).toBeInTheDocument());
    expect(screen.getByText(/ALERT-001 \(payments-api, CRITICAL\)/)).toBeInTheDocument();
    expect(screen.getByText(/payment gateway timeout rate exceeds threshold/)).toBeInTheDocument();
  });

  it("shows an error message when a fetch fails", async () => {
    vi.stubGlobal("fetch", vi.fn(async () => { throw new Error("network down"); }));
    render(<App />);
    await waitFor(() => expect(screen.getByRole("alert")).toHaveTextContent("network down"));
  });
});

function jsonResponse(body: unknown): Response {
  return {
    ok: true,
    status: 200,
    json: async () => body,
  } as Response;
}
