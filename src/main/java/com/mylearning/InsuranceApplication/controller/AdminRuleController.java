package com.mylearning.InsuranceApplication.controller;

import com.mylearning.InsuranceApplication.entity.AdminRule;
import com.mylearning.InsuranceApplication.service.AdminRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rules")
public class AdminRuleController {

    @Autowired
    private AdminRuleService ruleService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public AdminRule createRule(@RequestBody AdminRule rule) {
        return ruleService.createRule(rule);
    }

    @GetMapping("/active")
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdminRule> getActiveRules() {
        return ruleService.getActiveRules();
    }
}
