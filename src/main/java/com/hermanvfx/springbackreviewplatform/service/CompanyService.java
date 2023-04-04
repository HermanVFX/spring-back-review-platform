package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CompanyService {

    CompanyListDto findAllCompany(Pageable pageable);

    CompanyDto findCompanyById(UUID companyId);

    CompanyDto create(ShortCompanyDto company);

    CompanyDto update(CompanyDto company, UUID id);

    void delete(UUID id);
}
