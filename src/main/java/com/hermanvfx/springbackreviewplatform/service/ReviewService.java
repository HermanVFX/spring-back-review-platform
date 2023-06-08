package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReviewService {

    ReviewDtoPage findAllReviews(Pageable pageable);

    ReviewDtoPage findAllReviewsByUser(Pageable pageable, UUID userId);

    ReviewDtoPage findTobeReviews(Pageable pageable);

    ReviewDtoPage findPassedReviews(Pageable pageable);

    ReviewDtoPage findCanceledReviews(Pageable pageable);

    ReviewDto findReviewById(UUID reviewId);

    ReviewDto create(ShortReviewDto review, String token);

    ReviewDto createForUser(ShortReviewDto review, String token);

    ReviewDto update(ReviewDto review, UUID reviewId);

    void delete(UUID id);

    void deleteFromBd(UUID id);

    ReviewDto closeReviewSession(UUID reviewId, boolean isDone, AuthenticationToken request);

}
