package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService service;

    public IssuedDeviceRecordController(IssuedDeviceRecordService service) {
        this.service = service;
    }

    // 1️⃣ Get all issued devices
    @GetMapping
    public ResponseEntity<List<IssuedDeviceRecord>> getAllIssuedDevices() {
        return ResponseEntity.ok(service.getAllIssuedDevices());
    }

    // 2️⃣ Get issued device by ID
    @GetMapping("/{id}")
    public ResponseEntity<IssuedDeviceRecord> getIssuedDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getIssuedDeviceById(id));
    }

    // 3️⃣ Issue a device
    @PostMapping
    public ResponseEntity<IssuedDeviceRecord> issueDevice(
            @RequestBody IssuedDeviceRecord issuedDevice) {

        IssuedDeviceRecord created = service.issueDevice(issuedDevice);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // 4️⃣ Return a device
    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        return ResponseEntity.ok(service.returnDevice(id));
    }

    // 5️⃣ Get devices by employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<IssuedDeviceRecord>> getDevicesByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(service.getIssuedDevicesByEmployee(employeeId));
    }
}
