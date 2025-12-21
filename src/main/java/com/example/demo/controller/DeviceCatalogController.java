package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceCatalogController {

    private final DeviceCatalogService deviceCatalogService;

    // Constructor Injection 
    public DeviceCatalogController(DeviceCatalogService deviceCatalogService) {
        this.deviceCatalogService = deviceCatalogService;
    }

    // POST /api/devices
    @PostMapping
    public ResponseEntity<DeviceCatalogItem> createDevice(
            @RequestBody DeviceCatalogItem item) {

        DeviceCatalogItem createdItem = deviceCatalogService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    // GET /api/devices
    @GetMapping
    public ResponseEntity<List<DeviceCatalogItem>> getAllDevices() {
        return ResponseEntity.ok(deviceCatalogService.getAllItems());
    }

    // PUT /api/devices/{id}/active?active=true|false
    @PutMapping("/{id}/active")
    public ResponseEntity<DeviceCatalogItem> updateActiveStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        DeviceCatalogItem updatedItem =
                deviceCatalogService.updateActiveStatus(id, active);
        return ResponseEntity.ok(updatedItem);
    }
}
