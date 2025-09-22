package com.mylearning.InsuranceApplication.service.impl;

import com.mylearning.InsuranceApplication.entity.InsurancePlan;
import com.mylearning.InsuranceApplication.repository.InsurancePlanRepository;
import com.mylearning.InsuranceApplication.service.InsurancePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {

    @Autowired
    private InsurancePlanRepository planRepository;

    @Override
    public List<InsurancePlan> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public InsurancePlan addPlan(InsurancePlan plan) {
        return planRepository.save(plan);
    }
}
