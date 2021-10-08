package com.example.springsecuritymfaauthserverdemo.service.impl;

import com.example.springsecuritymfaauthserverdemo.repo.UserRepository;
import com.example.springsecuritymfaauthserverdemo.repo.UserTokenRepositories;
import com.example.springsecuritymfaauthserverdemo.repo.entity.GrantAuthority;
import com.example.springsecuritymfaauthserverdemo.repo.entity.OtpDetail;
import com.example.springsecuritymfaauthserverdemo.repo.entity.UserDetail;
import com.example.springsecuritymfaauthserverdemo.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    private UserRepository userRepository;
    private UserTokenRepositories userTokenRepositories;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserTokenRepositories(UserTokenRepositories userTokenRepositories) {
        this.userTokenRepositories = userTokenRepositories;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean authenticate(String username, String password) throws CredentialException {
        UserDetail userDetail = userRepository.findByUsername(username);
        if (passwordEncoder.matches(password, userDetail.getPassword())) {
            return true;
        } else {
            throw new BadCredentialsException("Bad Credential Exception");
        }
    }

    @Override
    public boolean validatedToken(String username, String token) throws CredentialException {
        OtpDetail otpDetail = userTokenRepositories.findByUsername(username).orElseThrow(() -> new BadCredentialsException("Bad Credential Exception"));
        if (token.equalsIgnoreCase(otpDetail.getOtp())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addUsers(String username, String password, String mobileNumber, List<String> authorities) {
        UserDetail userDetail = UserDetail.builder().username(username).password(password).mobile(mobileNumber)
                .accountLocked("N")
                .accountNotExpired("N")
                .credentialExpired("N")
                .enabled("Y")
                .authorities(authorities.stream().map((s) -> {
                    GrantAuthority grantAuthority = new GrantAuthority();
                    grantAuthority.setAuthority(s);
                    grantAuthority.setUsername(username);
                    return grantAuthority;
                }).collect(Collectors.toList())).build();
        userRepository.save(userDetail);
        return true;
    }

    @Override
    public boolean generateToken(String username) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            String token = String.valueOf(secureRandom.nextInt(9000) + 1000);

            OtpDetail otpDetail = userTokenRepositories.findByUsername(username).orElse(new OtpDetail());
            otpDetail.setOtp(token);
            otpDetail.setUsername(username);
            otpDetail.setValid_dtm(LocalDateTime.now().plusMinutes(30));
            userTokenRepositories.save(otpDetail);
        } catch (Exception exception) {
            throw new RuntimeException("Error While Generating OTP for user -" + username);
        }
        return true;
    }
}
