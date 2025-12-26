package com.example.demo.service;

import com.example.demo.model.PolicyRule;
import java.util.List;

public interface PolicyRuleService {
    PolicyRule createRule(PolicyRule rule);
    PolicyRule getRuleById(Long id);
    List<PolicyRule> getAllRules();
    List<PolicyRule> getActiveRules();
}