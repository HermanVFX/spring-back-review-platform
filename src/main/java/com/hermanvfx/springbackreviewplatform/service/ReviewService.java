package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.AuthenticationRequest;
import com.example.userservice.dto.AuthenticationToken;
import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewListDto;
import com.example.userservice.dto.ShortReviewDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReviewService {

    ReviewListDto findAllReviews(Pageable pageable);

    ReviewDto findUserById(UUID reviewId);

    ReviewDto create(ShortReviewDto review);

    ReviewDto update(ReviewDto review, UUID reviewId);

    void delete(UUID reviewId);

    ReviewDto closeReviewSession(UUID reviewId, boolean isDone, AuthenticationToken request);

}
