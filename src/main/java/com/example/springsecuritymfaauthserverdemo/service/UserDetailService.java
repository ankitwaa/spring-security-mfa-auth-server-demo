package com.example.springsecuritymfaauthserverdemo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import javax.security.auth.login.CredentialException;
import java.util.List;

public interface UserDetailService extends UserDetailsService {

    boolean authenticate(String username, String password) throws CredentialException;
    boolean validatedToken(String username, String token) throws CredentialException;
    boolean addUsers(String username, String password, String mobileNumber, List<String> authorities);
    boolean generateToken(String username);

}
