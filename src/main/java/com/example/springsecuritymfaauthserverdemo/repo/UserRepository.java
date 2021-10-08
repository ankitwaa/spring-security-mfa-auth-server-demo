package com.example.springsecuritymfaauthserverdemo.repo;

import com.example.springsecuritymfaauthserverdemo.repo.entity.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDetail, String> {
    UserDetail findByUsername(String username);
}
