package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Device Catalog Endpoints")
public class DeviceCatalogController {

    private final DeviceCatalogService deviceCatalogService;

    public DeviceCatalogController(DeviceCatalogService deviceCatalogService) {
        this.deviceCatalogService = deviceCatalogService;
    }

    @GetMapping
    @Operation(summary = "Get all devices")
    public List<DeviceCatalogItem> getAllDevices() {
        return deviceCatalogService.getAllItems();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get device by ID")
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
    @Operation(summary = "Create new device")
    public ResponseEntity<DeviceCatalogItem> createDevice(@Valid @RequestBody DeviceCatalogItem device) {
        try {
            DeviceCatalogItem created = deviceCatalogService.createItem(device);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/active")
    @Operation(summary = "Update device active status")
    public ResponseEntity<DeviceCatalogItem> updateDeviceStatus(@PathVariable Long id, @RequestParam boolean active) {
        try {
            DeviceCatalogItem updated = deviceCatalogService.updateActiveStatus(id, active);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete device")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        try {
            deviceCatalogService.updateActiveStatus(id, false);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}