package com.example.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name="user_accounts")
public class UserAccount{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String passwordHash;


    @Column(nullable = false,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false )
    private Boolean active = true;


    public UserAccount(){
        
    }
  
    public UserAccount(String fullName, String email, String passwordHash,Role role){
        
    this.fullName = fullName;
    this.email = email;
    this.passwordHash= passwordHash;
    this.role = role;
    this.active = true;
    }
    
    //Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
    this.id = id;
    }
    public Long getEmployeeId(){
    return employeeId;
    }
    public void setEmployeeId(Long employeeId){
    this.employeeId = employeeId;
    }
    public Long getDeviceItemId(){
    return deviceItemId;
    }
    public void setDeviceItemId(Long deviceItemId){
    this.deviceItemId = deviceItemId;
    }
    
    public Boolean getIsEligible(){
        return isEligible;
    }
    public void setIsEligible(Boolean isEligible){
        this.isEligible= isEligible;
    }
    public String getReason(){
        return reason;
    }
    public void setReason(String reason){
        this.reason = reason;

    }
    public LocalDateTime getCheckedAt(){
        return checkedAt;
    }
    public void setCheckedAt(LocalDateTime checkedAt){
        this.checkedAt = checkedAt;
    }

    public boolean isEligible(){
    return isEligible != null && isEligible;
    }
}


    

    






