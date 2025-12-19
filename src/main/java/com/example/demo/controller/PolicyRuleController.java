package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PolicyRule;
import com.example.demo.repository.PolicyRuleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/policy-rules")
public class PolicyRuleController {
    
    private final PolicyRuleRepository policyRuleRepository;
    
    public PolicyRuleController(PolicyRuleRepository policyRuleRepository) {
        this.policyRuleRepository = policyRuleRepository;
    }
    
    @GetMapping
    public List<PolicyRule> getAllPolicyRules() {
        return policyRuleRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PolicyRule> getPolicyRuleById(@PathVariable Long id) {
        return policyRuleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public PolicyRule createPolicyRule(@RequestBody PolicyRule policyRule) {
        if (policyRuleRepository.findByRuleCode(policyRule.getRuleCode()).isPresent()) {
            throw new BadRequestException("Rule code already exists");
        }
        return policyRuleRepository.save(policyRule);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PolicyRule> updatePolicyRule(@PathVariable Long id, @RequestBody PolicyRule policyRule) {
        return policyRuleRepository.findById(id)
                .map(existingRule -> {
                    existingRule.setDescription(policyRule.getDescription());
                    existingRule.setAppliesToRole(policyRule.getAppliesToRole());
                    existingRule.setAppliesToDepartment(policyRule.getAppliesToDepartment());
                    existingRule.setMaxDevicesAllowed(policyRule.getMaxDevicesAllowed());
                    existingRule.setActive(policyRule.getActive());
                    return ResponseEntity.ok(policyRuleRepository.save(existingRule));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePolicyRule(@PathVariable Long id) {
        return policyRuleRepository.findById(id)
                .map(rule -> {
                    policyRuleRepository.delete(rule);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
