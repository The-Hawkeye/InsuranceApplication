package com.mylearning.InsuranceApplication.service;

import com.mylearning.InsuranceApplication.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> findByUsername(String username);
}
