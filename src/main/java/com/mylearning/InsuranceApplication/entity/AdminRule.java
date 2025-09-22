package com.mylearning.InsuranceApplication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_rules")
public class AdminRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public String getConditionJson() {
        return conditionJson;
    }

    public void setConditionJson(String conditionJson) {
        this.conditionJson = conditionJson;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(length = 2000)
    private String ruleDescription;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conditionJson;
    /*
       Example:
       {
         "field": "age",
         "operator": ">=",
         "value": 60,
         "action": "NEEDS_REVIEW"
       }
    */

    private boolean active = true;

}
