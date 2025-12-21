package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService issuedDeviceRecordService;

    public IssuedDeviceRecordController(IssuedDeviceRecordService issuedDeviceRecordService) {
        this.issuedDeviceRecordService = issuedDeviceRecordService;
    }

    @GetMapping
    public List<IssuedDeviceRecord> getAllIssuedDevices() {
        return issuedDeviceRecordService.getIssuedDevicesByEmployee(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssuedDeviceRecord> getIssuedDeviceById(@PathVariable Long id) {
        try {
            List<IssuedDeviceRecord> records = issuedDeviceRecordService.getIssuedDevicesByEmployee(null);
            IssuedDeviceRecord record = records.stream()
                    .filter(r -> r.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<IssuedDeviceRecord> createIssuedDevice(@RequestBody IssuedDeviceRecord issuedDevice) {
        try {
            IssuedDeviceRecord created = issuedDeviceRecordService.issueDevice(issuedDevice);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        try {
            IssuedDeviceRecord returned = issuedDeviceRecordService.returnDevice(id);
            return ResponseEntity.ok(returned);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    public List<IssuedDeviceRecord> getDevicesByEmployee(@PathVariable Long employeeId) {
        return issuedDeviceRecordService.getIssuedDevicesByEmployee(employeeId);
    }
}