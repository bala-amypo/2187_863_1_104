package com.example.demo.service;

import com.example.demo.model.EligibilityCheckRecord;

public interface EligibilityCheckService {
    EligibilityCheckRecord validateEligibility(String employeeId, Long deviceItemId);
}