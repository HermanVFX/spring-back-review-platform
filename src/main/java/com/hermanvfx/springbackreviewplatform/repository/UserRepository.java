package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findAll();

    Optional<User> findByEmail(String email);
}
