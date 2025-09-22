package com.mylearning.InsuranceApplication.service;

import com.mylearning.InsuranceApplication.entity.AdminRule;

import java.util.List;

public interface AdminRuleService {
    AdminRule createRule(AdminRule rule);
    List<AdminRule> getActiveRules();
}
