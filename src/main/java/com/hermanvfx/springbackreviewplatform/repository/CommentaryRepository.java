package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.Commentary;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentaryRepository extends CrudRepository<Commentary, UUID> {
}