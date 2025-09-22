package com.mylearning.InsuranceApplication.service.impl;

import com.mylearning.InsuranceApplication.entity.AdminRule;
import com.mylearning.InsuranceApplication.repository.AdminRuleRepository;
import com.mylearning.InsuranceApplication.service.AdminRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRuleServiceImpl implements AdminRuleService {

    @Autowired
    private AdminRuleRepository ruleRepository;

    @Override
    public AdminRule createRule(AdminRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public List<AdminRule> getActiveRules() {
        return ruleRepository.findByActiveTrue();
    }
}

