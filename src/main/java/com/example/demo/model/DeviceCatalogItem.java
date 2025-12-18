package com.example.demo.model;

import jakarta.persistence.*;
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="device_catalog_item")
public class DeviceCatalogItem{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    @Column(nullable = false)
    private String deviceCode;

    @Column(nullable = false)
    private String deviceType;

    
    @Column(nullable = false)
    private String model;


    @Min(value = 1,message ="maxAllowedPerEmployee must be at least 1")
    @Column(nullable = false)
    private Integer maxAllowedPerEmployee;


    @Column(nullable = false)
    private Boolean active = true;

    


    public DeviceCatalogItem(){
        
    }

    public DeviceCatalogItem(String deviceCode, String deviceType, String model,Integer maxAllowedPerEmployee,Boolean active){
        
    this.deviceCode = deviceCode;
    this.deviceType = deviceType;
    this.model = model;
    setMaxAllowedPerEmployee(maxAllowedPerEmployee);
    this.active = active;

    }
    public DeviceCatalogItem(String deviceCode, String deviceType, String model,Integer maxAllowedPerEmployee){
    this(deviceCode,deviceType,model,maxAllowedPerEmployee,true);
    }
    //Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
    this.id = id;
    }
    public String getDeviceCode(){
    return device;
    }
    public void setEmployeeId(String employeeId){
    this.employeeId = employeeId;
    }
    public String getEmail(){
    return email;
    }
    public void setEmail(String email){
    this.email = email;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public String getJobRole(){
    return jobRole;
    }
    public void setJobRole(String jobRole){
    this.jobRole = jobRole;
    }
    public Boolean getActive(){
        return active;
    }
    public void setActive(Boolean active){
        this.active = active;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public boolean isActive(){
    return active != null && active;
    }
}


    

    






