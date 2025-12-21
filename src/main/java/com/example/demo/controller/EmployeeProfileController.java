package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    // Constructor Injection
    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    // GET /api/employees
    @GetMapping
    public List<EmployeeProfile> getAllEmployees() {
        return employeeProfileService.getAllEmployees();
    }

    // GET /api/employees/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile> getEmployeeById(@PathVariable Long id) {
        EmployeeProfile employee = employeeProfileService.getEmployeeById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
        return ResponseEntity.ok(employee);
    }

    // POST /api/employees
    @PostMapping
    public ResponseEntity<EmployeeProfile> createEmployee(@RequestBody EmployeeProfile employee) {
        EmployeeProfile created = employeeProfileService.createEmployee(employee);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // PUT /api/employees/{id}/status
    @PutMapping("/{id}/status")
    public ResponseEntity<EmployeeProfile> updateEmployeeStatus(
            @PathVariable Long id,
            @RequestParam boolean active) {

        EmployeeProfile updated = employeeProfileService.updateEmployeeStatus(id, active);
        return ResponseEntity.ok(updated);
    }
}
