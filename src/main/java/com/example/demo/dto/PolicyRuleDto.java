package com.example.demo.dto;

public class PolicyRuleDto {
    private String ruleCode;
    private String description;
    private String appliesToRole;
    private String appliesToDepartment;
    private Integer maxDevicesAllowed;
    private Boolean active;

    public PolicyRuleDto() {}

    public String getRuleCode() { return ruleCode; }
    public void setRuleCode(String ruleCode) { this.ruleCode = ruleCode; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAppliesToRole() { return appliesToRole; }
    public void setAppliesToRole(String appliesToRole) { this.appliesToRole = appliesToRole; }
    public String getAppliesToDepartment() { return appliesToDepartment; }
    public void setAppliesToDepartment(String appliesToDepartment) { this.appliesToDepartment = appliesToDepartment; }
    public Integer getMaxDevicesAllowed() { return maxDevicesAllowed; }
    public void setMaxDevicesAllowed(Integer maxDevicesAllowed) { this.maxDevicesAllowed = maxDevicesAllowed; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}