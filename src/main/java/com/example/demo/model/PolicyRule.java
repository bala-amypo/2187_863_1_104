package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "policy_rules")
@Data
public class PolicyRule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Rule code is required")
    private String ruleCode;
    
    private String appliesToDepartment;
    
    private String appliesToRole;
    
    @Positive(message = "Max devices allowed must be positive")
    private Integer maxDevicesAllowed;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @PrePersist
    protected void onCreate() {
        if (active == null) {
            active = true;
        }
    }
}