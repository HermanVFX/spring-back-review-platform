package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReviewService {

    ReviewListDto findAllReviews(Pageable pageable);

    ReviewListDto findTobeReviews(Pageable pageable);
    ReviewListDto findPassedReviews(Pageable pageable);
    ReviewListDto findCanceledReviews(Pageable pageable);

    ReviewDto findUserById(UUID reviewId);

    ReviewDto create(ShortReviewDto review);

    ReviewDto createForUser(ShortReviewDto review);

    ReviewDto update(ReviewDto review, UUID reviewId);

    void delete(UUID reviewId);

    ReviewDto closeReviewSession(UUID reviewId, boolean isDone, AuthenticationToken request);

}
