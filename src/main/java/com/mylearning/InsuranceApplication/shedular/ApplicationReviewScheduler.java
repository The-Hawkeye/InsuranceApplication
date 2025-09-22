package com.mylearning.InsuranceApplication.shedular;

import com.mylearning.InsuranceApplication.entity.AdminRule;
import com.mylearning.InsuranceApplication.entity.Application;
import com.mylearning.InsuranceApplication.repository.AdminRuleRepository;
import com.mylearning.InsuranceApplication.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationReviewScheduler {

    private ApplicationRepository applicationRepository;
    private AdminRuleRepository adminRuleRepository;

    @Scheduled(cron = "0 0 */4 * * *")
    public void reviewApplications() {
//        log.info("Scheduler triggered - Reviewing pending applications...");

        List<Application> pendingApps = applicationRepository.findByStatus(Application.ApplicationStatus.PENDING);
        List<AdminRule> rules = adminRuleRepository.findByActiveTrue();

        for (Application app : pendingApps) {
            boolean approved = applyRules(app, rules);
            if (approved) {
                app.setStatus(Application.ApplicationStatus.APPROVED);
//                log.info("Application {} auto-approved.", app.getId());
            } else {
                app.setStatus(Application.ApplicationStatus.REJECTED);
//                log.info("Application {} auto-rejected.", app.getId());
            }
            applicationRepository.save(app);
        }
    }

    /**
     * Apply business rules for approval/rejection
     * For simplicity: we just check numeric threshold type rules
     */
    private boolean applyRules(Application app, List<AdminRule> rules) {
//        for (AdminRule rule : rules) {
//            switch (rule.getRuleType()) {
//                case "MAX_DEPENDENTS":
//                    if (app.getDependents() != null &&
//                            app.getDependents().size() > rule.getThresholdValue()) {
//                        return false;
//                    }
//                    break;
//                case "MIN_AGE":
//                    if (app.getPrimarySubscriber().getAge() < rule.getThresholdValue()) {
//                        return false;
//                    }
//                    break;
//                case "MAX_AGE":
//                    if (app.getPrimarySubscriber().getAge() > rule.getThresholdValue()) {
//                        return false;
//                    }
//                    break;
//                default:
//                    log.warn("Unknown rule type: {}", rule.getRuleType());
//            }
//        }
        return true;
    }
}
