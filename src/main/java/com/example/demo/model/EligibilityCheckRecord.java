package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="eligibility_check_record")
public class EligibilityCheckRecord{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private Long deviceItemId;


    @Column(nullable = false)
    private Boolean isEligible;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false , updatable = false)
    private LocalDateTime checkedAt;


    public EmployeeProfile(){
        this.checkedAt = LocalDateTime.now();
    }

    public EligibilityCheckRecord(Long employeeId, String fullName, String email, String department,String jobRole,Boolean active){
        
    this.employeeId = employeeId;
    this.fullName = fullName;
    this.email = email;
    this.department = department;
    this.jobRole = jobRole;
    this.active = active;
    this.createdAt = LocalDateTime.now();
    }
    public EmployeeProfile(String employeeId, String fullName, String email, String department,String jobRole){

    this(employeeId,fullName,email,department,jobRole,true);
    }
    //Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
    this.id = id;
    }
    public String getEmployeeId(){
    return employeeId;
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


    

    






