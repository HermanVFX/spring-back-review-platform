package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.CompanyDto;
import com.example.userservice.dto.CompanyDtoPage;
import com.example.userservice.dto.CompanyListDto;
import com.example.userservice.dto.ShortCompanyDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CompanyService {

    CompanyDtoPage findAllCompany(Pageable pageable);

    CompanyDto findCompanyById(UUID companyId);

    CompanyDto create(ShortCompanyDto company);

    CompanyDto update(CompanyDto company, UUID id);

    void delete(UUID id);

    void deleteFromBd(UUID id);

}
