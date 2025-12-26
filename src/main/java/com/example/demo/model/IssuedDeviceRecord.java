package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "issued_device_records")
@Data
public class IssuedDeviceRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long employeeId;
    
    @Column(nullable = false)
    private Long deviceItemId;
    
    @Column(nullable = false)
    private LocalDate issuedDate;
    
    private LocalDate returnedDate;
    
    @Column(nullable = false)
    private String status = "ISSUED";
    
    @PrePersist
    protected void onCreate() {
        if (issuedDate == null) {
            issuedDate = LocalDate.now();
        }
        if (status == null) {
            status = "ISSUED";
        }
    }
}