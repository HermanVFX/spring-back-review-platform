package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.AuthenticationToken;
import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewListDto;
import com.example.userservice.dto.ShortReviewDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReviewService {

    ReviewListDto findAllReviews(Pageable pageable);

    ReviewListDto findAllReviewsByUser(Pageable pageable, UUID userId);

    ReviewListDto findTobeReviews(Pageable pageable);

    ReviewListDto findPassedReviews(Pageable pageable);

    ReviewListDto findCanceledReviews(Pageable pageable);

    ReviewDto findReviewById(UUID reviewId);

    ReviewDto create(ShortReviewDto review, String token);

    ReviewDto createForUser(ShortReviewDto review, String token);

    ReviewDto update(ReviewDto review, UUID reviewId);

    void delete(UUID id);

    void deleteFromBd(UUID id);

    ReviewDto closeReviewSession(UUID reviewId, boolean isDone, AuthenticationToken request);

}
