package com.hermanvfx.springbackreviewplatform.service.impl;

import com.example.userservice.dto.InterviewDto;
import com.example.userservice.dto.InterviewDtoPage;
import com.example.userservice.dto.ShortInterviewDto;
import com.hermanvfx.springbackreviewplatform.entity.Interview;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.mapper.InterviewMapper;
import com.hermanvfx.springbackreviewplatform.repository.InterviewRepository;
import com.hermanvfx.springbackreviewplatform.security.token.TokenRepository;
import com.hermanvfx.springbackreviewplatform.service.InterviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private  final InterviewMapper interviewMapper;
    private final TokenRepository tokenRepository;

    @Override
    public InterviewDtoPage findAllInterview(Pageable pageable) {
        Page<Interview> pageInterview = interviewRepository.findPageInterview(pageable);
        return pageToDto(pageable, pageInterview);
    }

    @Override
    public InterviewDto findInterviewById(UUID interviewId) {
        return interviewMapper.interviewToInterviewDto(interviewRepository.findById(interviewId)
                .orElseThrow(() -> new NotFoundException("Interview with id:[" + interviewId + "] does not found")));
    }

    @Override
    @Transactional
    public InterviewDto create(ShortInterviewDto interview, String tokenB) {
        var token = tokenB.substring(7);
        var authUser = tokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found"))
                .getUser();
//        Company company = companyRepository.findById(interview.getCompany().getId())
//                .orElseThrow(() -> new NotFoundException("Token not found"));

        Interview newInterview = interviewMapper.shortInterviewDtoToInterview(interview);
        newInterview.setCreate(OffsetDateTime.now());
        newInterview.setUser(authUser);
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

    @Override
    public void deleteFromBd(UUID id) {
        interviewRepository.delete(interviewRepository.findById(id).orElseThrow());
    }

    private InterviewDtoPage pageToDto(Pageable pageable, Page<Interview> page) {
        var content = interviewMapper.listInterviewToListInterviewDto(page.getContent());
        InterviewDtoPage interviewDtoPage = new InterviewDtoPage();
        interviewDtoPage.setContent(content);
        interviewDtoPage.setCurrentPage(pageable.getPageNumber());
        interviewDtoPage.setTotalPage(page.getTotalPages());
        interviewDtoPage.setTotalElement(page.getTotalElements());

        return interviewDtoPage;
    }

}


