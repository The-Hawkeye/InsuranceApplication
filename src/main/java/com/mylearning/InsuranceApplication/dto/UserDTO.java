package com.mylearning.InsuranceApplication.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private Integer age;
}
