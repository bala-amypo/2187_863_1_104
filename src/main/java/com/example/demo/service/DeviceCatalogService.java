package com.example.demo.service;

import com.example.demo.model.DeviceCatalogItem;
import java.util.List;

public interface DeviceCatalogService {
    DeviceCatalogItem createItem(DeviceCatalogItem item);
    DeviceCatalogItem updateActiveStatus(Long id, boolean active);
    DeviceCatalogItem updateDevice(Long id, DeviceCatalogItem device);
    List<DeviceCatalogItem> getAllItems();
}