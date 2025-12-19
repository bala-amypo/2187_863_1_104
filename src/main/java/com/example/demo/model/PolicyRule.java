package com.example.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name="user_accounts")
public class UserAccount{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    @Column(nullable = false,unique = true)
    private String ruleCode;

    @Column
    private String description;


    @Column
    private String description;

    
    @Column
    private String appliesToRole;
    
     
    @Column
    private String appliesToDepartment;

     @Column(nullable = false )
    private Integer maxDevicesAllowed ;


    @Column(nullable = false )
    private Boolean active = true;

    public PolicyRule(){
        
    }
  
    public PolicyRule(String ruleCode,Integer maxDevicesAllowed){
        
    this.ruleCode = ruleCode;
    this.maxDevicesAllowed = maxDevicesAllowed;
    this.active = true;
    }
    
    //Getters and Setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
    this.id = id;
    }
    public String getRuleCode(){
    return ruleCode;
    }
    public void setRuleCode(String ruleCode){
    this.ruleCode = ruleCode;
    }
      public String getEmail(){
    return email;
    }
    public void setEmail(String email){
    this.email = email;
    }
      public String getPasswordHash(){
    return passwordHash;
    }
    public void setPasswordHash(String passwordHash){
    this.passwordHash = passwordHash;
    }
    
    
    public Role getRole(){
        return role;
    }
    public void setRole(Role role){
        this.role= role;
    }
    public Boolean getActive(){
        return active;
    }
    public void setActive(Boolean active){
        this.active = active;

    }
   
    public boolean isActive(){
    return active != null && active;
    }
 
}


    

    






