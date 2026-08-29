package com.manas.mergegate.api;

import com.manas.mergegate.domain.Branch;
import com.manas.mergegate.domain.BranchRepository;
import com.manas.mergegate.domain.PrUnit;
import com.manas.mergegate.domain.PrUnitRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prs")
public class PrController {

    private final PrUnitRepository prUnitRepository;
    private final BranchRepository branchRepository;

    public PrController(PrUnitRepository prUnitRepository, BranchRepository branchRepository) {
        this.prUnitRepository = prUnitRepository;
        this.branchRepository = branchRepository;
    }

    public record PrSummaryDto(String prId, String className, String source, String description,
                                int branchCount, int flaggedCount, boolean blocked) {
    }

    @GetMapping
    public List<PrSummaryDto> list() {
        return prUnitRepository.findAll().stream()
                .map(this::toSummary)
                .sorted((a, b) -> a.prId().compareTo(b.prId()))
                .toList();
    }

    @GetMapping("/{prId}")
    public Map<String, Object> detail(@PathVariable String prId) {
        PrUnit pr = prUnitRepository.findById(prId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "no such PR: " + prId));
        List<Branch> branches = branchRepository.findByPrId(prId);
        boolean blocked = branches.stream().anyMatch(Branch::isFlagged);
        return Map.of(
                "prId", pr.getPrId(),
                "className", pr.getClassName(),
                "packageName", pr.getPackageName(),
                "source", pr.getSource(),
                "description", pr.getDescription(),
                "blocked", blocked,
                "branches", branches
        );
    }

    private PrSummaryDto toSummary(PrUnit pr) {
        List<Branch> branches = branchRepository.findByPrId(pr.getPrId());
        long flagged = branches.stream().filter(Branch::isFlagged).count();
        return new PrSummaryDto(pr.getPrId(), pr.getClassName(), pr.getSource(), pr.getDescription(),
                branches.size(), (int) flagged, flagged > 0);
    }
}
