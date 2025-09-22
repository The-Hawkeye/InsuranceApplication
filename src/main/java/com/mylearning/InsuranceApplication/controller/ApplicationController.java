package com.mylearning.InsuranceApplication.controller;

import com.mylearning.InsuranceApplication.entity.Application;
import com.mylearning.InsuranceApplication.entity.User;
import com.mylearning.InsuranceApplication.service.ApplicationService;
import com.mylearning.InsuranceApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Application submitApplication(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Application application) {

        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        application.setPrimarySubscriber(user);
        return applicationService.submitApplication(application);
    }

    @GetMapping("/my")
    public List<Application> getMyApplications(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return applicationService.getApplicationsByUser(user);
    }

    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Application> getPendingApplications() {
        return applicationService.getPendingApplications();
    }

    @PostMapping("/{id}/review")
    @PreAuthorize("hasRole('ADMIN')")
    public Application reviewApplication(
            @PathVariable Long id,
            @RequestParam Application.ApplicationStatus status) {
        return applicationService.reviewApplication(id, status);
    }
}

