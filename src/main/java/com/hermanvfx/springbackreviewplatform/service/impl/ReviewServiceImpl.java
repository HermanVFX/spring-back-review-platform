package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.AuthenticationRequest;
import com.example.userservice.dto.AuthenticationToken;
import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewListDto;
import com.example.userservice.dto.ShortReviewDto;
import com.hermanvfx.springbackreviewplatform.entity.Review;
import com.hermanvfx.springbackreviewplatform.entity.enums.StatusReview;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.mapper.ReviewMapper;
import com.hermanvfx.springbackreviewplatform.mapper.SpecialityMapper;
import com.hermanvfx.springbackreviewplatform.mapper.UserMapper;
import com.hermanvfx.springbackreviewplatform.repository.ReviewRepository;
import com.hermanvfx.springbackreviewplatform.repository.UserRepository;
import com.hermanvfx.springbackreviewplatform.security.token.TokenRepository;
import com.hermanvfx.springbackreviewplatform.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
    public ReviewListDto findAllReviews(Pageable pageable) {
        List<ReviewDto> list = reviewMapper.iterableReviewToListReviewDto(reviewRepository.findAll());

        int last = pageable.getPageNumber() * pageable.getPageSize();
        int first = last - pageable.getPageSize();

        if (list.size() < first) {
            throw new NotFoundException("Review not found");
        } else if (list.size() < last) {
            last = list.size();
        }

        Page<ReviewDto> page = new PageImpl<>(list.subList(first, last), pageable, list.size());

        return new ReviewListDto()
                .content(list.subList(first, last))
                .totalPages(BigDecimal.valueOf(page.getTotalPages()))
                .totalElements(BigDecimal.valueOf(page.getTotalElements()))
                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
    }

    @Override
    public ReviewDto findUserById(UUID reviewId) {
        return reviewMapper.reviewToReviewDto(reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review with id:[" + reviewId + "] does not found")));
    }

    @Override
    @Transactional
    public ReviewDto create(ShortReviewDto review) {
        var token = review.getAuthData().getToken();
        var authUser = tokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found"))
                .getUser();


        Review newReview = reviewMapper.ShortReviewDtoToReview(review);
        newReview.setCreate(LocalDate.now());
        newReview.setReviewer(authUser);
        return reviewMapper.reviewToReviewDto(reviewRepository.save(newReview));
    }

    @Override
    @Transactional
    public ReviewDto update(ReviewDto review, UUID reviewId) {
        Review oldReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review with id:[" + reviewId + "] does not found"));

        oldReview.setUpdate(LocalDate.now());

        oldReview.setReviewer(userMapper.userDtoToUser(review.getReviewer()));
        oldReview.setLink(review.getLink());
        oldReview.setSpeciality(specialityMapper.specialityEnumToSpeciality(review.getSpeciality()));
        oldReview.setStudent(userMapper.userDtoToUser(review.getStudent()));
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
        return reviewMapper.reviewToReviewDto(reviewRepository.findById(reviewId).orElseThrow());
    }
}
