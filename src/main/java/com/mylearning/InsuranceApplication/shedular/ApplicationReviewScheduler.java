package com.mylearning.InsuranceApplication.shedular;
//
//import com.mylearning.InsuranceApplication.entity.Application;
//
//
//import com.mylearning.InsuranceApplication.repository.ApplicationRepository;
//import com.mylearning.InsuranceApplication.service.ApplicationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class ApplicationReviewScheduler {
//
//    @Autowired
//    private ApplicationService applicationService;
//
//    @Autowired
//    private ApplicationRepository applicationRepository;
//
//    // Runs every 4 hours
//    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
//    public void reviewPendingApplications() {
//        List<Application> pendingApplications = applicationRepository.findByStatus(Application.ApplicationStatus.PENDING);
//
//        for (Application application : pendingApplications) {
//            boolean approved = applicationService.applyRules(application);
//
//            if (approved) {
//                application.setStatus(Application.ApplicationStatus.APPROVED);
//            } else {
//                application.setStatus(Application.ApplicationStatus.REJECTED);
//            }
//            application.setReviewedAt(java.time.LocalDateTime.now());
//            applicationRepository.save(application);
//        }
//    }
//}


import com.mylearning.InsuranceApplication.entity.Application;
import com.mylearning.InsuranceApplication.entity.MedicalInfo;
import com.mylearning.InsuranceApplication.entity.PlanAgeRule;
import com.mylearning.InsuranceApplication.entity.PlanHealthRule;
import com.mylearning.InsuranceApplication.service.AdminRuleService;
import com.mylearning.InsuranceApplication.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ApplicationReviewScheduler {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private AdminRuleService adminRuleService; // service to fetch PlanHealthRules

    /**
     * Run every 4 hours to auto-review pending applications.
     */
    @Scheduled(fixedRate = 4 * 60 * 60 * 1000) // every 4 hours
    public void reviewPendingApplications() {
        List<Application> pendingApplications = applicationService.getPendingApplications();

        for (Application app : pendingApplications) {
            boolean approved = evaluateApplication(app);
            app.setStatus(approved ? Application.ApplicationStatus.APPROVED : Application.ApplicationStatus.REJECTED);
            app.setReviewedAt(LocalDateTime.now());
            applicationService.saveApplication(app);
        }
    }

//    /**
//     * Evaluate a single application based on plan health rules.
//     */
//    private boolean evaluateApplication(Application app) {
//        PlanHealthRule rules = adminRuleService.getPlanHealthRules(app.getInsurancePlan().getPlanId());
//
//        // Check primary subscriber
//        MedicalInfo primaryMedical = app.getPrimarySubscriberMedicalInfos(); // should be fetched properly
//        if (primaryMedical != null && !checkAgainstRules(rules, primaryMedical)) {
//            return false;
//        }
//
//        // Check all dependents
//        if (app.getDependents() != null) {
//            for (var dep : app.getDependents()) {
//                if (dep.getMedicalInfos() != null) {
//                    for (MedicalInfo depMedical : dep.getMedicalInfos()) {
//                        if (!checkAgainstRules(rules, depMedical)) {
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//
//        return true; // passed all checks
//    }

    private boolean evaluateApplication(Application app) {
        PlanHealthRule healthRules = adminRuleService.getPlanHealthRules(app.getInsurancePlan().getPlanId());
        List<PlanAgeRule> ageRules = adminRuleService.getPlanAgeRules(app.getInsurancePlan().getPlanId());

        // ------------------
        // 1. Primary subscriber
        // ------------------
        int primaryAge = app.getPrimarySubscriber().getAge();
        if (!checkAgeEligibility(primaryAge, ageRules)) return false;

        MedicalInfo primaryInfo = medicalInfoRepository
                .findByUserIdAndPlanId(app.getPrimarySubscriber().getId(), app.getInsurancePlan().getPlanId())
                .orElse(null);

        if (primaryInfo == null || !checkMedicalEligibility(healthRules, primaryInfo)) return false;

        // ------------------
        // 2. Dependents
        // ------------------
        if (app.getDependents() != null) {
            for (Dependent dep : app.getDependents()) {
                // Age check
                if (!checkAgeEligibility(dep.getAge(), ageRules)) return false;

                // Medical info for dependent (from flat medicalInfos list)
                MedicalInfo depInfo = app.getMedicalInfos().stream()
                        .filter(mi -> mi.getDependent() != null && mi.getDependent().getId().equals(dep.getId()))
                        .findFirst()
                        .orElse(null);

                if (depInfo == null) {
                    // No info → mark for review or skip
                    return false; // or return true if you want to approve anyway
                }

                if (!checkMedicalEligibility(healthRules, depInfo)) return false;
            }
        }

        return true; // passed both medical + age rules
    }

    private boolean checkAgeEligibility(int age, List<PlanAgeRule> ageRules) {
        for (PlanAgeRule rule : ageRules) {
            if (age >= rule.getMinAge() && age <= rule.getMaxAge()) {
                return rule.isAllowed();
            }
        }
        return false; // if no matching rule found
    }

    private boolean checkMedicalEligibility(PlanHealthRule rules, MedicalInfo info) {
        if (!rules.isSmokingOrAlcoholAllowed() && info.isSmokingOrAlcohol()) return false;
        if (!rules.isRecentHospitalizationAllowed() && info.isRecentHospitalization()) return false;
        if (!rules.isPreviousClaimsAllowed() && info.isPreviousClaims()) return false;
        if (!rules.isProposalDeclinedAllowed() && info.isProposalDeclined()) return false;
        if (!rules.isOtherInsuranceAllowed() && info.isOtherInsurance()) return false;
        if (!rules.isDiabetesAllowed() && info.isDiabetes()) return false;
        if (!rules.isHypertensionAllowed() && info.isHypertension()) return false;
        if (!rules.isCancerAllowed() && info.isCancer()) return false;
        if (!rules.isAsthmaAllowed() && info.isAsthma()) return false;
        if (!rules.isHeartDiseaseAllowed() && info.isHeartDisease()) return false;

        return true;
    }



    /**
     * Compare a single MedicalInfo against the PlanHealthRule.
     */
    private boolean checkAgainstRules(PlanHealthRule rules, MedicalInfo info) {
        if (!rules.isSmokingOrAlcoholAllowed() && info.isSmokingOrAlcohol()) return false;
        if (!rules.isRecentHospitalizationAllowed() && info.isRecentHospitalization()) return false;
        if (!rules.isPreviousClaimsAllowed() && info.isPreviousClaims()) return false;
        if (!rules.isProposalDeclinedAllowed() && info.isProposalDeclined()) return false;
        if (!rules.isOtherInsuranceAllowed() && info.isOtherInsurance()) return false;
        if (!rules.isDiabetesAllowed() && info.isDiabetes()) return false;
        if (!rules.isHypertensionAllowed() && info.isHypertension()) return false;
        if (!rules.isCancerAllowed() && info.isCancer()) return false;
        if (!rules.isAsthmaAllowed() && info.isAsthma()) return false;
        if (!rules.isHeartDiseaseAllowed() && info.isHeartDisease()) return false;

        return true;
    }


}
