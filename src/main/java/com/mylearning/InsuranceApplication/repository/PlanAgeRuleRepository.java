package com.mylearning.InsuranceApplication.repository;

import com.mylearning.InsuranceApplication.entity.PlanAgeRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanAgeRuleRepository extends JpaRepository<PlanAgeRule, Long> {
    List<PlanAgeRule> findByPlanPlanId(Long planId);
}
