//package com.mylearning.InsuranceApplication.entity;
//
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "insurance_plans")
//public class InsurancePlan {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private String type; // LIFE, DENTAL
//
//    private String description;
//
//    private Double baseCost;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Double getBaseCost() {
//        return baseCost;
//    }
//
//    public void setBaseCost(Double baseCost) {
//        this.baseCost = baseCost;
//    }
//
//    // Getters & Setters
//    // ...
//}


package com.mylearning.InsuranceApplication.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "insurance_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsurancePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long planId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanType planType;  // LIFE, DENTAL

    @Column(nullable = false, length = 100)
    private String planName;

    private String description;

    private Double costPerDependent;

    private boolean active = true;

    @JsonManagedReference
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PlanAgeRule> ageRules;

    public enum PlanType {
        LIFE, DENTAL
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCostPerDependent() {
        return costPerDependent;
    }

    public void setCostPerDependent(Double costPerDependent) {
        this.costPerDependent = costPerDependent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<PlanAgeRule> getAgeRules() {
        return ageRules;
    }

    public void setAgeRules(List<PlanAgeRule> ageRules) {
        this.ageRules = ageRules;
    }
}
