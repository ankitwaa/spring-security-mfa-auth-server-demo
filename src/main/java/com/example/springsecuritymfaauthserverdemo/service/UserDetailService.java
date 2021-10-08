package com.example.springsecuritymfaauthserverdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import javax.security.auth.login.CredentialException;

public interface UserDetailService extends UserDetailsService {

    boolean authenticate(String username, String password) throws CredentialException;
    boolean validatedToken(String username, String token) throws CredentialException;
    boolean addUsers(String username, String password, String mobileNumber, String ...authorities);
    boolean generateToken(String username);

}
