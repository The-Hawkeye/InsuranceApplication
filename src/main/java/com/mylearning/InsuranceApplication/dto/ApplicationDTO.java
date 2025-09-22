package com.mylearning.InsuranceApplication.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationDTO {
    private Long planId;
    private List<DependentDTO> dependents;
    private Double premium;
}

