package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class IssuedDeviceRecordServiceImpl implements IssuedDeviceRecordService {

    private final IssuedDeviceRecordRepository issuedDeviceRecordRepository;
    private final EmployeeProfileRepository employeeProfileRepository;
    private final DeviceCatalogItemRepository deviceCatalogItemRepository;

    public IssuedDeviceRecordServiceImpl(IssuedDeviceRecordRepository issuedDeviceRecordRepository,
                                         EmployeeProfileRepository employeeProfileRepository,
                                         DeviceCatalogItemRepository deviceCatalogItemRepository) {
        this.issuedDeviceRecordRepository = issuedDeviceRecordRepository;
        this.employeeProfileRepository = employeeProfileRepository;
        this.deviceCatalogItemRepository = deviceCatalogItemRepository;
    }

    @Override
    public IssuedDeviceRecord issueDevice(IssuedDeviceRecord record) {
        if (!employeeProfileRepository.existsById(record.getEmployee().getId())) {
            throw new ResourceNotFoundException("Employee not found");
        }
        if (!deviceCatalogItemRepository.existsById(record.getDeviceItem().getId())) {
            throw new ResourceNotFoundException("Device not found");
        }
        record.setIssuedDate(LocalDate.now());
        record.setStatus("ISSUED");
        return issuedDeviceRecordRepository.save(record);
    }

    @Override
    public IssuedDeviceRecord returnDevice(Long recordId) {
        IssuedDeviceRecord record = issuedDeviceRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
        
        if ("RETURNED".equals(record.getStatus())) {
            throw new BadRequestException("Device already returned");
        }
        
        record.setReturnedDate(LocalDate.now());
        record.setStatus("RETURNED");
        return issuedDeviceRecordRepository.save(record);
    }

    @Override
    public List<IssuedDeviceRecord> getIssuedDevicesByEmployee(Long employeeId) {
        if (employeeId == null) {
            return issuedDeviceRecordRepository.findAll();
        }
        return issuedDeviceRecordRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<IssuedDeviceRecord> getAllIssuedDevices() {
        return issuedDeviceRecordRepository.findAll();
    }

    @Override
    public IssuedDeviceRecord getIssuedDeviceById(Long id) {
        return issuedDeviceRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Issued device record not found"));
    }
}
