package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PolicyRule;
import com.example.demo.service.PolicyRuleService;

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
            PolicyRule rule = policyRuleService.getAllRules().stream()
                    .filter(r -> r.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            return rule != null ? ResponseEntity.ok(rule) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PolicyRule createPolicyRule(@RequestBody PolicyRule policyRule) {
        return policyRuleService.createRule(policyRule);
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
            PolicyRule existingRule = policyRuleService.getAllRules().stream()
                    .filter(r -> r.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (existingRule != null) {
                existingRule.setActive(false);
                policyRuleService.updateRule(id, existingRule);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}