package com.example.demo.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.example.demo.model.PolicyRule;
import com.example.demo.service.PolicyRuleService;

@RestController
@RequestMapping("/api/policy-rules")
@Tag(name = "Policy Rules Endpoints")
public class PolicyRuleController {

    private final PolicyRuleService policyRuleService;

    public PolicyRuleController(PolicyRuleService policyRuleService) {
        this.policyRuleService = policyRuleService;
    }

    @GetMapping
    @Operation(summary = "Get all policy rules")
    public List<PolicyRule> getAllPolicyRules() {
        return policyRuleService.getAllRules();
    }

    @GetMapping("/active")
    @Operation(summary = "Get active policy rules")
    public List<PolicyRule> getActivePolicyRules() {
        return policyRuleService.getActiveRules();
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
    @Operation(summary = "Create policy rule")
    public PolicyRule createPolicyRule(@Valid @RequestBody PolicyRule policyRule) {
        return policyRuleService.createRule(policyRule);
    }

    @PutMapping("/{id}/active")
    @Operation(summary = "Update policy rule active status")
    public ResponseEntity<PolicyRule> updatePolicyRuleStatus(@PathVariable Long id, @RequestParam boolean active) {
        try {
            PolicyRule rule = policyRuleService.getAllRules().stream()
                    .filter(r -> r.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            if (rule != null) {
                rule.setActive(active);
                PolicyRule updated = policyRuleService.updateRule(id, rule);
                return ResponseEntity.ok(updated);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete policy rule")
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