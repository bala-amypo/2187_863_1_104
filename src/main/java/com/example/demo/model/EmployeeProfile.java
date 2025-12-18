package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="employees")
public class EmployeeProfile{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message="Employee ID is required")
    @Column(nullable = false,unique = true)
    private String employeeId;

    @NotBlank(message="Full is required")
    @Column(nullable = false,unique = true)
    private String employeeId;


}