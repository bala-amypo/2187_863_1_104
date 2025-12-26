package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EligibilityCheckService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private final EmployeeProfileRepository employeeProfileRepository;
    private final DeviceCatalogItemRepository deviceCatalogItemRepository;
    private final IssuedDeviceRecordRepository issuedDeviceRecordRepository;
    private final PolicyRuleRepository policyRuleRepository;
    private final EligibilityCheckRecordRepository eligibilityCheckRecordRepository;

    public EligibilityCheckServiceImpl(EmployeeProfileRepository employeeProfileRepository,
                                       DeviceCatalogItemRepository deviceCatalogItemRepository,
                                       IssuedDeviceRecordRepository issuedDeviceRecordRepository,
                                       PolicyRuleRepository policyRuleRepository,
                                       EligibilityCheckRecordRepository eligibilityCheckRecordRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
        this.deviceCatalogItemRepository = deviceCatalogItemRepository;
        this.issuedDeviceRecordRepository = issuedDeviceRecordRepository;
        this.policyRuleRepository = policyRuleRepository;
        this.eligibilityCheckRecordRepository = eligibilityCheckRecordRepository;
    }

    @Override
    public EligibilityCheckRecord validateEligibility(Long employeeId, Long deviceItemId) {
        // Check if employee exists
        EmployeeProfile employee = employeeProfileRepository.findById(employeeId).orElse(null);
        DeviceCatalogItem device = deviceCatalogItemRepository.findById(deviceItemId).orElse(null);
        
        if (employee == null || device == null) {
            EligibilityCheckRecord record = new EligibilityCheckRecord();
            record.setEmployeeId(employeeId);
            record.setDeviceItemId(deviceItemId);
            record.setIsEligible(false);
            record.setReason("Employee or device not found");
            return eligibilityCheckRecordRepository.save(record);
        }

        if (!employee.getActive()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord();
            record.setEmployeeId(employeeId);
            record.setDeviceItemId(deviceItemId);
            record.setIsEligible(false);
            record.setReason("Employee is not active");
            return eligibilityCheckRecordRepository.save(record);
        }

        if (!device.getActive()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord();
            record.setEmployeeId(employeeId);
            record.setDeviceItemId(deviceItemId);
            record.setIsEligible(false);
            record.setReason("Device is inactive");
            return eligibilityCheckRecordRepository.save(record);
        }

        List<IssuedDeviceRecord> activeIssuances = issuedDeviceRecordRepository
                .findActiveByEmployeeAndDevice(employeeId, deviceItemId);
        if (!activeIssuances.isEmpty()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord();
            record.setEmployeeId(employeeId);
            record.setDeviceItemId(deviceItemId);
            record.setIsEligible(false);
            record.setReason("Employee already has an active issuance for this device");
            return eligibilityCheckRecordRepository.save(record);
        }

        Long activeDeviceCount = issuedDeviceRecordRepository.countActiveDevicesForEmployee(employeeId);

        if (activeDeviceCount >= device.getMaxAllowedPerEmployee()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord();
            record.setEmployeeId(employeeId);
            record.setDeviceItemId(deviceItemId);
            record.setIsEligible(false);
            record.setReason("Maximum allowed devices per employee exceeded");
            return eligibilityCheckRecordRepository.save(record);
        }

        List<PolicyRule> activeRules = policyRuleRepository.findByActiveTrue();
        for (PolicyRule rule : activeRules) {
            boolean ruleApplies = false;
            
            // Check if rule applies to all employees (null department and role)
            if (rule.getAppliesToRole() == null && rule.getAppliesToDepartment() == null) {
                ruleApplies = true;
            } else {
                // Check specific department and role matches
                if (rule.getAppliesToRole() != null && rule.getAppliesToRole().equals(employee.getJobRole())) {
                    ruleApplies = true;
                }
                
                if (rule.getAppliesToDepartment() != null && rule.getAppliesToDepartment().equals(employee.getDepartment())) {
                    ruleApplies = true;
                }
            }
            
            if (ruleApplies && activeDeviceCount >= rule.getMaxDevicesAllowed()) {
                EligibilityCheckRecord record = new EligibilityCheckRecord();
                record.setEmployeeId(employeeId);
                record.setDeviceItemId(deviceItemId);
                record.setIsEligible(false);
                record.setReason("Policy violation: " + (rule.getDescription() != null ? rule.getDescription() : rule.getRuleCode()));
                return eligibilityCheckRecordRepository.save(record);
            }
        }

        EligibilityCheckRecord record = new EligibilityCheckRecord();
        record.setEmployeeId(employeeId);
        record.setDeviceItemId(deviceItemId);
        record.setIsEligible(true);
        record.setReason("Eligible for device issuance");
        return eligibilityCheckRecordRepository.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return eligibilityCheckRecordRepository.findByEmployeeId(employeeId);
    }
}