package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PolicyRule;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.PolicyRuleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyRuleServiceImpl implements PolicyRuleService {

    private final PolicyRuleRepository policyRuleRepository;

    public PolicyRuleServiceImpl(PolicyRuleRepository policyRuleRepository) {
        this.policyRuleRepository = policyRuleRepository;
    }

    @Override
    public PolicyRule createRule(PolicyRule rule) {
        if (policyRuleRepository.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new BadRequestException("Rule code already exists");
        }
        return policyRuleRepository.save(rule);
    }

    @Override
    public List<PolicyRule> getAllRules() {
        return policyRuleRepository.findAll();
    }

    @Override
    public List<PolicyRule> getActiveRules() {
        return policyRuleRepository.findByActiveTrue();
    }

    @Override
    public PolicyRule updateRule(Long id, PolicyRule rule) {
        PolicyRule existingRule = policyRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy rule not found"));
        if (rule.getDescription() != null) existingRule.setDescription(rule.getDescription());
        if (rule.getAppliesToRole() != null) existingRule.setAppliesToRole(rule.getAppliesToRole());
        if (rule.getAppliesToDepartment() != null) existingRule.setAppliesToDepartment(rule.getAppliesToDepartment());
        if (rule.getMaxDevicesAllowed() != null) existingRule.setMaxDevicesAllowed(rule.getMaxDevicesAllowed());
        if (rule.getActive() != null) existingRule.setActive(rule.getActive());
        return policyRuleRepository.save(existingRule);
    }

    @Override
    public PolicyRule getRuleById(Long id) {
        return policyRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy rule not found"));
    }
}
