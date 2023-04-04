package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewListDto;
import com.example.userservice.dto.ShortReviewDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReviewService {

    ReviewListDto findAllUser(Pageable pageable);

    ReviewDto findUserById(UUID reviewId);

    ReviewDto create(ShortReviewDto review);

    ReviewDto update(ReviewDto review, UUID reviewId);

    void delete(UUID reviewId);

}
