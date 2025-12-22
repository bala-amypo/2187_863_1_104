package com.example.demo.controller;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceRecordController {

    private final IssuedDeviceRecordService service;

    public IssuedDeviceRecordController(IssuedDeviceRecordService service) {
        this.service = service;
    }

    @GetMapping
    public List<IssuedDeviceRecord> getAllIssuedDevices() {
        return service.getAllIssuedDevices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssuedDeviceRecord> getIssuedDeviceById(@PathVariable Long id) {
        try {
            IssuedDeviceRecord record = service.getIssuedDeviceById(id);
            return ResponseEntity.ok(record);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<IssuedDeviceRecord> createIssuedDevice(@RequestBody IssuedDeviceRecord issuedDevice) {
        try {
            IssuedDeviceRecord created = service.issueDevice(issuedDevice);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        try {
            IssuedDeviceRecord returned = service.returnDevice(id);
            return ResponseEntity.ok(returned);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/employee/{employeeId}")
    public List<IssuedDeviceRecord> getDevicesByEmployee(@PathVariable Long employeeId) {
        return service.getIssuedDevicesByEmployee(employeeId);
    }
}
