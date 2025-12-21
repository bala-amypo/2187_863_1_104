package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceCatalogController {

    private final DeviceCatalogService deviceCatalogService;

    public DeviceCatalogController(DeviceCatalogService deviceCatalogService) {
        this.deviceCatalogService = deviceCatalogService;
    }

    @GetMapping
    public List<DeviceCatalogItem> getAllDevices() {
        return deviceCatalogService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceCatalogItem> getDeviceById(@PathVariable Long id) {
        try {
            DeviceCatalogItem device = deviceCatalogService.getAllItems().stream()
                    .filter(d -> d.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            return device != null ? ResponseEntity.ok(device) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DeviceCatalogItem> createDevice(@RequestBody DeviceCatalogItem device) {
        try {
            DeviceCatalogItem created = deviceCatalogService.createItem(device);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceCatalogItem> updateDevice(@PathVariable Long id, @RequestBody DeviceCatalogItem device) {
        try {
            DeviceCatalogItem updated = deviceCatalogService.updateDevice(id, device);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        try {
            deviceCatalogService.updateActiveStatus(id, false);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}