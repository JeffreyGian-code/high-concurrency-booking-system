package seatshield.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seatshield.dto.RushResult;
import seatshield.service.RushSimulationService;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private final RushSimulationService rushSimulationService;

    public DemoController(
            RushSimulationService rushSimulationService) {

        this.rushSimulationService = rushSimulationService;
    }

    @PostMapping("/simulate-rush/{seatId}")
    public ResponseEntity<RushResult> simulateRush(
            @PathVariable Long seatId) {

        RushResult result =
                rushSimulationService.simulateRush(seatId);

        return ResponseEntity.ok(result);
    }
}