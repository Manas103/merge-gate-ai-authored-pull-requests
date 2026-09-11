package com.manas.mergegate.api;

import com.manas.mergegate.domain.AlertCoverage;
import com.manas.mergegate.domain.AlertCoverageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/** Serves the release gate's runbook-coverage rule: for every alert a
 * service can raise, whether a documented runbook step exists. The
 * "gaps" list names the specific alerts a release would be blocked for. */
@RestController
@RequestMapping("/api/runbook-coverage")
public class RunbookCoverageController {

    private final AlertCoverageRepository alertCoverageRepository;

    public RunbookCoverageController(AlertCoverageRepository alertCoverageRepository) {
        this.alertCoverageRepository = alertCoverageRepository;
    }

    @GetMapping
    public Map<String, Object> coverage() {
        List<AlertCoverage> alerts = alertCoverageRepository.findAll();
        List<AlertCoverage> gaps = alerts.stream().filter(a -> !a.isDocumented()).toList();
        return Map.of(
                "totalAlerts", alerts.size(),
                "documentedAlerts", alerts.size() - gaps.size(),
                "undocumentedAlerts", gaps.size(),
                "gaps", gaps,
                "alerts", alerts
        );
    }
}
