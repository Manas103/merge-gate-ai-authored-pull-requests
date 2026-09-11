package com.manas.mergegate.api;

import com.manas.mergegate.domain.MutationScoreResult;
import com.manas.mergegate.domain.MutationScoreResultRepository;
import com.manas.mergegate.gate.PrMutationScore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/** Serves the release gate's mutation-score-floor rule: the per-PR mutation
 * score rolled up by gate.MutationScoreGate, currently the 9 seeded
 * zero-kill PR units, and which PRs it would block. */
@RestController
@RequestMapping("/api/mutation-scores")
public class MutationScoreController {

    private final MutationScoreResultRepository mutationScoreResultRepository;

    public MutationScoreController(MutationScoreResultRepository mutationScoreResultRepository) {
        this.mutationScoreResultRepository = mutationScoreResultRepository;
    }

    @GetMapping
    public Map<String, Object> scores() {
        List<MutationScoreResult> units = mutationScoreResultRepository.findAll();
        long belowFloor = units.stream().filter(MutationScoreResult::isBelowFloor).count();
        long zeroKill = units.stream().filter(MutationScoreResult::isZeroKill).count();
        return Map.of(
                "floorScore", PrMutationScore.FLOOR_SCORE,
                "totalUnits", units.size(),
                "belowFloorCount", belowFloor,
                "zeroKillCount", zeroKill,
                "units", units
        );
    }
}
