package com.example.demo.controller;

import com.example.demo.model.PolicyRule;
import com.example.demo.service.PolicyRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/policy-rules")
public class PolicyRuleController {

    private final PolicyRuleService policyRuleService;

    public PolicyRuleController(PolicyRuleService policyRuleService) {
        this.policyRuleService = policyRuleService;
    }

    @GetMapping
    public List<PolicyRule> getAllPolicyRules() {
        return policyRuleService.getAllRules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyRule> getPolicyRuleById(@PathVariable Long id) {
        try {
            PolicyRule rule = policyRuleService.getRuleById(id);
            return ResponseEntity.ok(rule);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PolicyRule> createPolicyRule(@RequestBody PolicyRule policyRule) {
        try {
            PolicyRule created = policyRuleService.createRule(policyRule);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PolicyRule> updatePolicyRule(@PathVariable Long id, @RequestBody PolicyRule policyRule) {
        try {
            PolicyRule updatedRule = policyRuleService.updateRule(id, policyRule);
            return ResponseEntity.ok(updatedRule);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePolicyRule(@PathVariable Long id) {
        try {
            PolicyRule existingRule = policyRuleService.getRuleById(id);
            existingRule.setActive(false);
            policyRuleService.updateRule(id, existingRule);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
