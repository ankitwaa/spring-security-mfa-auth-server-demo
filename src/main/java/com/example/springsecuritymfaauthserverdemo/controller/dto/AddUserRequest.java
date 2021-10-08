package com.example.springsecuritymfaauthserverdemo.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddUserRequest {
    private String username;
    private String password;
    private String mobile;
    private String enabled;
    private String passwordExpired;
    private String accountExpired;
    private String accountLocked;
    private List<String> authorities;
}
