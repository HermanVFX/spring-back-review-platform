package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.InterviewDto;
import com.example.userservice.dto.InterviewListDto;
import com.example.userservice.dto.ShortInterviewDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface InterviewService {

    InterviewListDto findAllInterview(Pageable pageable);

    InterviewDto findInterviewById(UUID interviewId);

    InterviewDto create(ShortInterviewDto interview);

    InterviewDto update(InterviewDto interview, UUID interviewId);

    void delete(UUID interviewId);

}
