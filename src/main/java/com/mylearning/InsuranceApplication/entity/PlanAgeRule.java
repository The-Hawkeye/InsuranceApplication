//package com.mylearning.InsuranceApplication.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "plan_age_rules")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class PlanAgeRule {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long ruleId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "plan_id", nullable = false)
//    private InsurancePlan plan;
//
//    @Column(nullable = false)
//    private int minAge;
//
//    @Column(nullable = false)
//    private int maxAge;
//
//    @Column(nullable = false)
//    private Double baseCost;
//
//    @Column(nullable = false)
//    private Double perYearIncrement;
//}


package com.mylearning.InsuranceApplication.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "plan_age_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanAgeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ruleId;

    private int minAge;
    private int maxAge;
    private double baseCost;
    private double perYearIncrement;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonBackReference
    private InsurancePlan plan;

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(double baseCost) {
        this.baseCost = baseCost;
    }

    public double getPerYearIncrement() {
        return perYearIncrement;
    }

    public void setPerYearIncrement(double perYearIncrement) {
        this.perYearIncrement = perYearIncrement;
    }

    public InsurancePlan getPlan() {
        return plan;
    }

    public void setPlan(InsurancePlan plan) {
        this.plan = plan;
    }
}
