package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.AuthenticationToken;
import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ReviewListDto;
import com.example.userservice.dto.ShortReviewDto;
import com.example.userservice.dto.UserDto;
import com.hermanvfx.springbackreviewplatform.entity.Review;
import com.hermanvfx.springbackreviewplatform.entity.enums.StatusReview;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.mapper.ReviewMapper;
import com.hermanvfx.springbackreviewplatform.mapper.SpecialityMapper;
import com.hermanvfx.springbackreviewplatform.mapper.UserMapper;
import com.hermanvfx.springbackreviewplatform.repository.ReviewRepository;
import com.hermanvfx.springbackreviewplatform.security.token.TokenRepository;
import com.hermanvfx.springbackreviewplatform.service.ReviewService;
import com.hermanvfx.springbackreviewplatform.util.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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
        List<ReviewDto> list = reviewMapper.listReviewToListReviewDto(reviewRepository.findAll());

        Page<ReviewDto> page = new Pagination<ReviewDto>().addPagination(list, pageable);

        return new ReviewListDto()
                .content(page.getContent())
                .totalPages(BigDecimal.valueOf((int) Math.ceil((double) list.size() / pageable.getPageSize())))
                .totalElements(BigDecimal.valueOf(list.size()))
                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
    }

    @Override
    public ReviewListDto findAllReviewsByUser(Pageable pageable, UUID userId) {
        List<ReviewDto> list = reviewMapper.listReviewToListReviewDto(reviewRepository.findAllByReviewerId(userId));
        list.addAll(reviewMapper.listReviewToListReviewDto(reviewRepository.findAllByStudentId(userId)));

        Page<ReviewDto> page = new Pagination<ReviewDto>().addPagination(list, pageable);

        return new ReviewListDto()
                .content(page.getContent())
                .totalPages(BigDecimal.valueOf((int) Math.ceil((double) list.size() / pageable.getPageSize())))
                .totalElements(BigDecimal.valueOf(list.size()))
                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
    }

    @Override
    public ReviewListDto findTobeReviews(Pageable pageable) {
        List<ReviewDto> list = reviewMapper.iterableReviewToListReviewDto(reviewRepository.findAll());
        List<ReviewDto> listTobe = new ArrayList<>();

        for (ReviewDto dto : list) {
            if (dto.getStatus() == ReviewDto.StatusEnum.TOBE) {
                listTobe.add(dto);
            }
        }

        Page<ReviewDto> page = new Pagination<ReviewDto>().addPagination(listTobe, pageable);

        return new ReviewListDto()
                .content(page.getContent())
                .totalPages(BigDecimal.valueOf((int) Math.ceil((double) list.size() / pageable.getPageSize())))
                .totalElements(BigDecimal.valueOf(listTobe.size()))
                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
    }

    @Override
    public ReviewListDto findPassedReviews(Pageable pageable) {
        List<ReviewDto> list = reviewMapper.iterableReviewToListReviewDto(reviewRepository.findAll());
        List<ReviewDto> listTobe = new ArrayList<>();

        for (ReviewDto dto : list) {
            if (dto.getStatus() == ReviewDto.StatusEnum.PASSED) {
                listTobe.add(dto);
            }
        }

        Page<ReviewDto> page = new Pagination<ReviewDto>().addPagination(listTobe, pageable);

        return new ReviewListDto()
                .content(page.getContent())
                .totalPages(BigDecimal.valueOf((int) Math.ceil((double) listTobe.size() / pageable.getPageSize())))
                .totalElements(BigDecimal.valueOf(listTobe.size()))
                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
    }

    @Override
    public ReviewListDto findCanceledReviews(Pageable pageable) {
        List<ReviewDto> list = reviewMapper.iterableReviewToListReviewDto(reviewRepository.findAll());
        List<ReviewDto> listTobe = new ArrayList<>();

        for (ReviewDto dto : list) {
            if (dto.getStatus() == ReviewDto.StatusEnum.CANCELED) {
                listTobe.add(dto);
            }
        }

        Page<ReviewDto> page = new Pagination<ReviewDto>().addPagination(listTobe, pageable);

        return new ReviewListDto()
                .content(page.getContent())
                .totalPages(BigDecimal.valueOf((int) Math.ceil((double) listTobe.size() / pageable.getPageSize())))
                .totalElements(BigDecimal.valueOf(listTobe.size()))
                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
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
}
