package com.mylearning.InsuranceApplication.service;


import com.mylearning.InsuranceApplication.dto.ApplicationRequest;
import com.mylearning.InsuranceApplication.entity.Application;
import com.mylearning.InsuranceApplication.entity.User;

import java.util.List;

public interface ApplicationService {
    Application submitApplication(ApplicationRequest application, User user);
    List<Application> getApplicationsByUser(User user);
    List<Application> getPendingApplications();
    Application reviewApplication(Long id, Application.ApplicationStatus status);
}
