package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
