package com.mylearning.InsuranceApplication.util;

import com.mylearning.InsuranceApplication.entity.AdminRule;
import com.mylearning.InsuranceApplication.entity.Application;
import com.mylearning.InsuranceApplication.entity.Dependent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Helper class to evaluate admin rules for applications.
 * Can be used in Scheduler or Services.
 */
@Component
@Slf4j
public class RuleEvaluationHelper {

    /**
     * Evaluates a single application against a list of active rules.
     * Returns true if the application passes all rules (approved), false otherwise.
     */
    public boolean evaluate(Application app, List<AdminRule> rules) {
        for (AdminRule rule : rules) {
            boolean passed = evaluateRule(app, rule);
            if (!passed) {
//                log.info("Application {} failed rule {}", app.getId(), rule.getRuleName());
                return false;
            }
        }
        return true;
    }

    /**
     * Evaluate a single rule for a single application
     */
    private boolean evaluateRule(Application app, AdminRule rule) {
//        String type = rule.getRuleType();  // e.g., "MAX_DEPENDENTS", "MIN_AGE", etc.
//        double threshold = rule.getThresholdValue();

//        switch (type) {
//            case "MAX_DEPENDENTS":
//                List<Dependent> dependents = app.getDependents();
//                int depCount = (dependents != null) ? dependents.size() : 0;
//                return depCount <= threshold;
//
//            case "MIN_AGE":
//                int age = app.getPrimarySubscriber().getAge();
//                return age >= threshold;
//
//            case "MAX_AGE":
//                int maxAge = app.getPrimarySubscriber().getAge();
//                return maxAge <= threshold;
//
//            case "PLAN_COST_LIMIT":
//                double premium = app.getPremium() != null ? app.getPremium() : 0;
//                return premium <= threshold;
//
//            default:
//                log.warn("Unknown rule type: {}", type);
//                return true; // if unknown, ignore rule
//        }
        return true;
    }
}

