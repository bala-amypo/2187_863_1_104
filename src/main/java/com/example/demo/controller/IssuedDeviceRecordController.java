package com.example.demo.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.IssuedDeviceRecord;
import jakarta.validation.Valid;
import com.example.demo.service.IssuedDeviceRecordService;

@RestController
@RequestMapping("/api/issued-devices")
@Tag(name = "Issued Device Records Endpoints")
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
    @Operation(summary = "Get issued device by ID")
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
    @Operation(summary = "Issue device to employee")
    public ResponseEntity<IssuedDeviceRecord> createIssuedDevice(@Valid @RequestBody IssuedDeviceRecord issuedDevice) {
        try {
            IssuedDeviceRecord created = issuedDeviceRecordService.issueDevice(issuedDevice);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/return")
    @Operation(summary = "Return issued device")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        try {
            IssuedDeviceRecord returned = issuedDeviceRecordService.returnDevice(id);
            return ResponseEntity.ok(returned);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get devices by employee")
    public List<IssuedDeviceRecord> getDevicesByEmployee(@PathVariable Long employeeId) {
        return issuedDeviceRecordService.getIssuedDevicesByEmployee(employeeId);
    }
}