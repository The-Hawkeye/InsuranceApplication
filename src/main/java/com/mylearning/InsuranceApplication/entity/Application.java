package com.mylearning.InsuranceApplication.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User getPrimarySubscriber() {
        return primarySubscriber;
    }

    public void setPrimarySubscriber(User primarySubscriber) {
        this.primarySubscriber = primarySubscriber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InsurancePlan getInsurancePlan() {
        return insurancePlan;
    }

    public void setInsurancePlan(InsurancePlan insurancePlan) {
        this.insurancePlan = insurancePlan;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public LocalDateTime getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(LocalDateTime reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

    // Primary user applying
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User primarySubscriber;

    // Plan selected
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private InsurancePlan insurancePlan;

    // Dependents list
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dependent> dependents = new ArrayList<>();

    private Double premium;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalDateTime submittedAt;

    private LocalDateTime reviewedAt;

    public enum ApplicationStatus {
        PENDING, APPROVED, REJECTED, NEEDS_REVIEW
    }

    // Getters & Setters
    // ...
}
