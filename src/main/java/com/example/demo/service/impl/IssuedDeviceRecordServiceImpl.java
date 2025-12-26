package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {
    
    @Autowired
    private IssuedDeviceRecordRepository issuedRepo;
    
    @Autowired
    private EmployeeProfileRepository employeeRepo;
    
    @Autowired
    private DeviceCatalogItemRepository deviceRepo;
    
    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository issuedRepo,
                                          EmployeeProfileRepository employeeRepo,
                                          DeviceCatalogItemRepository deviceRepo) {
        this.issuedRepo = issuedRepo;
        this.employeeRepo = employeeRepo;
        this.deviceRepo = deviceRepo;
    }
    
    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {
        if (!employeeRepo.existsById(record.getEmployeeId())) {
            throw new BadRequestException("Employee not found");
        }
        if (!deviceRepo.existsById(record.getDeviceItemId())) {
            throw new BadRequestException("Device item not found");
        }
        return issuedRepo.save(record);
    }
    
    @Override
    public IssuedDeviceRecord returnDevice(Long id) {
        IssuedDeviceRecord record = issuedRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Issued device record not found"));
        if ("RETURNED".equals(record.getStatus())) {
            throw new BadRequestException("Device already returned");
        }
        record.setStatus("RETURNED");
        record.setReturnedDate(LocalDate.now());
        return issuedRepo.save(record);
    }
    
    @Override
    public List<IssuedDeviceRecord> getAllIssuedDevices() {
        return issuedRepo.findAll();
    }
    
    @Override
    public IssuedDeviceRecord getIssuedDeviceById(Long id) {
        return issuedRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Issued device record not found"));
    }
}