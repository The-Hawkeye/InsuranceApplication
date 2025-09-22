package com.mylearning.InsuranceApplication.dto;

import lombok.Data;

@Data
public class AdminRuleDTO {
    private String ruleName;
    private String ruleType;  // e.g., MAX_DEPENDENTS, MIN_AGE
    private Double thresholdValue;
    private Boolean active;
}

