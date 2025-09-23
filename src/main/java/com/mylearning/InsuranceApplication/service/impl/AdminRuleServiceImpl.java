package com.mylearning.InsuranceApplication.service.impl;

import com.mylearning.InsuranceApplication.entity.InsurancePlan;
import com.mylearning.InsuranceApplication.entity.PlanAgeRule;
import com.mylearning.InsuranceApplication.entity.PlanHealthRule;
import com.mylearning.InsuranceApplication.repository.InsurancePlanRepository;
import com.mylearning.InsuranceApplication.repository.PlanAgeRuleRepository;
import com.mylearning.InsuranceApplication.repository.PlanHealthRuleRepository;
import com.mylearning.InsuranceApplication.service.AdminRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRuleServiceImpl implements AdminRuleService {

    @Autowired
    private InsurancePlanRepository planRepository;

    @Autowired
    private PlanHealthRuleRepository healthRuleRepository;

    @Autowired
    private PlanAgeRuleRepository ageRuleRepository;

    // ---------------- Health Rules ----------------
    @Override
    public PlanHealthRule setPlanHealthRules(Long planId, PlanHealthRule rules) {
        InsurancePlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        rules.setPlan(plan);
        return healthRuleRepository.save(rules);
    }

    @Override
    public PlanHealthRule getPlanHealthRules(Long planId) {
        InsurancePlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        return healthRuleRepository.findByPlan(plan)
                .orElseThrow(() -> new RuntimeException("Health rules not found for plan"));
    }

    // ---------------- Age Rules ----------------
    @Override
    public List<PlanAgeRule> setPlanAgeRules(Long planId, List<PlanAgeRule> ageRules) {
        InsurancePlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // Link each rule to the plan
        for (PlanAgeRule rule : ageRules) {
            rule.setPlan(plan);
        }

        return ageRuleRepository.saveAll(ageRules);
    }

    @Override
    public List<PlanAgeRule> getPlanAgeRules(Long planId) {
        InsurancePlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        return ageRuleRepository.findByPlanPlanId(plan.getPlanId());
    }

}
