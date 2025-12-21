package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
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
    public EligibilityCheckRecord validateEligibility(String employeeId, Long deviceItemId) {
        // 1. Check employee exists
        EmployeeProfile employee = employeeProfileRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // 2. Check employee active
        if (!employee.getActive()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord(employee, null, false, "Employee is not active");
            return eligibilityCheckRecordRepository.save(record);
        }

        // 3. Check device exists
        DeviceCatalogItem device = deviceCatalogItemRepository.findById(deviceItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));

        // 4. Check device active
        if (!device.getActive()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, false, "Device is inactive");
            return eligibilityCheckRecordRepository.save(record);
        }

        // 5. Check no active issuance exists
        List<IssuedDeviceRecord> activeIssuances = issuedDeviceRecordRepository
                .findActiveByEmployeeAndDevice(employee.getId(), deviceItemId);
        if (!activeIssuances.isEmpty()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, false, 
                "Employee already has an active issuance for this device");
            return eligibilityCheckRecordRepository.save(record);
        }

        // 6. Count active devices per employee
        Long activeDeviceCount = issuedDeviceRecordRepository.countActiveDevicesForEmployee(employee.getId());

        // 7. Enforce device.maxAllowedPerEmployee
        if (activeDeviceCount >= device.getMaxAllowedPerEmployee()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, false, 
                "Maximum allowed devices per employee exceeded");
            return eligibilityCheckRecordRepository.save(record);
        }

        // 8. Apply ALL active PolicyRules
        List<PolicyRule> activeRules = policyRuleRepository.findByActiveTrue();
        for (PolicyRule rule : activeRules) {
            boolean ruleApplies = false;
            
            // Check if rule applies to employee's role
            if (rule.getAppliesToRole() != null && rule.getAppliesToRole().equals(employee.getJobRole())) {
                ruleApplies = true;
            }
            
            // Check if rule applies to employee's department
            if (rule.getAppliesToDepartment() != null && rule.getAppliesToDepartment().equals(employee.getDepartment())) {
                ruleApplies = true;
            }
            
            // If rule applies, check maxDevicesAllowed
            if (ruleApplies && activeDeviceCount >= rule.getMaxDevicesAllowed()) {
                EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, false, 
                    "Policy violation: " + rule.getDescription());
                return eligibilityCheckRecordRepository.save(record);
            }
        }

        // 9. All checks passed - eligible
        EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, true, "Eligible for device issuance");
        return eligibilityCheckRecordRepository.save(record);
    }
}