package com.mylearning.InsuranceApplication.repository;

import com.mylearning.InsuranceApplication.entity.Application;
import com.mylearning.InsuranceApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByPrimarySubscriber(User user);
    List<Application> findByStatus(Application.ApplicationStatus status);
}

