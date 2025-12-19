package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.repository.IssuedDeviceRecordRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordRepository issuedDeviceRecordRepository;

    public IssuedDeviceRecordController(IssuedDeviceRecordRepository issuedDeviceRecordRepository) {
        this.issuedDeviceRecordRepository = issuedDeviceRecordRepository;
    }

    @GetMapping
    public List<IssuedDeviceRecord> getAllIssuedDevices() {
        return issuedDeviceRecordRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssuedDeviceRecord> getIssuedDeviceById(@PathVariable Long id) {
        return issuedDeviceRecordRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public IssuedDeviceRecord createIssuedDevice(@RequestBody IssuedDeviceRecord issuedDevice) {
        return issuedDeviceRecordRepository.save(issuedDevice);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        return issuedDeviceRecordRepository.findById(id)
                .map(record -> {
                    if ("RETURNED".equals(record.getStatus())) {
                        throw new BadRequestException("Device already returned");
                    }
                    record.setReturnedDate(LocalDate.now());
                    record.setStatus("RETURNED");
                    return ResponseEntity.ok(issuedDeviceRecordRepository.save(record));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/employee/{employeeId}")
    public List<IssuedDeviceRecord> getDevicesByEmployee(@PathVariable Long employeeId) {
        return issuedDeviceRecordRepository.findByEmployeeId(employeeId);
    }
}