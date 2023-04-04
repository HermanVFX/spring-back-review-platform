package com.hermanvfx.springbackreviewplatform.mapper;

import com.example.userservice.dto.CompanyDto;
import com.example.userservice.dto.ShortCompanyDto;
import com.hermanvfx.springbackreviewplatform.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDto companyToCompanyDto(Company entity);

    @Mapping(target = "commentaries", ignore = true)
    @Mapping(target = "interviews", ignore = true)
    Company shortCompanyDtoToCompany(ShortCompanyDto dto);


    List<CompanyDto> listCompanyToListCompanyDto(List<Company> entities);

}
