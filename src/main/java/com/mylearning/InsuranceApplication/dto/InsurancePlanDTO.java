//package com.mylearning.InsuranceApplication.dto;
//
//import lombok.Data;
//
//@Data
//public class InsurancePlanDTO {
//    private String name;
//    private String type; // LIFE or DENTAL
//    private Double basePremium;
//}
//
package com.mylearning.InsuranceApplication.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePlanDTO {

    private String planType;
    private String planName;
    private String description;
    private double costPerDependent;
    private List<PlanAgeRuleRequest> ageRules;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlanAgeRuleRequest {
        private int minAge;
        private int maxAge;
        private double baseCost;
        private double perYearIncrement;

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
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
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

    public double getCostPerDependent() {
        return costPerDependent;
    }

    public void setCostPerDependent(double costPerDependent) {
        this.costPerDependent = costPerDependent;
    }

    public List<PlanAgeRuleRequest> getAgeRules() {
        return ageRules;
    }

    public void setAgeRules(List<PlanAgeRuleRequest> ageRules) {
        this.ageRules = ageRules;
    }
}
