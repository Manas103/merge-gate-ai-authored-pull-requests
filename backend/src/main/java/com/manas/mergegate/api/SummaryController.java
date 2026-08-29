package com.manas.mergegate.api;

import com.manas.mergegate.domain.Branch;
import com.manas.mergegate.domain.BranchRepository;
import com.manas.mergegate.domain.PrUnit;
import com.manas.mergegate.domain.PrUnitRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {

    private final PrUnitRepository prUnitRepository;
    private final BranchRepository branchRepository;

    public SummaryController(PrUnitRepository prUnitRepository, BranchRepository branchRepository) {
        this.prUnitRepository = prUnitRepository;
        this.branchRepository = branchRepository;
    }

    @GetMapping
    public Map<String, Object> summary() {
        List<PrUnit> prs = prUnitRepository.findAll();
        List<Branch> branches = branchRepository.findAll();

        Map<String, Long> prsBySource = prs.stream()
                .collect(Collectors.groupingBy(PrUnit::getSource, Collectors.counting()));

        long flaggedBranches = branches.stream().filter(Branch::isFlagged).count();
        long coveredBranches = branches.stream().filter(Branch::isCovered).count();

        Map<String, Boolean> prBlocked = branches.stream()
                .collect(Collectors.groupingBy(Branch::getPrId,
                        Collectors.reducing(false, Branch::isFlagged, Boolean::logicalOr)));
        long blockedPrs = prBlocked.values().stream().filter(Boolean::booleanValue).count();

        return Map.of(
                "totalPrs", prs.size(),
                "prsBySource", prsBySource,
                "totalBranches", branches.size(),
                "coveredBranches", coveredBranches,
                "flaggedBranches", flaggedBranches,
                "blockedPrs", blockedPrs
        );
    }
}
