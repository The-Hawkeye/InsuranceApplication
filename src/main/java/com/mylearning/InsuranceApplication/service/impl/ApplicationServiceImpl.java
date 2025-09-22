package com.mylearning.InsuranceApplication.service.impl;

import com.mylearning.InsuranceApplication.dto.ApplicationRequest;
import com.mylearning.InsuranceApplication.entity.Application;
import com.mylearning.InsuranceApplication.entity.Dependent;
import com.mylearning.InsuranceApplication.entity.InsurancePlan;
import com.mylearning.InsuranceApplication.entity.User;
import com.mylearning.InsuranceApplication.repository.ApplicationRepository;
import com.mylearning.InsuranceApplication.repository.DependentRepository;
import com.mylearning.InsuranceApplication.repository.InsurancePlanRepository;
import com.mylearning.InsuranceApplication.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private InsurancePlanRepository planRepository;

    @Autowired
    private DependentRepository dependentRepository;


    public Application submitApplication(ApplicationRequest request, User user) {
        InsurancePlan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        Application application = new Application();
        application.setPrimarySubscriber(user);
        application.setInsurancePlan(plan);
        application.setStatus(Application.ApplicationStatus.PENDING);
        application.setSubmittedAt(LocalDateTime.now());

        // Map dependents from DTO
        List<Dependent> dependents = request.getDependents().stream()
                .map(d -> {
                    Dependent dep = new Dependent();
                    dep.setName(d.getName());
                    dep.setAge(d.getAge());
                    dep.setRelation(d.getRelation());
                    dep.setApplication(application);
                    return dep;
                })
                .toList();


        application.setDependents(dependents);

        // Calculate premium (example: baseCost + dependents cost)
        double premium = plan.getCostPerDependent() * dependents.size();
        application.setPremium(premium);

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
