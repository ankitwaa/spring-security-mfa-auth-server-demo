package com.example.springsecuritymfaauthserverdemo.repo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Table(name = "otp_details")
@Entity
public class OtpDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "otp")
    private String otp;
    @Column(name = "valid_dtm")
    private LocalDateTime valid_dtm;
}
