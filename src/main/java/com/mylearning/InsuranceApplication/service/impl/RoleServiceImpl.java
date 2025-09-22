package com.mylearning.InsuranceApplication.service.impl;

import com.mylearning.InsuranceApplication.entity.Role;
import com.mylearning.InsuranceApplication.repository.RoleRepository;
import com.mylearning.InsuranceApplication.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
