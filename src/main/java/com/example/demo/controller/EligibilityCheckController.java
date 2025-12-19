package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EligibilityCheckRecord;
import com.example.demo.repository.EligibilityCheckRecordRepository;
import com.example.demo.service.EligibilityCheckService;

@RestController
@RequestMapping("/api/eligibility")
public class EligibilityCheckController {

    private final EligibilityCheckService eligibilityCheckService;
    private final EligibilityCheckRecordRepository eligibilityCheckRecordRepository;

    public EligibilityCheckController(EligibilityCheckService eligibilityCheckService,
                                     EligibilityCheckRecordRepository eligibilityCheckRecordRepository) {
        this.eligibilityCheckService = eligibilityCheckService;
        this.eligibilityCheckRecordRepository = eligibilityCheckRecordRepository;
    }

    @PostMapping("/check")
    public ResponseEntity<EligibilityCheckRecord> checkEligibility(@RequestParam String employeeId, 
                                                                  @RequestParam Long deviceItemId) {
        EligibilityCheckRecord result = eligibilityCheckService.validateEligibility(employeeId, deviceItemId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EligibilityCheckRecord> getEligibilityHistory(@PathVariable Long employeeId) {
        return eligibilityCheckRecordRepository.findByEmployeeId(employeeId);
    }

    @GetMapping
    public List<EligibilityCheckRecord> getAllEligibilityChecks() {
        return eligibilityCheckRecordRepository.findAll();
    }
}