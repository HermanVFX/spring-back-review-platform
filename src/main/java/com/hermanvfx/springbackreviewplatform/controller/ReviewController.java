package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.ReviewApi;
import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewListDto;
import com.example.userservice.dto.ShortReviewDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ReviewController implements ReviewApi {
    @Override
    public ResponseEntity<ReviewDto> createReview(ShortReviewDto shortReviewDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteReview(UUID reviewId) {
        return null;
    }

    @Override
    public ResponseEntity<ReviewListDto> findAllReviews(Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseEntity<List<ReviewDto>> findReviewById(UUID reviewId) {
        return null;
    }

    @Override
    public ResponseEntity<ReviewDto> updateReview(UUID reviewId, ReviewDto reviewDto) {
        return null;
    }
}
