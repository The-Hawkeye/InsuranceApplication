package com.mylearning.InsuranceApplication.repository;

import com.mylearning.InsuranceApplication.entity.InsurancePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePlanRepository extends JpaRepository<InsurancePlan, Long> {
}
