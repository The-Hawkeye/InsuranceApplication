package com.mylearning.InsuranceApplication.service.impl;

import com.mylearning.InsuranceApplication.entity.Dependent;
import com.mylearning.InsuranceApplication.repository.DependentRepository;
import com.mylearning.InsuranceApplication.service.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DependentServiceImpl implements DependentService {

    @Autowired
    private DependentRepository dependentRepository;

    @Override
    public Dependent addDependent(Dependent dependent) {
        return dependentRepository.save(dependent);
    }
}

