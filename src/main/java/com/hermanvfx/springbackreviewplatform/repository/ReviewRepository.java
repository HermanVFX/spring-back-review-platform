package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID> {
}
