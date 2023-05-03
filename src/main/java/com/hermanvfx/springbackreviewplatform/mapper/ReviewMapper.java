package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.ReviewDto;
import com.example.userservice.dto.ShortReviewDto;
import com.hermanvfx.springbackreviewplatform.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                CompanyMapper.class,
                UserMapper.class
        })
public interface ReviewMapper {

    ReviewDto reviewToReviewDto(Review entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "reviewer", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "active", ignore = true)
    Review ShortReviewDtoToReview(ShortReviewDto shortDto);

    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "active", ignore = true)
    Review reviewDtoToReview(ReviewDto dto);

    List<ReviewDto> iterableReviewToListReviewDto(Iterable<Review> entities);
    List<ReviewDto> listReviewToListReviewDto(List<Review> entities);

}
