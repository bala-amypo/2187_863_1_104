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

    public EmployeeProfile(String employeeId, String fullName, String email, String department,String jobRole,Boolean active){
        
    this.employeeId = employeeId;
    this.fullName = fullName;
    this.email = email;
    this department = department;
    this.jobRole = jobRole;
    this.active = active;
    this.createdAt = LocalDataTime.now();
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
    public void setEmail



    

    






}