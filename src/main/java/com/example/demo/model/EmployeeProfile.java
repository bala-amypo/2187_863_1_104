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

    public EmployeeProfile(String employeeId, String fullName, String email, String department,String jobRole){
    this(employeeId,fullName,email,department,jobRole,true);

    this.emplo


    

    






}