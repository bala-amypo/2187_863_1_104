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
    private Boolean active =tr;


    

    






}