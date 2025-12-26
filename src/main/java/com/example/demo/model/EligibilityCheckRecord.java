package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_check_record")
public class EligibilityCheckRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeProfile employee;

    @ManyToOne
    @JoinColumn(name = "device_item_id", nullable = false)
    private DeviceCatalogItem deviceItem;

    @Column(nullable = false)
    private Boolean isEligible;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false, updatable = false)
    private LocalDateTime checkedAt;

    @PrePersist
    public void prePersist() {
        this.checkedAt = LocalDateTime.now();
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}