package com.mylearning.InsuranceApplication.service;


import com.mylearning.InsuranceApplication.dto.InsurancePlanDTO;
import com.mylearning.InsuranceApplication.entity.InsurancePlan;

import java.util.List;

public interface InsurancePlanService {
    List<InsurancePlan> getAllPlans();
    InsurancePlan getPlanById(Long id);
    double calculatePremium(Long planId, int age, int dependents);
    InsurancePlan addPlan(InsurancePlanDTO plan);
}

