package com.example.demo.controller;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Profile Endpoints")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @GetMapping
    @Operation(summary = "Get all employees")
    public List<EmployeeProfile> getAllEmployees() {
        return employeeProfileService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID")
    public ResponseEntity<EmployeeProfile> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeProfile employee = employeeProfileService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Create new employee")
    public ResponseEntity<EmployeeProfile> createEmployee(@Valid @RequestBody EmployeeProfile employee) {
        try {
            EmployeeProfile created = employeeProfileService.createEmployee(employee);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update employee status")
    public ResponseEntity<EmployeeProfile> updateEmployeeStatus(@PathVariable Long id, @RequestParam boolean active) {
        try {
            EmployeeProfile updated = employeeProfileService.updateEmployeeStatus(id, active);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeProfileService.updateEmployeeStatus(id, false);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}