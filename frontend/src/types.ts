export interface PrSummary {
  prId: string;
  className: string;
  source: string;
  description: string;
  branchCount: number;
  flaggedCount: number;
  blocked: boolean;
}

export interface Summary {
  totalPrs: number;
  prsBySource: Record<string, number>;
  totalBranches: number;
  coveredBranches: number;
  flaggedBranches: number;
  blockedPrs: number;
}

export interface HoldoutRow {
  prId: string;
  source: string;
  labelMethod: string;
  expectedBlock: boolean;
  actualBlock: boolean;
  falseBlock: boolean;
  missedBlock: boolean;
}

export interface HoldoutSummary {
  total: number;
  falseBlocks: number;
  missedBlocks: number;
  rows: HoldoutRow[];
}
