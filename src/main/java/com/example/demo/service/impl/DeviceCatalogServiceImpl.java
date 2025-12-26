package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {
    
    @Autowired
    private DeviceCatalogItemRepository deviceRepo;
    
    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository deviceRepo) {
        this.deviceRepo = deviceRepo;
    }
    
    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item) {
        if (item.getMaxAllowedPerEmployee() != null && item.getMaxAllowedPerEmployee() <= 0) {
            throw new BadRequestException("maxAllowedPerEmployee must be greater than 0");
        }
        if (deviceRepo.findByDeviceCode(item.getDeviceCode()).isPresent()) {
            throw new BadRequestException("Device code already exists");
        }
        return deviceRepo.save(item);
    }
    
    @Override
    public DeviceCatalogItem getItemById(Long id) {
        return deviceRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Device item not found with id: " + id));
    }
    
    @Override
    public List<DeviceCatalogItem> getAllItems() {
        return deviceRepo.findAll();
    }
    
    @Override
    public DeviceCatalogItem updateActiveStatus(Long id, Boolean active) {
        DeviceCatalogItem item = getItemById(id);
        item.setActive(active);
        return deviceRepo.save(item);
    }
}