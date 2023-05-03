package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.CompanyDto;
import com.example.userservice.dto.CompanyListDto;
import com.example.userservice.dto.ShortCompanyDto;
import com.hermanvfx.springbackreviewplatform.entity.Company;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.mapper.CompanyMapper;
import com.hermanvfx.springbackreviewplatform.repository.CompanyRepository;
import com.hermanvfx.springbackreviewplatform.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyListDto findAllCompany(Pageable pageable) {
        List<CompanyDto> list = companyMapper.listCompanyToListCompanyDto(companyRepository.findAll());

        int last = pageable.getPageNumber() * pageable.getPageSize();
        int first = last - pageable.getPageSize();

        if (list.size() < first) {
            throw new NotFoundException("Company not found");
        } else if (list.size() < last) {
            last = list.size();
        }

        Page<CompanyDto> page = new PageImpl<>(list.subList(first, last), pageable, list.size());

        return new CompanyListDto()
                .content(list.subList(first, last))
                .totalPages(BigDecimal.valueOf(page.getTotalPages()))
                .totalElements(BigDecimal.valueOf(page.getTotalElements()))
                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
    }

    @Override
    public CompanyDto findCompanyById(UUID companyId) {
        return companyMapper.companyToCompanyDto(companyRepository
                .findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company with id[" + companyId + "] does not found")));
    }

    @Override
    @Transactional
    public CompanyDto create(ShortCompanyDto company) {
        Company newCompany = companyMapper.shortCompanyDtoToCompany(company);
        newCompany.setCreate(OffsetDateTime.now());
        return companyMapper.companyToCompanyDto(companyRepository.save(newCompany));

    }

    @Override
    @Transactional
    public CompanyDto update(CompanyDto company, UUID id) {
        Company oldCompany = companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Company with id:[" + id + "] does not found"));

        oldCompany.setUpdate(OffsetDateTime.now());

        oldCompany.setName(company.getName());
        oldCompany.setJobLink(company.getJobLink());

        Company newCompany = companyRepository.save(oldCompany);

        return companyMapper.companyToCompanyDto(newCompany);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
            companyRepository.delete(companyRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Company with id:[" + id + "] does not found")));
    }

    @Override
    public void deleteFromBd(UUID id) {
        companyRepository.delete(companyRepository.findById(id).orElseThrow());
    }
}
