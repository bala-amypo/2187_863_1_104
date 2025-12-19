package com.example.demo.controller;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.service.EmployeeProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@SecurityRequirement(name = "Bearer Authentication")
public class EmployeeProfileController {

    private final EmployeeProfileService service;

    public EmployeeProfileController(EmployeeProfileService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeProfile>createEmployee(
    @RequestBody EmployeeProfile employee){

        return new ResponseEntity<>(
            service.createEmployee(employee),
            HttpStatus.CREATED
        );
    }


    @GetMapping
    public 
    ResponseEntity<List<EmployeeProfile>>getAllEmployees(){
        return 
        ResponseEntity.ok<>(service.getAllEmployees()
         );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile>
    getEmployeeById(
        @PathVariable Long id){
            return ResponseEntity.ok(service.getEmployeeById(id)
            );
        }

    @PutMapping ("/{id}/status")
    public ResponseEntity<Map<String,
    String>>updateEmployeeStatus(
    @PathVariable Long id,
    @RequestParam Boolean active) {
     service.updateEmployeeStatus(id,active);
     return ResponseEntity.ok(Map.of("message","Employee status updated")
     );
        }


     @DeleteMapping("/{id}")
     public ResponseEntity<Void>
     deleteEmployee(
        @PathVariable Long id ){
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
        }
     }

    