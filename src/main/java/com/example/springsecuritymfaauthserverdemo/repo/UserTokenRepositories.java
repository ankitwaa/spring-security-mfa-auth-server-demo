package com.example.springsecuritymfaauthserverdemo.repo;

import com.example.springsecuritymfaauthserverdemo.repo.entity.OtpDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepositories extends CrudRepository<OtpDetail, String> {
    Optional<OtpDetail> findByUsername(String username);
}
