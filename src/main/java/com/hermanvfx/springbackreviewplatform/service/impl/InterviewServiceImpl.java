package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.InterviewDto;
import com.example.userservice.dto.InterviewListDto;
import com.example.userservice.dto.ShortInterviewDto;
import com.hermanvfx.springbackreviewplatform.entity.Company;
import com.hermanvfx.springbackreviewplatform.entity.Interview;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.mapper.InterviewMapper;
import com.hermanvfx.springbackreviewplatform.repository.CommentaryRepository;
import com.hermanvfx.springbackreviewplatform.repository.CompanyRepository;
import com.hermanvfx.springbackreviewplatform.repository.InterviewRepository;
import com.hermanvfx.springbackreviewplatform.security.token.TokenRepository;
import com.hermanvfx.springbackreviewplatform.service.InterviewService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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

@Service
@AllArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private  final InterviewMapper interviewMapper;
    private final TokenRepository tokenRepository;
    private final CompanyRepository companyRepository;

    @Override
    public InterviewListDto findAllInterview(Pageable pageable) {
        List<InterviewDto> list = interviewMapper.iterableInterviewToListInterviewDto(interviewRepository.findAll());

        int last = pageable.getPageNumber() * pageable.getPageSize();
        int first = last - pageable.getPageSize();

        if (list.size() < first) {
            throw new NotFoundException("Review not found");
        } else if (list.size() < last) {
            last = list.size();
        }

        Page<InterviewDto> page = new PageImpl<>(list.subList(first, last), pageable, list.size());

        return new InterviewListDto()
                .content(list.subList(first, last))
                .totalPages(BigDecimal.valueOf(page.getTotalPages()))
                .totalElements(BigDecimal.valueOf(page.getTotalElements()))
                .currentPage(BigDecimal.valueOf(pageable.getPageNumber()));
    }

    @Override
    public InterviewDto findInterviewById(UUID interviewId) {
        return interviewMapper.interviewToInterviewDto(interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Interview with id:[" + interviewId + "] does not found")));
    }

    @Override
    @Transactional
    public InterviewDto create(ShortInterviewDto interview) {
        var token = interview.getAuthData().getToken();
        var authUser = tokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found"))
                .getUser();
        Company company = companyRepository.findById(interview.getCompany().getId())
                .orElseThrow(() -> new NotFoundException("Token not found"));

        Interview newInterview = interviewMapper.shortInterviewDtoToInterview(interview);
        newInterview.setCreate(OffsetDateTime.now());
        newInterview.setUser(authUser);
        newInterview.setCompany(company);
        return interviewMapper.interviewToInterviewDto(interviewRepository.save(newInterview));
    }

    @Override
    @Transactional
    public InterviewDto update(InterviewDto interview, UUID interviewId) {
        Interview oldInterview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Interview with id:[" + interviewId + "] does not found"));

        oldInterview.setUpdate(OffsetDateTime.now());

        oldInterview.setVideoLink(interview.getVideoLink());
        oldInterview.setDescription(interview.getDescription());
        oldInterview.setJobLink(interview.getJobLink());
        oldInterview.setJobTitle(interview.getJobTitle());
        oldInterview.setMoney(interview.getMoney());

        Interview newInterview = interviewRepository.save(oldInterview);
        return interviewMapper.interviewToInterviewDto(newInterview);
    }

    @Override
    @Transactional
    public void delete(UUID interviewId) {
        interviewRepository.delete(interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Interview with id:[" + interviewId + "] does not found")));
    }
}
