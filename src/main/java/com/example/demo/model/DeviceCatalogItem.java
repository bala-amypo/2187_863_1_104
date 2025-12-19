package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="device_catalog_item")
public class DeviceCatalogItem{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(nullable = false,unique =true)
    private String deviceCode;

    @NotBlank
    @Column(nullable = false)
    private String deviceType;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @NotNull
    @Min(value = 1,message = "maxAllowedPerEmployee must be at least 1")
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
    this.maxAllowedPerEmployee = maxAllowedPerEmployee;
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
    return deviceCode;
    }
    public void setDeviceCode(String deviceCode){
    this.deviceCode = deviceCode;
    }
    public String getDeviceType(){
    return deviceType;
    }
    public void setDeviceType(String deviceType){
    this.deviceType = deviceType;
    }
    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }
    public Integer getMaxAllowedPerEmployee(){
    return maxAllowedPerEmployee;
    }
    public void setMaxAllowedPerEmployee(Integer maxAllowedPerEmployee){
    this.maxAllowedPerEmployee = maxAllowedPerEmployee;
    }

    public Boolean getActive(){
        return active;
    }
    public void setActive(Boolean active){
        this.active = active;
    }
    
    public boolean isActive(){
    return active != null && active;
    }
}


    

    






