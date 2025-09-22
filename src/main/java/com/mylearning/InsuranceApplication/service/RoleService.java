package com.mylearning.InsuranceApplication.service;

import com.mylearning.InsuranceApplication.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
}

