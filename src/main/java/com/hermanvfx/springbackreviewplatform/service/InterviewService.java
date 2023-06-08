package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.InterviewDto;
import com.example.userservice.dto.InterviewDtoPage;
import com.example.userservice.dto.InterviewListDto;
import com.example.userservice.dto.ShortInterviewDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface InterviewService {

    InterviewDtoPage findAllInterview(Pageable pageable);

    InterviewDto findInterviewById(UUID interviewId);

    InterviewDto create(ShortInterviewDto interview, String token);

    InterviewDto update(InterviewDto interview, UUID interviewId);

    void delete(UUID id);

    void deleteFromBd(UUID id);

}
