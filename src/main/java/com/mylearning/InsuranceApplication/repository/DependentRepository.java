package com.mylearning.InsuranceApplication.repository;

import com.mylearning.InsuranceApplication.entity.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long> {
}
