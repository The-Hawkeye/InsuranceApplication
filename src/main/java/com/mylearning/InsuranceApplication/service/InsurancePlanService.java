package com.mylearning.InsuranceApplication.service;


import com.mylearning.InsuranceApplication.entity.InsurancePlan;

import java.util.List;

public interface InsurancePlanService {
    List<InsurancePlan> getAllPlans();
    InsurancePlan addPlan(InsurancePlan plan);
}

