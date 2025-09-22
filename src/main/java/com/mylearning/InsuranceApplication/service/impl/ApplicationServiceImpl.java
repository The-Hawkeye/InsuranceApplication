package com.mylearning.InsuranceApplication.service.impl;

import com.mylearning.InsuranceApplication.entity.Application;
import com.mylearning.InsuranceApplication.entity.User;
import com.mylearning.InsuranceApplication.repository.ApplicationRepository;
import com.mylearning.InsuranceApplication.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Application submitApplication(Application application) {
        application.setStatus(Application.ApplicationStatus.PENDING);
        application.setSubmittedAt(LocalDateTime.now());
        return applicationRepository.save(application);
    }

    @Override
    public List<Application> getApplicationsByUser(User user) {
        return applicationRepository.findByPrimarySubscriber(user);
    }

    @Override
    public List<Application> getPendingApplications() {
        return applicationRepository.findByStatus(Application.ApplicationStatus.PENDING);
    }

    @Override
    public Application reviewApplication(Long id, Application.ApplicationStatus status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        application.setReviewedAt(LocalDateTime.now());
        return applicationRepository.save(application);
    }
}
