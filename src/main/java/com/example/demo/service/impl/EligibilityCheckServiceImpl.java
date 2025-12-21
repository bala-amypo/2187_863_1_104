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
        EmployeeProfile employee = employeeProfileRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        if (!employee.getActive()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord(employee, null, false, "Employee is not active");
            return eligibilityCheckRecordRepository.save(record);
        }

        DeviceCatalogItem device = deviceCatalogItemRepository.findById(deviceItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));

        if (!device.getActive()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, false, "Device is inactive");
            return eligibilityCheckRecordRepository.save(record);
        }

        List<IssuedDeviceRecord> activeIssuances = issuedDeviceRecordRepository
                .findActiveByEmployeeAndDevice(employeeId, deviceItemId);
        if (!activeIssuances.isEmpty()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, false, 
                "Employee already has an active issuance for this device");
            return eligibilityCheckRecordRepository.save(record);
        }

        Long activeDeviceCount = issuedDeviceRecordRepository.countActiveDevicesForEmployee(employeeId);

        if (activeDeviceCount >= device.getMaxAllowedPerEmployee()) {
            EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, false, 
                "Maximum allowed devices per employee exceeded");
            return eligibilityCheckRecordRepository.save(record);
        }

        List<PolicyRule> activeRules = policyRuleRepository.findByActiveTrue();
        for (PolicyRule rule : activeRules) {
            boolean ruleApplies = false;
            
            if (rule.getAppliesToRole() != null && rule.getAppliesToRole().equals(employee.getJobRole())) {
                ruleApplies = true;
            }
            
            if (rule.getAppliesToDepartment() != null && rule.getAppliesToDepartment().equals(employee.getDepartment())) {
                ruleApplies = true;
            }
            
            if (ruleApplies && activeDeviceCount >= rule.getMaxDevicesAllowed()) {
                EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, false, 
                    "Policy violation: " + rule.getDescription());
                return eligibilityCheckRecordRepository.save(record);
            }
        }

        EligibilityCheckRecord record = new EligibilityCheckRecord(employee, device, true, "Eligible for device issuance");
        return eligibilityCheckRecordRepository.save(record);
    }

    @Override
    public List<EligibilityCheckRecord> getChecksByEmployee(Long employeeId) {
        return eligibilityCheckRecordRepository.findByEmployeeId(employeeId);
    }
}