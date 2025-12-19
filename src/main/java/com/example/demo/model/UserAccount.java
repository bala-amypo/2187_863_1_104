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
    
       public enum Role{
    ADMIN,
    IT_OPERATOR,
    AUDITOR
}

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
    public String getFullName(){
    return fullName;
    }
    public void setFullName(String fullName){
    this.fullName = fullName;
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


    

    






