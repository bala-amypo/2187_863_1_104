package com.example.demo.controller;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.service.EligibilityCheckService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/eligibility")
@Tag(name = "Eligibility Check Endpoints")
public class EligibilityCheckController {

    private final EligibilityCheckService eligibilityCheckService;

    public EligibilityCheckController(EligibilityCheckService eligibilityCheckService) {
        this.eligibilityCheckService = eligibilityCheckService;
    }

    @PostMapping("/validate/{employeeId}/{deviceItemId}")
    @Operation(summary = "Validate eligibility")
    public ResponseEntity<EligibilityCheckRecord> validateEligibility(@PathVariable Long employeeId, 
                                                                     @PathVariable Long deviceItemId) {
        try {
            EligibilityCheckRecord result = eligibilityCheckService.validateEligibility(employeeId, deviceItemId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get eligibility checks by employee")
    public List<EligibilityCheckRecord> getEligibilityHistory(@PathVariable Long employeeId) {
        return eligibilityCheckService.getChecksByEmployee(employeeId);
    }

    @GetMapping("/{checkId}")
    @Operation(summary = "Get eligibility check by ID")
    public ResponseEntity<EligibilityCheckRecord> getEligibilityCheck(@PathVariable Long checkId) {
        try {
            List<EligibilityCheckRecord> allChecks = eligibilityCheckService.getChecksByEmployee(null);
            EligibilityCheckRecord check = allChecks.stream()
                    .filter(c -> c.getId().equals(checkId))
                    .findFirst()
                    .orElse(null);
            return check != null ? ResponseEntity.ok(check) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}