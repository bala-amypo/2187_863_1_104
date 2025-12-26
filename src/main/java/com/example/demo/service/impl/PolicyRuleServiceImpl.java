package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PolicyRule;
import com.example.demo.repository.PolicyRuleRepository;
import com.example.demo.service.PolicyRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyRuleServiceImpl implements PolicyRuleService {
    
    @Autowired
    private PolicyRuleRepository policyRepo;
    
    public PolicyRuleServiceImpl(PolicyRuleRepository policyRepo) {
        this.policyRepo = policyRepo;
    }
    
    @Override
    public PolicyRule createRule(PolicyRule rule) {
        if (policyRepo.findByRuleCode(rule.getRuleCode()).isPresent()) {
            throw new BadRequestException("Rule code already exists");
        }
        return policyRepo.save(rule);
    }
    
    @Override
    public PolicyRule getRuleById(Long id) {
        return policyRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Policy rule not found with id: " + id));
    }
    
    @Override
    public List<PolicyRule> getAllRules() {
        return policyRepo.findAll();
    }
    
    @Override
    public List<PolicyRule> getActiveRules() {
        return policyRepo.findByActiveTrue();
    }
}