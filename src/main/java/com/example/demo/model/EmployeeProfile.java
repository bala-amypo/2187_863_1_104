package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="employees")
public class EmployeeProfile{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    @Column(nullable = false,unique = true)
    private String employeeId;

    @Column(nullable = false)
    private String fullName;

    
    @Column(nullable = false,unique = true)
    private String email;


    
    @Column(nullable = false)
    private String department;

    
    @Column(nullable = false)
    private String jobRole;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false , updatable = false)
    private LocalDateTime createdAt;


    public EmployeeProfile(){
        this.createdAt = LocalDateTime.now();
    }

    public EmployeeProfile(String employeeId, String fullName, String email, String department,String jobRole,Boolean active){
        
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


    

    






