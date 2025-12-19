package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity

@Table(name="issued_device_record")
public class IssuedDeviceRecord{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id",nullable= false)
    private Long employeeId;

    @Column(name = "device_item_id",nullable= false)
    private Long deviceItemId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id",nullable = false)
    private EmployeeProfile employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_item_id",nullable = false)
    private DeviceCatalogItem deviceItem;


    @Column(nullable = false)
    private LocalDate issuedDate;

    @Column
    private LocalDate returnedDate;

    @Column(nullable = false)
    private String status;


    public IssuedDeviceRecord(){
        
    }

    public IssuedDeviceRecord(Long employee, Long deviceItem){
    this.employee = employee;
    this.deviceItem = deviceItem;
    this.issuedDate = LocalDate.now();
    this.status = "ISSUED";
    }
    //businness rule enforcement
    public void markAsReturned(){
    if(this.returnedDate != null){
    throw new IllegalStateException("already returned");
    }
    this.returnedDate = LocalDate.now()
    this.status = "RETURNED";
    //Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
    this.id = id;
    }
    public Long getEmployee(){
    return employeeId;
    }
    public void setEmployeeId(Long employeeId){
    this.employeeId = employeeId;
    }
    public Long getDeviceItemId(){
    return deviceItemId;
    }
    public void setDeviceItemId(Long deviceItemId){
    this.deviceItemId = deviceItemId;
    }
    
    public LocalDate getIssuedDate(){
        return issuedDate;
    }
    public void setIssuedDate(LocalDate issuedDate){
        this.issuedDate= issuedDate;
    }
    public LocalDate getReturnedDate(){
        return returnedDate;
    }
    public void setReturnedDate(LocalDate returnedDate){
        this.returnedDate = returnedDate;
        this.status = (returnedDate == null) ? "ISSUED" : "RETURNED";
    }
    public String getStatus(){
    return status;
    }

    public boolean isActive(){
    return returnedDate == null;
    }
    public void markAsReturned(){
    this.returnedDate = LocalDate.now();
    this.status = "RETURNED";
    }
}


    

    






