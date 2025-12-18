package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="issued_device_record")
public class IssuedDeviceRecord{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private Long deviceItemId;


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
    this.isEligible = isEligible;
    this.reason = reason;
    this.checkedAt = LocalDateTime.now();
    }
    @PrePersist
    protected void onCheck(){
        this.checkedAt = LocalDataTime.now();
    }
    //Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
    this.id = id;
    }
    public Long getEmployeeId(){
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
    
    public Boolean getIsEligible(){
        return isEligible;
    }
    public void setIsEligible(Boolean isEligible){
        this.isEligible= isEligible;
    }
    public String getReason(){
        return reason;
    }
    public void setReason(String reason){
        this.reason = reason;

    }
    public LocalDateTime getCheckedAt(){
        return checkedAt;
    }
    public void setCheckedAt(LocalDateTime checkedAt){
        this.checkedAt = checkedAt;
    }

    public boolean isEligible(){
    return isEligible != null && isEligible;
    }
}


    

    






