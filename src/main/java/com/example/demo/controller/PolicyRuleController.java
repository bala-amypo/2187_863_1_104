package com.example.demo.controller;

import com.example.demo.model.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyRuleController {
    
    @Autowired
    private PolicyRuleService policyService;
    
    @PostMapping
    public ResponseEntity<PolicyRule> createPolicy(@RequestBody PolicyRule rule) {
        return ResponseEntity.ok(policyService.createRule(rule));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PolicyRule> getPolicyById(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getRuleById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<PolicyRule>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllRules());
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<PolicyRule>> getActivePolicies() {
        return ResponseEntity.ok(policyService.getActiveRules());
    }
}