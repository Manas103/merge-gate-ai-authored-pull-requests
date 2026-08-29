package com.manas.mergegate.api;

import com.manas.mergegate.domain.HoldoutResult;
import com.manas.mergegate.domain.HoldoutResultRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/holdout")
public class HoldoutController {

    private final HoldoutResultRepository holdoutResultRepository;

    public HoldoutController(HoldoutResultRepository holdoutResultRepository) {
        this.holdoutResultRepository = holdoutResultRepository;
    }

    @GetMapping
    public Map<String, Object> holdout() {
        List<HoldoutResult> rows = holdoutResultRepository.findAll();
        long falseBlocks = rows.stream().filter(HoldoutResult::isFalseBlock).count();
        long missedBlocks = rows.stream().filter(HoldoutResult::isMissedBlock).count();
        return Map.of(
                "total", rows.size(),
                "falseBlocks", falseBlocks,
                "missedBlocks", missedBlocks,
                "rows", rows
        );
    }
}
