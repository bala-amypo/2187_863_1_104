package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity

@Table(name="issued_device_record")
public class IssuedDeviceRecord{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id",nullable = false)
    private EmployeeProfile employeeId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "device_item_id",nullable = false)
    @Column(nullable = false)
    private DeviceCatalogItem deviceItemId;


    @Column(nullable = false)
    private LocalDate issuedDate;

    @Column
    private LocalDate returnedDate;

    @Column(nullable = false)
    private String status;


    public IssuedDeviceRecord(){
        
    }

    public IssuedDeviceRecord(Long employeeId, Long deviceItemId){
    this.employeeId = employeeId;
    this.deviceItemId = deviceItemId;
    this.issuedDate = LocalDate.now();
    this.status = "ISSUED";
    }
    //businness rule enforcement
    public void markAsReturned(){
    if(this.returnedDate != null){
    throw new IllegalStateException("already returned");
    }
    this.returnedDate = LocalDate.now();
    this.status = "RETURNED";
    //Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
    this.id = id;
    }
    public EmployeeProfile getEmployeeId(){
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


    

    






