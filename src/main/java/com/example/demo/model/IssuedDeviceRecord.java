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

    public IssuedDeviceRecord(EmployeeProfile employee, DeviceCatdeviceItem){
    this.employee = employee;
    this.deviceItem = deviceItem;
    this.issuedDate = LocalDate.now();
    this.status = "ISSUED";
    }

    //Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
    this.id = id;
    }
    public EmployeeProfile getEmployee(){
    return employee;
    }
    public void setEmployees(EmployeeProfile employee){
    this.employee = employee;
    }
    public DeviceCatalogItem getDeviceItem(){
    return deviceItem;
    }
    public void setDeviceItem(DeviceCatalogueItem deviceItem){
    this.deviceItem = deviceItem;
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
        if(this.returnedDate != null){
        throw new RunTimeException("already returned");
        }
        this.returnedDate = returnedDate;
        this.status = "RETURNED";
    }
    public String getStatus(){
    return status;
    }

    public boolean isActive(){
    return returnedDate == null;
    }
   
}


    

    






