package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.CompanyApi;
import com.example.userservice.dto.CompanyDto;
import com.example.userservice.dto.CompanyListDto;
import com.example.userservice.dto.ShortCompanyDto;
import com.hermanvfx.springbackreviewplatform.service.CompanyService;
import com.hermanvfx.springbackreviewplatform.service.impl.CompanyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class CompanyController implements CompanyApi {

    private final CompanyService companyService;


    @Override
    public ResponseEntity<CompanyDto> createCompany(ShortCompanyDto shortCompanyDto) {
        CompanyDto newCompany = companyService.create(shortCompanyDto);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCompany(UUID companyId) {
        return null;
    }

    @Override
    public ResponseEntity<CompanyListDto> findAllCompanys(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>( companyService.findAllCompany(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CompanyDto>> findCompanyById(UUID companyId) {
        return null;
    }

    @Override
    public ResponseEntity<CompanyDto> updateCompany(UUID companyId, CompanyDto companyDto) {
        return null;
    }
}
