package com.mylearning.InsuranceApplication.repository;

import com.mylearning.InsuranceApplication.entity.AdminRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRuleRepository extends JpaRepository<AdminRule, Long> {
    List<AdminRule> findByActiveTrue();
}
