package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID> {
    List<Review> findAllByReviewerId(UUID reviewerId);
    List<Review> findAll();
}
