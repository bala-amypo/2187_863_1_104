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
    
    public LocalDate getIssuedDate(){
        return issuedDate;
    }
    public void setIssuedDate(LocalDate issuedDate){
        this.issuedDate= issuedDate;
    }
    public LocalDateTime getReturnedDate(){
        return returnedDate;
    }
    public void setReturnedDate(LocalDateTime returnedDate){
        this.returnedDate = returnedDate;
        this.status = (returnedDate == null) ? "ISSUED" : "RETURNED;
    }

    public boolean isActive(){
    return isEligibl
    }
}


    

    






