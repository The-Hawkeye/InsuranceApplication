package com.mylearning.InsuranceApplication.dto;

import lombok.Data;

@Data
public class InsurancePlanDTO {
    private String name;
    private String type; // LIFE or DENTAL
    private Double basePremium;
}

