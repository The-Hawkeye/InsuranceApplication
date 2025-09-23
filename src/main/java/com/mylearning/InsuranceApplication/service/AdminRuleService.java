package com.mylearning.InsuranceApplication.service;

import com.mylearning.InsuranceApplication.entity.PlanAgeRule;
import com.mylearning.InsuranceApplication.entity.PlanHealthRule;

import java.util.List;

public interface AdminRuleService {

    // ---------------- Health Rules ----------------
    PlanHealthRule setPlanHealthRules(Long planId, PlanHealthRule rules);
    PlanHealthRule getPlanHealthRules(Long planId);

    // ---------------- Age Rules ----------------
    List<PlanAgeRule> setPlanAgeRules(Long planId, List<PlanAgeRule> ageRules);
    List<PlanAgeRule> getPlanAgeRules(Long planId);
}
