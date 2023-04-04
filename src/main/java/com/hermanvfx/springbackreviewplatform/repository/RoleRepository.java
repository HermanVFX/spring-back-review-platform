package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
    Optional<Role> findByRole(String role);
}
