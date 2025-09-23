package com.mylearning.InsuranceApplication.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "plan_health_rules")
public class PlanHealthRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private InsurancePlan plan;

    // Booleans representing whether the plan allows this condition
    private boolean smokingOrAlcoholAllowed;
    private boolean recentHospitalizationAllowed;
    private boolean previousClaimsAllowed;
    private boolean proposalDeclinedAllowed;
    private boolean otherInsuranceAllowed;

    private boolean diabetesAllowed;
    private boolean hypertensionAllowed;
    private boolean cancerAllowed;
    private boolean asthmaAllowed;
    private boolean heartDiseaseAllowed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsurancePlan getPlan() {
        return plan;
    }

    public void setPlan(InsurancePlan plan) {
        this.plan = plan;
    }

    public boolean isSmokingOrAlcoholAllowed() {
        return smokingOrAlcoholAllowed;
    }

    public void setSmokingOrAlcoholAllowed(boolean smokingOrAlcoholAllowed) {
        this.smokingOrAlcoholAllowed = smokingOrAlcoholAllowed;
    }

    public boolean isRecentHospitalizationAllowed() {
        return recentHospitalizationAllowed;
    }

    public void setRecentHospitalizationAllowed(boolean recentHospitalizationAllowed) {
        this.recentHospitalizationAllowed = recentHospitalizationAllowed;
    }

    public boolean isPreviousClaimsAllowed() {
        return previousClaimsAllowed;
    }

    public void setPreviousClaimsAllowed(boolean previousClaimsAllowed) {
        this.previousClaimsAllowed = previousClaimsAllowed;
    }

    public boolean isProposalDeclinedAllowed() {
        return proposalDeclinedAllowed;
    }

    public void setProposalDeclinedAllowed(boolean proposalDeclinedAllowed) {
        this.proposalDeclinedAllowed = proposalDeclinedAllowed;
    }

    public boolean isOtherInsuranceAllowed() {
        return otherInsuranceAllowed;
    }

    public void setOtherInsuranceAllowed(boolean otherInsuranceAllowed) {
        this.otherInsuranceAllowed = otherInsuranceAllowed;
    }

    public boolean isHypertensionAllowed() {
        return hypertensionAllowed;
    }

    public void setHypertensionAllowed(boolean hypertensionAllowed) {
        this.hypertensionAllowed = hypertensionAllowed;
    }

    public boolean isDiabetesAllowed() {
        return diabetesAllowed;
    }

    public void setDiabetesAllowed(boolean diabetesAllowed) {
        this.diabetesAllowed = diabetesAllowed;
    }

    public boolean isCancerAllowed() {
        return cancerAllowed;
    }

    public void setCancerAllowed(boolean cancerAllowed) {
        this.cancerAllowed = cancerAllowed;
    }

    public boolean isAsthmaAllowed() {
        return asthmaAllowed;
    }

    public void setAsthmaAllowed(boolean asthmaAllowed) {
        this.asthmaAllowed = asthmaAllowed;
    }

    public boolean isHeartDiseaseAllowed() {
        return heartDiseaseAllowed;
    }

    public void setHeartDiseaseAllowed(boolean heartDiseaseAllowed) {
        this.heartDiseaseAllowed = heartDiseaseAllowed;
    }
}
