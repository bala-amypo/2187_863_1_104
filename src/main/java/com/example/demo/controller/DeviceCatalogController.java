package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    
    @Autowired
    private DeviceCatalogService deviceService;
    
    @PostMapping
    public ResponseEntity<DeviceCatalogItem> createDevice(@RequestBody DeviceCatalogItem item) {
        return ResponseEntity.ok(deviceService.createItem(item));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DeviceCatalogItem> getDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.getItemById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<DeviceCatalogItem>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllItems());
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<DeviceCatalogItem> updateDeviceStatus(@PathVariable Long id, @RequestParam Boolean active) {
        return ResponseEntity.ok(deviceService.updateActiveStatus(id, active));
    }
}