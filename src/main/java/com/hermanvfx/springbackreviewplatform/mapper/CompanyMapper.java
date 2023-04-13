package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.CompanyDto;
import com.example.userservice.dto.ShortCompanyDto;
import com.hermanvfx.springbackreviewplatform.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {
                InterviewMapper.class,
                CommentaryMapper.class
        })
public interface CompanyMapper {

//    Попытка избавиться от цикличности:
    @Mapping(target = "interviews", expression = "java(null)")
    CompanyDto companyToCompanyDto(Company entity);



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "commentaries", ignore = true)
    @Mapping(target = "interviews", ignore = true)
    @Mapping(target = "create", ignore = true)
    @Mapping(target = "update", ignore = true)
    @Mapping(target = "delete", ignore = true)
    @Mapping(target = "active", ignore = true)
    Company shortCompanyDtoToCompany(ShortCompanyDto dto);


    List<CompanyDto> listCompanyToListCompanyDto(List<Company> entities);

}
