package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.AuthenticationToken;
import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewDtoPage;
import com.example.userservice.dto.ShortReviewDto;
import com.hermanvfx.springbackreviewplatform.entity.Review;
import com.hermanvfx.springbackreviewplatform.entity.enums.StatusReview;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.mapper.ReviewMapper;
import com.hermanvfx.springbackreviewplatform.mapper.SpecialityMapper;
import com.hermanvfx.springbackreviewplatform.mapper.UserMapper;
import com.hermanvfx.springbackreviewplatform.repository.ReviewRepository;
import com.hermanvfx.springbackreviewplatform.security.token.TokenRepository;
import com.hermanvfx.springbackreviewplatform.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final TokenRepository tokenRepository;
    private final UserMapper userMapper;
    private final SpecialityMapper specialityMapper;

    @Override
    public ReviewDtoPage findAllReviews(Pageable pageable) {
        Page<Review> pageReview = reviewRepository.findPageReview(pageable);
        return pageToDto(pageable, pageReview);
    }

    @Override
    public ReviewDtoPage findAllReviewsByUser(Pageable pageable, UUID userId) {
        Page<Review> page = reviewRepository.findAllReviewByUser(userId, pageable);
        return pageToDto(pageable, page);
    }

    @Override
    public ReviewDtoPage findTobeReviews(Pageable pageable) {
        Page<Review> page = reviewRepository.findByStatusReviewPage(StatusReview.TOBE, pageable);
        return pageToDto(pageable, page);
    }

    @Override
    public ReviewDtoPage findPassedReviews(Pageable pageable) {
        Page<Review> page = reviewRepository.findByStatusReviewPage(StatusReview.PASSED, pageable);
        return pageToDto(pageable, page);
    }

    @Override
    public ReviewDtoPage findCanceledReviews(Pageable pageable) {
        Page<Review> page = reviewRepository.findByStatusReviewPage(StatusReview.CANCELED, pageable);
        return pageToDto(pageable, page);
    }


    @Override
    public ReviewDto findReviewById(UUID reviewId) {
        return reviewMapper.reviewToReviewDto(reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review with id:[" + reviewId + "] does not found")));
    }

    @Override
    @Transactional
    public ReviewDto create(ShortReviewDto review, String tokenB) {
        var token = tokenB.substring(7);
        var authUser = tokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found"))
                .getUser();

        Review newReview = reviewMapper.ShortReviewDtoToReview(review);
        newReview.setCreate(OffsetDateTime.now());
        newReview.setReviewer(authUser);
        return reviewMapper.reviewToReviewDto(reviewRepository.save(newReview));
    }

    @Override
    public ReviewDto createForUser(ShortReviewDto review, String token) {
        token = token.substring(7);
        var authUser = tokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found"))
                .getUser();

        Review newReview = reviewMapper.ShortReviewDtoToReview(review);
        newReview.setCreate(OffsetDateTime.now());
        newReview.setStudent(authUser);
        return reviewMapper.reviewToReviewDto(reviewRepository.save(newReview));
    }

    @Override
    @Transactional
    public ReviewDto update(ReviewDto review, UUID reviewId) {
        Review oldReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review with id:[" + reviewId + "] does not found"));

        oldReview.setUpdate(OffsetDateTime.now());

        oldReview.setReviewer(userMapper.userForReviewDtoToUser(review.getReviewer()));
        oldReview.setLink(review.getLink());
        oldReview.setSpeciality(specialityMapper.specialityEnumToSpeciality(review.getSpeciality()));
        oldReview.setStudent(userMapper.userForReviewDtoToUser(review.getStudent()));
        oldReview.setTheme(review.getTheme());
        oldReview.setTime(review.getTime());

        Review newReview = reviewRepository.save(oldReview);
        return reviewMapper.reviewToReviewDto(newReview);
    }

    @Override
    @Transactional
    public void delete(UUID reviewId) {
        reviewRepository.delete(reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review with id:[" + reviewId + "] does not found")));
    }

    @Override
    public void deleteFromBd(UUID id) {
        reviewRepository.delete(reviewRepository.findById(id).orElseThrow());
    }

    @Override
    @Transactional
    public ReviewDto closeReviewSession(UUID reviewId, boolean isDone, AuthenticationToken request) {
        var token = request.getToken();
        var authUserId = tokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found"))
                .getUser()
                .getId();

        reviewRepository.findAllByReviewerId(authUserId).forEach(review -> {
            if (review.getId().equals(reviewId)) {
                if (isDone) {
                    review.setStatus(StatusReview.PASSED);
                } else {
                    review.setStatus(StatusReview.CANCELED);
                }
                update(reviewMapper.reviewToReviewDto(review), reviewId);
            }
        });
        return reviewMapper.reviewToReviewDto(reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review with id:[" + reviewId + "] does not update")));
    }

    private ReviewDtoPage pageToDto(Pageable pageable, Page<Review> page) {
        var content = reviewMapper.listReviewToListReviewDto(page.getContent());
        ReviewDtoPage reviewDtoPage = new ReviewDtoPage();
        reviewDtoPage.setContent(content);
        reviewDtoPage.setCurrentPage(pageable.getPageNumber());
        reviewDtoPage.setTotalPage(page.getTotalPages());
        reviewDtoPage.setTotalElement(page.getTotalElements());
        return reviewDtoPage;
    }


}
