package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ShortReviewDto;
import com.hermanvfx.springbackreviewplatform.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ReviewMapper {
    List<ReviewDto> iterableReviewToListReviewDto (Iterable<Review> entities);
    Review ShortReviewDtoToReview(ShortReviewDto shortDto);
    ReviewDto reviewToReviewDto (Review entity);
    Review reviewDtoToReview(ReviewDto dto);
}
