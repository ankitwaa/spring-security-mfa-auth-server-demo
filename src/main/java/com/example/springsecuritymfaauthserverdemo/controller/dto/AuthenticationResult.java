package com.example.springsecuritymfaauthserverdemo.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResult {

    private String message;
    private String errorMessage;

}
