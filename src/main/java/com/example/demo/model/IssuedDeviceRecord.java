package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "issued_device_record")
public class IssuedDeviceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Employee is required")
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    @NotNull(message = "Device item is required")
    @ManyToOne
    @JoinColumn(name = "device_item_id", nullable = false)
    private DeviceCatalogItem deviceItem;

    @Column(nullable = false)
    private LocalDate issuedDate;

    private LocalDate returnedDate;

    @Column(nullable = false)
    private String status = "ISSUED";

    public IssuedDeviceRecord() {
    }

    public IssuedDeviceRecord(EmployeeProfile employee, DeviceCatalogItem deviceItem, LocalDate issuedDate) {
        this.employee = employee;
        this.deviceItem = deviceItem;
        this.issuedDate = issuedDate;
        this.status = "ISSUED";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeProfile getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeProfile employee) {
        this.employee = employee;
    }

    public DeviceCatalogItem getDeviceItem() {
        return deviceItem;
    }

    public void setDeviceItem(DeviceCatalogItem deviceItem) {
        this.deviceItem = deviceItem;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployeeId(Long employeeId) {
        // This method is needed for tests but we use the relationship
        if (this.employee == null) {
            this.employee = new EmployeeProfile();
        }
        this.employee.setId(employeeId);
    }

    public void setDeviceItemId(Long deviceItemId) {
        // This method is needed for tests but we use the relationship
        if (this.deviceItem == null) {
            this.deviceItem = new DeviceCatalogItem();
        }
        this.deviceItem.setId(deviceItemId);
    }
}