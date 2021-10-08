package com.example.springsecuritymfaauthserverdemo.repo;

import com.example.springsecuritymfaauthserverdemo.repo.entity.GrantAuthority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGrantRepository extends CrudRepository<GrantAuthority, String> {
}
