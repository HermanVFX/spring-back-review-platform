package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.ReviewDto;
import com.hermanvfx.springbackreviewplatform.entity.enums.Speciality;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialityMapper {
    Speciality specialityEnumToSpeciality(ReviewDto.SpecialityEnum specialityEnum);
}
