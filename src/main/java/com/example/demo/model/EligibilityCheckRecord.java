package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_check_records")
@Data
public class EligibilityCheckRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long employeeId;
    
    @Column(nullable = false)
    private Long deviceItemId;
    
    @Column(nullable = false)
    private Boolean isEligible;
    
    @Column(length = 1000)
    private String reason;
    
    private LocalDateTime checkedAt;
    
    @PrePersist
    public void prePersist() {
        if (checkedAt == null) {
            checkedAt = LocalDateTime.now();
        }
    }
}