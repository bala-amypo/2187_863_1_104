package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceCatalogController {
    
    private final DeviceCatalogItemRepository deviceCatalogItemRepository;
    
    public DeviceCatalogController(DeviceCatalogItemRepository deviceCatalogItemRepository) {
        this.deviceCatalogItemRepository = deviceCatalogItemRepository;
    }
    
    @GetMapping
    public List<DeviceCatalogItem> getAllDevices() {
        return deviceCatalogItemRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DeviceCatalogItem> getDeviceById(@PathVariable Long id) {
        return deviceCatalogItemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public DeviceCatalogItem createDevice(@RequestBody DeviceCatalogItem device) {
        if (deviceCatalogItemRepository.findByDeviceCode(device.getDeviceCode()).isPresent()) {
            throw new BadRequestException("Device code already exists");
        }
        if (device.getMaxAllowedPerEmployee() == null || device.getMaxAllowedPerEmployee() <= 0) {
            throw new BadRequestException("Invalid maxAllowedPerEmployee value");
        }
        return deviceCatalogItemRepository.save(device);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DeviceCatalogItem> updateDevice(@PathVariable Long id, @RequestBody DeviceCatalogItem device) {
        return deviceCatalogItemRepository.findById(id)
                .map(existingDevice -> {
                    existingDevice.setDeviceType(device.getDeviceType());
                    existingDevice.setModel(device.getModel());
                    existingDevice.setMaxAllowedPerEmployee(device.getMaxAllowedPerEmployee());
                    existingDevice.setActive(device.getActive());
                    return ResponseEntity.ok(deviceCatalogItemRepository.save(existingDevice));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable Long id) {
        return deviceCatalogItemRepository.findById(id)
                .map(device -> {
                    deviceCatalogItemRepository.delete(device);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
