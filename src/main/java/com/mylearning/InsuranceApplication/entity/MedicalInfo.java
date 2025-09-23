package com.mylearning.InsuranceApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "medical_info")
public class MedicalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Soft delete flag
    private boolean active = true;

    private boolean smokingOrAlcohol;
    private boolean recentHospitalization;
    private boolean previousClaims;
    private boolean proposalDeclined;
    private boolean otherInsurance;

    private boolean diabetes;
    private boolean hypertension;
    private boolean cancer;
    private boolean asthma;
    private boolean heartDisease;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne
    @JoinColumn(name = "dependent_id")
    private Dependent dependent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSmokingOrAlcohol() {
        return smokingOrAlcohol;
    }

    public void setSmokingOrAlcohol(boolean smokingOrAlcohol) {
        this.smokingOrAlcohol = smokingOrAlcohol;
    }

    public boolean isRecentHospitalization() {
        return recentHospitalization;
    }

    public void setRecentHospitalization(boolean recentHospitalization) {
        this.recentHospitalization = recentHospitalization;
    }

    public boolean isPreviousClaims() {
        return previousClaims;
    }

    public void setPreviousClaims(boolean previousClaims) {
        this.previousClaims = previousClaims;
    }

    public boolean isProposalDeclined() {
        return proposalDeclined;
    }

    public void setProposalDeclined(boolean proposalDeclined) {
        this.proposalDeclined = proposalDeclined;
    }

    public boolean isOtherInsurance() {
        return otherInsurance;
    }

    public void setOtherInsurance(boolean otherInsurance) {
        this.otherInsurance = otherInsurance;
    }

    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isHypertension() {
        return hypertension;
    }

    public void setHypertension(boolean hypertension) {
        this.hypertension = hypertension;
    }

    public boolean isCancer() {
        return cancer;
    }

    public void setCancer(boolean cancer) {
        this.cancer = cancer;
    }

    public boolean isHeartDisease() {
        return heartDisease;
    }

    public void setHeartDisease(boolean heartDisease) {
        this.heartDisease = heartDisease;
    }

    public boolean isAsthma() {
        return asthma;
    }

    public void setAsthma(boolean asthma) {
        this.asthma = asthma;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Dependent getDependent() {
        return dependent;
    }

    public void setDependent(Dependent dependent) {
        this.dependent = dependent;
    }
}
