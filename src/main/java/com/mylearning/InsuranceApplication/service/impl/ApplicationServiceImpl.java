package com.mylearning.InsuranceApplication.service.impl;

import com.mylearning.InsuranceApplication.dto.ApplicationRequest;
import com.mylearning.InsuranceApplication.entity.*;
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


//    public Application submitApplication(ApplicationRequest request, User user) {
//        InsurancePlan plan = planRepository.findById(request.getPlanId())
//                .orElseThrow(() -> new RuntimeException("Plan not found"));
//
//        Application application = new Application();
//        application.setPrimarySubscriber(user);
//        application.setInsurancePlan(plan);
//        application.setStatus(Application.ApplicationStatus.PENDING);
//        application.setSubmittedAt(LocalDateTime.now());
//
//        // Map dependents from DTO
//        List<Dependent> dependents = request.getDependents().stream()
//                .map(d -> {
//                    Dependent dep = new Dependent();
//                    dep.setName(d.getName());
//                    dep.setAge(d.getAge());
//                    dep.setRelation(d.getRelation());
//                    dep.setApplication(application);
//                    return dep;
//                })
//                .toList();
//
//
//        application.setDependents(dependents);
//
//        // Calculate premium (example: baseCost + dependents cost)
//        double premium = plan.getCostPerDependent() * dependents.size();
//        application.setPremium(premium);
//
//        return applicationRepository.save(application);
//    }


    @Override
    public Application submitApplication(ApplicationRequest request, User user) {
        // 1️⃣ Fetch the selected insurance plan
        InsurancePlan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // 2️⃣ Create the Application entity
        Application application = new Application();
        application.setPrimarySubscriber(user);
        application.setInsurancePlan(plan);
        application.setStatus(Application.ApplicationStatus.PENDING);
        application.setSubmittedAt(LocalDateTime.now());

        // 3️⃣ Map applicant medical info
        if (request.getApplicant() != null && request.getApplicant().getMedicalInfo() != null) {
            MedicalInfo applicantMedicalInfo = mapMedicalInfo(request.getApplicant().getMedicalInfo());
            applicantMedicalInfo.setApplication(application);
            applicantMedicalInfo.setDependent(null); // belongs to primary applicant
            applicantMedicalInfo.setActive(true);
            // If Application has a list of medicalInfos for applicant
            application.setMedicalInfos(List.of(applicantMedicalInfo));
        }

        // 4️⃣ Map dependents + their medical info
        List<Dependent> dependents = request.getDependents().stream()
                .map(d -> {
                    Dependent dep = new Dependent();
                    dep.setName(d.getName());
                    dep.setAge(d.getAge());
                    dep.setRelation(d.getRelation());
                    dep.setApplication(application);

                    if (d.getMedicalInfo() != null) {
                        MedicalInfo depMedicalInfo = mapMedicalInfo(d.getMedicalInfo());
                        depMedicalInfo.setDependent(dep);
                        depMedicalInfo.setApplication(application);
                        depMedicalInfo.setActive(true);
                        dep.setMedicalInfos(List.of(depMedicalInfo));
                    }

                    return dep;
                })
                .toList();

        application.setDependents(dependents);

        // 5️⃣ Calculate premium (simple example, customize as needed)
        double premium = plan.getCostPerDependent() * dependents.size();
        application.setPremium(premium);

        // 6️⃣ Save the application (cascades will save dependents + medical info)
        return applicationRepository.save(application);
    }

//    // ✅ Helper method to map DTO to entity
//    private MedicalInfo mapMedicalInfo(ApplicationRequest.MedicalInfoDto dto) {
//        return MedicalInfo.builder()
//                .smokingOrAlcohol(dto.isSmokingOrAlcohol())
//                .recentHospitalization(dto.isRecentHospitalization())
//                .previousClaims(dto.isPreviousClaims())
//                .proposalDeclined(dto.isProposalDeclined())
//                .otherInsurance(dto.isOtherInsurance())
//                .diabetes(dto.isDiabetes())
//                .hypertension(dto.isHypertension())
//                .cancer(dto.isCancer())
//                .asthma(dto.isAsthma())
//                .heartDisease(dto.isHeartDisease())
//                .build();
//    }

    // Helper method to map DTO to entity (without Lombok builder)
    private MedicalInfo mapMedicalInfo(ApplicationRequest.MedicalInfoDto dto) {
        MedicalInfo medicalInfo = new MedicalInfo();
        medicalInfo.setSmokingOrAlcohol(dto.isSmokingOrAlcohol());
        medicalInfo.setRecentHospitalization(dto.isRecentHospitalization());
        medicalInfo.setPreviousClaims(dto.isPreviousClaims());
        medicalInfo.setProposalDeclined(dto.isProposalDeclined());
        medicalInfo.setOtherInsurance(dto.isOtherInsurance());
        medicalInfo.setDiabetes(dto.isDiabetes());
        medicalInfo.setHypertension(dto.isHypertension());
        medicalInfo.setCancer(dto.isCancer());
        medicalInfo.setAsthma(dto.isAsthma());
        medicalInfo.setHeartDisease(dto.isHeartDisease());
        return medicalInfo;
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

    @Override
    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }


}
