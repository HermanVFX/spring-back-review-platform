package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.CompanyApi;
import com.example.userservice.dto.CompanyDto;
import com.example.userservice.dto.CompanyDtoPage;
import com.example.userservice.dto.ShortCompanyDto;
import com.hermanvfx.springbackreviewplatform.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class CompanyController implements CompanyApi {

    private final CompanyService companyService;

    @Override
    public ResponseEntity<CompanyDto> createCompany(String authorization, ShortCompanyDto shortCompanyDto) {
        return new ResponseEntity<>(companyService.create(shortCompanyDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCompany(String authorization, UUID companyId) {
        companyService.delete(companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CompanyDtoPage> findAllCompanys(String authorization, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>( companyService.findAllCompany(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CompanyDto> findCompanyById(String authorization, UUID companyId) {
        return new ResponseEntity<>(companyService.findCompanyById(companyId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CompanyDto> updateCompany(String authorization, UUID companyId, CompanyDto companyDto) {
        return new ResponseEntity<>(companyService.update(companyDto, companyId), HttpStatus.OK);
    }
}
