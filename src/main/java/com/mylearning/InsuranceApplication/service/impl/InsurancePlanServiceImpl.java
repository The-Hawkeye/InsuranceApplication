//package com.mylearning.InsuranceApplication.service.impl;
//
//import com.mylearning.InsuranceApplication.entity.InsurancePlan;
//import com.mylearning.InsuranceApplication.repository.InsurancePlanRepository;
//import com.mylearning.InsuranceApplication.service.InsurancePlanService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class InsurancePlanServiceImpl implements InsurancePlanService {
//
//    @Autowired
//    private InsurancePlanRepository planRepository;
//
//    @Override
//    public List<InsurancePlan> getAllPlans() {
//        return planRepository.findAll();
//    }
//
//    @Override
//    public InsurancePlan addPlan(InsurancePlan plan) {
//        return planRepository.save(plan);
//    }
//}


package com.mylearning.InsuranceApplication.service.impl;

import com.mylearning.InsuranceApplication.dto.InsurancePlanDTO;
import com.mylearning.InsuranceApplication.entity.InsurancePlan;
import com.mylearning.InsuranceApplication.entity.PlanAgeRule;
import com.mylearning.InsuranceApplication.exception.ResourceNotFoundException;
import com.mylearning.InsuranceApplication.repository.InsurancePlanRepository;
import com.mylearning.InsuranceApplication.repository.PlanAgeRuleRepository;
import com.mylearning.InsuranceApplication.service.InsurancePlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsurancePlanServiceImpl implements InsurancePlanService {
    @Autowired
    private InsurancePlanRepository planRepository;
    @Autowired
    private PlanAgeRuleRepository ageRuleRepository;

    public List<InsurancePlan> getAllPlans() {
        return planRepository.findAll();
    }

    public InsurancePlan getPlanById(Long id) {
        return planRepository.findById(id).orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    public double calculatePremium(Long planId, int age, int dependents) {
        List<PlanAgeRule> rules = ageRuleRepository.findByPlanPlanId(planId);

        PlanAgeRule matchingRule = rules.stream()
                .filter(r -> age >= r.getMinAge() && age <= r.getMaxAge())
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No Active Plan"));

        double baseCost = matchingRule.getBaseCost();
        double ageSurcharge = (age - matchingRule.getMinAge()) * matchingRule.getPerYearIncrement();
        double dependentCost = dependents * getPlanById(planId).getCostPerDependent();
        System.out.println("dependentCost: " + dependentCost);

        return baseCost + ageSurcharge + dependentCost;
    }

//    @Override
//    public InsurancePlan addPlan(InsurancePlan plan) {
//        return planRepository.save(plan);
//    }

    @Override
    public InsurancePlan addPlan(InsurancePlanDTO request) {
        InsurancePlan plan = new InsurancePlan();
        plan.setPlanType(InsurancePlan.PlanType.valueOf(request.getPlanType()));
        plan.setPlanName(request.getPlanName());
        plan.setDescription(request.getDescription());
        plan.setCostPerDependent(request.getCostPerDependent());

    // Map age rules
        List<PlanAgeRule> rules = request.getAgeRules().stream().map(r -> {
            PlanAgeRule rule = new PlanAgeRule();
            rule.setMinAge(r.getMinAge());
            rule.setMaxAge(r.getMaxAge());
            rule.setBaseCost(r.getBaseCost());
            rule.setPerYearIncrement(r.getPerYearIncrement());
            rule.setPlan(plan); // link to plan
            return rule;
        }).toList();

        plan.setAgeRules(rules);

        return planRepository.save(plan); // cascade saves the age rules
    }

}
