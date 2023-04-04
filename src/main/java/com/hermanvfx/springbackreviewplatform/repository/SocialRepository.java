package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.Social;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SocialRepository extends CrudRepository<Social, UUID> {
}
