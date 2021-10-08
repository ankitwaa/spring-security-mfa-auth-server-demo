package com.example.springsecuritymfaauthserverdemo.repo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Table(name = "user_grant_details")
@Entity
public class GrantAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name="authority")
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
