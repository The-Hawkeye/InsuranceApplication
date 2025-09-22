package com.mylearning.InsuranceApplication.controller;

import com.mylearning.InsuranceApplication.dto.InsurancePlanDTO;
import com.mylearning.InsuranceApplication.entity.InsurancePlan;
import com.mylearning.InsuranceApplication.service.InsurancePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
public class InsurancePlanController {

    @Autowired
    private InsurancePlanService planService;

    @GetMapping
    public List<InsurancePlan> getPlans() {
        return planService.getAllPlans();
    }

    @GetMapping("/{id}")
    public InsurancePlan getPlan(@PathVariable Long id) {
        return planService.getPlanById(id);
    }

    @GetMapping("/{id}/premium")
    public double calculatePremium(
            @PathVariable Long id,
            @RequestParam int age,
            @RequestParam int dependents
    ) {
        return planService.calculatePremium(id, age, dependents);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public InsurancePlan addPlan(@RequestBody InsurancePlanDTO plan) {
        return planService.addPlan(plan);
    }
}

