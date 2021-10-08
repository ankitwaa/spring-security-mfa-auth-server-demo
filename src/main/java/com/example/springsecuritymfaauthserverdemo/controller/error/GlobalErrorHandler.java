package com.example.springsecuritymfaauthserverdemo.controller.error;

import com.example.springsecuritymfaauthserverdemo.controller.dto.AuthenticationResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.CredentialException;

@ControllerAdvice
public class GlobalErrorHandler{

    @ExceptionHandler(value = CredentialException.class)
    public ResponseEntity<AuthenticationResult> handleException(Exception exception){
        AuthenticationResult authenticationResult = new AuthenticationResult();
        authenticationResult.setErrorMessage(exception.getMessage());
        return ResponseEntity.status(400).body(authenticationResult);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<AuthenticationResult> handleExceptionGeneric(Exception exception){
        AuthenticationResult authenticationResult = new AuthenticationResult();
        authenticationResult.setErrorMessage(exception.getMessage());
        return ResponseEntity.status(400).body(authenticationResult);
    }
}
