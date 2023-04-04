package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.InterviewApi;
import com.example.userservice.dto.InterviewDto;
import com.example.userservice.dto.InterviewListDto;
import com.example.userservice.dto.ShortInterviewDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class InterviewController implements InterviewApi {
    @Override
    public ResponseEntity<InterviewDto> createInterview(ShortInterviewDto shortInterviewDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteInterview(UUID interviewId) {
        return null;
    }

    @Override
    public ResponseEntity<InterviewListDto> findAllInterviews(Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseEntity<List<InterviewDto>> findInterviewById(UUID interviewId) {
        return null;
    }

    @Override
    public ResponseEntity<InterviewDto> updateInterview(UUID interviewId, InterviewDto interviewDto) {
        return null;
    }
}
