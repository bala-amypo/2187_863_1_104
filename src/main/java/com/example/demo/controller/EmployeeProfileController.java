package com.example.demo.controller;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeProfileController {

    private final EmployeeProfileRepository employeeProfileRepository;

    public EmployeeProfileController(EmployeeProfileRepository employeeProfileRepository) {
        this.employeeProfileRepository = employeeProfileRepository;
    }

    @GetMapping
    public List<EmployeeProfile> getAllEmployees() {
        return employeeProfileRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile> getEmployeeById(@PathVariable Long id) {
        return employeeProfileRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmployeeProfile createEmployee(@RequestBody EmployeeProfile employee) {
        if (employeeProfileRepository.findByEmployeeId(employee.getEmployeeId()).isPresent()) {
            throw new BadRequestException("EmployeeId already exists");
        }
        if (employeeProfileRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        return employeeProfileRepository.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProfile> updateEmployee(@PathVariable Long id, @RequestBody EmployeeProfile employee) {
        return employeeProfileRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setFullName(employee.getFullName());
                    existingEmployee.setDepartment(employee.getDepartment());
                    existingEmployee.setJobRole(employee.getJobRole());
                    existingEmployee.setActive(employee.getActive());
                    return ResponseEntity.ok(employeeProfileRepository.save(existingEmployee));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        return employeeProfileRepository.findById(id)
                .map(employee -> {
                    employeeProfileRepository.delete(employee);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}