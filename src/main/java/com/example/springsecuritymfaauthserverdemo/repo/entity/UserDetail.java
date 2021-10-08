package com.example.springsecuritymfaauthserverdemo.repo.entity;

import lombok.Builder;
import org.hibernate.annotations.Formula;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Builder
@Table(name = "user_details")
@Entity
public class UserDetail implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "is_enabled")
    private String enabled;
    @Column(name = "is_account_locked")
    private String accountLocked;
    @Column(name = "is_credential_expired")
    private String credentialExpired;
    @Column(name = "is_account_expired")
    private String accountNotExpired;

    @OneToMany
    private List<GrantAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNotExpired.equalsIgnoreCase("N");
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLocked.equalsIgnoreCase("N");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialExpired.equalsIgnoreCase("N");
    }

    @Override
    public boolean isEnabled() {
        return enabled.equalsIgnoreCase("Y");
    }
}
