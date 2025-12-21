package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {

    private final DeviceCatalogItemRepository deviceCatalogItemRepository;

    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository deviceCatalogItemRepository) {
        this.deviceCatalogItemRepository = deviceCatalogItemRepository;
    }

    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item) {
        if (deviceCatalogItemRepository.findByDeviceCode(item.getDeviceCode()).isPresent()) {
            throw new BadRequestException("Device code already exists");
        }
        if (item.getMaxAllowedPerEmployee() == null || item.getMaxAllowedPerEmployee() <= 0) {
            throw new BadRequestException("Invalid maxAllowedPerEmployee value");
        }
        return deviceCatalogItemRepository.save(item);
    }

    @Override
    public DeviceCatalogItem updateActiveStatus(Long id, boolean active) {
        DeviceCatalogItem device = deviceCatalogItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        device.setActive(active);
        return deviceCatalogItemRepository.save(device);
    }

    @Override
    public List<DeviceCatalogItem> getAllItems() {
        return deviceCatalogItemRepository.findAll();
    }
}