//package com.mylearning.InsuranceApplication.dto;
//
//import lombok.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class ApplicationRequest {
//
//    private ApplicantInfo applicant;        // Primary applicant details
//    private Long planId;                    // Selected InsurancePlan id
//    private LocalDate coverageStartDate;    // When coverage should begin
//    private List<DependentInfo> dependents; // List of dependents to cover
//
//    public ApplicantInfo getApplicant() {
//        return applicant;
//    }
//
//    public void setApplicant(ApplicantInfo applicant) {
//        this.applicant = applicant;
//    }
//
//    public Long getPlanId() {
//        return planId;
//    }
//
//    public void setPlanId(Long planId) {
//        this.planId = planId;
//    }
//
//    public LocalDate getCoverageStartDate() {
//        return coverageStartDate;
//    }
//
//    public void setCoverageStartDate(LocalDate coverageStartDate) {
//        this.coverageStartDate = coverageStartDate;
//    }
//
//    public List<DependentInfo> getDependents() {
//        return dependents;
//    }
//
//    public void setDependents(List<DependentInfo> dependents) {
//        this.dependents = dependents;
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class ApplicantInfo {
//        private String firstName;
//        private String lastName;
//        private int age;
//        private String email;
//        private String phone;
//
//        public String getFirstName() {
//            return firstName;
//        }
//
//        public void setFirstName(String firstName) {
//            this.firstName = firstName;
//        }
//
//        public String getLastName() {
//            return lastName;
//        }
//
//        public void setLastName(String lastName) {
//            this.lastName = lastName;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//        }
//    }
//
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class DependentInfo {
//        private String name;
//        private int age;
//        private String relation; // e.g., CHILD, SPOUSE, PARENT
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        public String getRelation() {
//            return relation;
//        }
//
//        public void setRelation(String relation) {
//            this.relation = relation;
//        }
//    }
//}



package com.mylearning.InsuranceApplication.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationRequest {

    private ApplicantInfo applicant;        // Primary applicant details
    private Long planId;                    // Selected InsurancePlan id
    private LocalDate coverageStartDate;    // When coverage should begin
    private List<DependentInfo> dependents; // List of dependents to cover

    public ApplicantInfo getApplicant() {
        return applicant;
    }

    public void setApplicant(ApplicantInfo applicant) {
        this.applicant = applicant;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public LocalDate getCoverageStartDate() {
        return coverageStartDate;
    }

    public void setCoverageStartDate(LocalDate coverageStartDate) {
        this.coverageStartDate = coverageStartDate;
    }

    public List<DependentInfo> getDependents() {
        return dependents;
    }

    public void setDependents(List<DependentInfo> dependents) {
        this.dependents = dependents;
    }

    // ---------------- Applicant ----------------
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApplicantInfo {
        private String firstName;
        private String lastName;
        private int age;
        private String email;
        private String phone;
        private MedicalInfoDto medicalInfo;   // ✅ Medical Info for applicant

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public MedicalInfoDto getMedicalInfo() {
            return medicalInfo;
        }

        public void setMedicalInfo(MedicalInfoDto medicalInfo) {
            this.medicalInfo = medicalInfo;
        }
    }

    // ---------------- Dependent ----------------
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DependentInfo {
        private String name;
        private int age;
        private String relation; // e.g., CHILD, SPOUSE, PARENT
        private MedicalInfoDto medicalInfo;   // ✅ Medical Info for dependent

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public MedicalInfoDto getMedicalInfo() {
            return medicalInfo;
        }

        public void setMedicalInfo(MedicalInfoDto medicalInfo) {
            this.medicalInfo = medicalInfo;
        }
    }

    // ---------------- Medical Info ----------------
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MedicalInfoDto {
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

        public boolean isAsthma() {
            return asthma;
        }

        public void setAsthma(boolean asthma) {
            this.asthma = asthma;
        }

        public boolean isHeartDisease() {
            return heartDisease;
        }

        public void setHeartDisease(boolean heartDisease) {
            this.heartDisease = heartDisease;
        }
    }
}
