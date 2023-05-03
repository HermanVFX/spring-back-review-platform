package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.ReviewApi;
import com.example.userservice.dto.AuthenticationToken;
import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewListDto;
import com.example.userservice.dto.ShortReviewDto;
import com.hermanvfx.springbackreviewplatform.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class ReviewController implements ReviewApi {

    private final ReviewService reviewService;


    @Override
    public ResponseEntity<ReviewDto> closeReview(
            String authorization,
            UUID reviewId,
            Boolean isDone,
            AuthenticationToken request) {
        return new ResponseEntity<>
                (
                    reviewService.closeReviewSession(reviewId, isDone, request),
                    HttpStatus.OK
                );
    }

    @Override
    public ResponseEntity<ReviewDto> createReview(String authorization, ShortReviewDto shortReviewDto) {
        ReviewDto newReview = reviewService.create(shortReviewDto);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ReviewDto> createReviewForUser(String authorization, ShortReviewDto shortReviewDto) {
        ReviewDto newReview = reviewService.createForUser(shortReviewDto);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ReviewListDto> findAllReviews(String authorization, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(reviewService.findAllReviews(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReviewListDto> findTobeReviews(String authorization, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(reviewService.findTobeReviews(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReviewListDto> findCanceledReviews(String authorization, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(reviewService.findCanceledReviews(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReviewListDto> findPassedReviews(String authorization, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(reviewService.findPassedReviews(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteReview(UUID reviewId) {
        reviewService.delete(reviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ReviewDto> findReviewById(String authorization, UUID reviewId) {
        return new ResponseEntity<>(reviewService.findUserById(reviewId), HttpStatus.OK);
    }



    @Override
    public ResponseEntity<ReviewDto> updateReview(String authorization, UUID reviewId, ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.update(reviewDto, reviewId), HttpStatus.OK);
    }
}
