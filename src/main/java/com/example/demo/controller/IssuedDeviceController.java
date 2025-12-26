package com.example.demo.controller;

import com.example.demo.model.IssuedDeviceRecord;
import com.example.demo.service.IssuedDeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/issued-devices")
public class IssuedDeviceController {
    
    @Autowired
    private IssuedDeviceRecordService issuedService;
    
    @PostMapping
    public ResponseEntity<IssuedDeviceRecord> issueDevice(@RequestBody IssuedDeviceRecord record) {
        return ResponseEntity.ok(issuedService.issueDevice(record));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<IssuedDeviceRecord> getIssuedDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(issuedService.getIssuedDeviceById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<IssuedDeviceRecord>> getAllIssuedDevices() {
        return ResponseEntity.ok(issuedService.getAllIssuedDevices());
    }
    
    @PutMapping("/{id}/return")
    public ResponseEntity<IssuedDeviceRecord> returnDevice(@PathVariable Long id) {
        return ResponseEntity.ok(issuedService.returnDevice(id));
    }
}