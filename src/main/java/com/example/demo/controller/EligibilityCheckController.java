package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService eligibilityCheckService;

    public EligibilityCheckController(EligibilityCheckService eligibilityCheckService) {
        this.eligibilityCheckService = eligibilityCheckService;
    }

    @PostMapping("/check")
    public ResponseEntity<EligibilityCheckRecord> checkEligibility(@RequestParam Long employeeId, 
                                                                  @RequestParam Long deviceItemId) {
        try {
            EligibilityCheckRecord result = eligibilityCheckService.validateEligibility(employeeId, deviceItemId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    public List<EligibilityCheckRecord> getEligibilityHistory(@PathVariable Long employeeId) {
        return eligibilityCheckService.getChecksByEmployee(employeeId);
    }

    @GetMapping
    public List<EligibilityCheckRecord> getAllEligibilityChecks() {
        return eligibilityCheckService.getChecksByEmployee(null);
    }
}