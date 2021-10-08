package com.example.springsecuritymfaauthserverdemo.controller;

import com.example.springsecuritymfaauthserverdemo.controller.dto.AddUserRequest;
import com.example.springsecuritymfaauthserverdemo.controller.dto.AuthenticateUserTokenRequest;
import com.example.springsecuritymfaauthserverdemo.controller.dto.AuthenticationRequest;
import com.example.springsecuritymfaauthserverdemo.controller.dto.AuthenticationResult;
import com.example.springsecuritymfaauthserverdemo.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
import java.util.Optional;

@RestController
public class UserAuthenticationController {

    private UserDetailService userDetailService;

    @Autowired
    public void setUserDetailService(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping("/user/authenticate")
    public ResponseEntity<AuthenticationResult> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws CredentialException {
       boolean success = userDetailService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
       AuthenticationResult authenticationResult = new AuthenticationResult();
       if(success){
           authenticationResult.setMessage("Authenticated!");
       }else {
           authenticationResult.setMessage("Failed to Authenticate User");
       }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationResult);
    }

    @PostMapping("/user/token/validate")
    public ResponseEntity<AuthenticationResult> validateToken(@RequestBody AuthenticateUserTokenRequest authenticationRequest) throws CredentialException {
        boolean success = userDetailService.validatedToken(authenticationRequest.getUsername(), authenticationRequest.getOpt());
        AuthenticationResult authenticationResult = new AuthenticationResult();
        if(success){
            authenticationResult.setMessage("Authenticated!");
        }else {
            authenticationResult.setMessage("Failed to Authenticate User");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationResult);
    }

    @PostMapping("/user/add/")
    public ResponseEntity<AuthenticationResult> validateToken(@RequestBody AddUserRequest addUserRequest) throws CredentialException {
        boolean success = userDetailService.addUsers(addUserRequest.getUsername(), addUserRequest.getPassword(), addUserRequest.getMobile(), addUserRequest.getAuthorities());
        AuthenticationResult authenticationResult = new AuthenticationResult();
        if(success){
            authenticationResult.setMessage("Authenticated!");
        }else {
            authenticationResult.setMessage("Failed to Authenticate User");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationResult);
    }

}
