package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewListDto;
import com.example.userservice.dto.ShortReviewDto;
import com.hermanvfx.springbackreviewplatform.entity.Review;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.mapper.ReviewMapper;
import com.hermanvfx.springbackreviewplatform.mapper.SpecialityMapper;
import com.hermanvfx.springbackreviewplatform.mapper.UserMapper;
import com.hermanvfx.springbackreviewplatform.repository.ReviewRepository;
import com.hermanvfx.springbackreviewplatform.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    private final UserMapper userMapper;
    private final SpecialityMapper specialityMapper;

    @Override
    public ReviewListDto findAllUser(Pageable pageable) {
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
        Review newReview = reviewMapper.ShortReviewDtoToReview(review);
        newReview.setCreate(LocalDate.now());
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
        oldReview.setTime(LocalDate.parse(review.getTime()));

        Review newReview = reviewRepository.save(oldReview);
        return reviewMapper.reviewToReviewDto(newReview);
    }

    @Override
    @Transactional
    public void delete(UUID reviewId) {
        reviewRepository.delete(reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Review with id:[" + reviewId + "] does not found")));
    }
}
