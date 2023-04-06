package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.ReviewApi;
import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewListDto;
import com.example.userservice.dto.ShortReviewDto;
import com.hermanvfx.springbackreviewplatform.mapper.ReviewMapper;
import com.hermanvfx.springbackreviewplatform.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewService reviewService;

    @Override
    public ResponseEntity<ReviewDto> createReview(ShortReviewDto shortReviewDto) {
        ReviewDto newReview = reviewService.create(shortReviewDto);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ReviewListDto> findAllReviews(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>( reviewService.findAllUser(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteReview(UUID reviewId) {
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
