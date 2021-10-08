package com.example.springsecuritymfaauthserverdemo.controller.dto;

import lombok.Data;

@Data
public class AuthenticateUserTokenRequest extends AuthenticationRequest{
    private String opt;
}
