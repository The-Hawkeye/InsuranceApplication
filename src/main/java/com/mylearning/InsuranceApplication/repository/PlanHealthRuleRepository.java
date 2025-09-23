package com.mylearning.InsuranceApplication.repository;

import com.mylearning.InsuranceApplication.entity.InsurancePlan;
import com.mylearning.InsuranceApplication.entity.MedicalInfo;
import com.mylearning.InsuranceApplication.entity.PlanHealthRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanHealthRuleRepository extends JpaRepository<PlanHealthRule, Long> {
    Optional<PlanHealthRule> findByPlan(InsurancePlan plan);
    Optional<MedicalInfo> findByUserIdAndPlanId(Long userId, Long planId);

}
