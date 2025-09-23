package com.mylearning.InsuranceApplication.controller;

import com.mylearning.InsuranceApplication.entity.InsurancePlan;
import com.mylearning.InsuranceApplication.entity.PlanAgeRule;
import com.mylearning.InsuranceApplication.entity.PlanHealthRule;
import com.mylearning.InsuranceApplication.repository.InsurancePlanRepository;
import com.mylearning.InsuranceApplication.repository.PlanAgeRuleRepository;
import com.mylearning.InsuranceApplication.repository.PlanHealthRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/plans")
@PreAuthorize("hasRole('ADMIN')")
public class AdminRuleController {

    @Autowired
    private InsurancePlanRepository planRepository;

    @Autowired
    private PlanHealthRuleRepository healthRuleRepository;

    @Autowired
    private PlanAgeRuleRepository ageRuleRepository;

    // ------------------- Plan Health Rules -------------------
    @PostMapping("/{planId}/health-rules")
    public PlanHealthRule setPlanHealthRules(@PathVariable Long planId,
                                             @RequestBody PlanHealthRule rules) {
        InsurancePlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        rules.setPlan(plan);
        return healthRuleRepository.save(rules);
    }

    @GetMapping("/{planId}/health-rules")
    public PlanHealthRule getPlanHealthRules(@PathVariable Long planId) {
        InsurancePlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        return healthRuleRepository.findByPlan(plan)
                .orElseThrow(() -> new RuntimeException("Health rules not found for plan"));
    }

    // ------------------- Plan Age Rules -------------------
    @PostMapping("/{planId}/age-rules")
    public List<PlanAgeRule> setPlanAgeRules(@PathVariable Long planId,
                                             @RequestBody List<PlanAgeRule> ageRules) {
        InsurancePlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // Link each rule to the plan
        for (PlanAgeRule rule : ageRules) {
            rule.setPlan(plan);
        }

        // Save all
        return ageRuleRepository.saveAll(ageRules);
    }

    @GetMapping("/{planId}/age-rules")
    public List<PlanAgeRule> getPlanAgeRules(@PathVariable Long planId) {
        InsurancePlan plan = planRepository.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        return ageRuleRepository.findByPlanPlanId(plan.getPlanId());
    }
}
