package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.CompanyDto;
import com.example.userservice.dto.CompanyDtoPage;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public CompanyDtoPage findAllCompany(Pageable pageable) {

        Page<Company> pageCompanies = companyRepository.findPageCompany(pageable);

        var a = companyRepository.testFind();




        return pageCompanyToCompanyDto(pageable, pageCompanies, a);

//        List<CompanyDto> list = companyMapper.listCompanyToListCompanyDto(companyRepository.findAll());
//
//        Page<CompanyDto> page = new Pagination<CompanyDto>().addPagination(list, pageable);
//
//        return new CompanyListDto()
//                .content(page.getContent())
//                .totalPages(BigDecimal.valueOf((int) Math.ceil((double) list.size() / pageable.getPageSize())))
//                .totalElements(BigDecimal.valueOf(list.size()))
//                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
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


    private CompanyDtoPage pageCompanyToCompanyDto(Pageable pageable, Page<Company> page, List<Company> a) {
        var content = companyMapper.listCompanyToListCompanyDto(page.getContent());
        log.info(content.toString(), "Контент");
        System.out.println("var переменная content:");
        System.out.println(content);
        System.out.println("getContent");
        System.out.println(page.getContent());
        System.out.println("Test Find");
        System.out.println(a);

        CompanyDtoPage companyDtoPage = new CompanyDtoPage();
        companyDtoPage.setContent(content);
        companyDtoPage.setCurrentPage(pageable.getPageNumber());
        companyDtoPage.setTotalPage(page.getTotalPages());
        companyDtoPage.setTotalElement(page.getTotalElements());

        return companyDtoPage;
    }

}


