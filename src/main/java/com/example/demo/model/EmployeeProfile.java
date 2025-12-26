package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_profiles")
@Data
public class EmployeeProfile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Employee ID is required")
    private String employeeId;
    
    @Column(nullable = false)
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    private String department;
    
    private String jobRole;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (active == null) {
            active = true;
        }
    }
}