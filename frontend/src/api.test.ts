import { describe, expect, it } from "vitest";
import { blockRateLabel } from "./api";

describe("blockRateLabel", () => {
  it("reports no tracked branches when there are none", () => {
    expect(blockRateLabel(0, 0)).toBe("no tracked branches");
  });

  it("formats a clean PR with zero flagged branches", () => {
    expect(blockRateLabel(0, 4)).toBe("0 / 4 branches flagged (0%)");
  });

  it("formats a flagged PR with a rounded percentage", () => {
    expect(blockRateLabel(1, 3)).toBe("1 / 3 branches flagged (33.3%)");
  });

  it("formats every branch flagged", () => {
    expect(blockRateLabel(2, 2)).toBe("2 / 2 branches flagged (100%)");
  });
});
