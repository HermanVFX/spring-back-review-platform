package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.InterviewApi;
import com.example.userservice.dto.InterviewDto;
import com.example.userservice.dto.InterviewListDto;
import com.example.userservice.dto.ShortInterviewDto;
import com.hermanvfx.springbackreviewplatform.service.InterviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class InterviewController implements InterviewApi {

    private final InterviewService interviewService;

    @Override
    public ResponseEntity<InterviewDto> createInterview(String authorization, ShortInterviewDto shortInterviewDto) {
        return new ResponseEntity<>(interviewService.create(shortInterviewDto, authorization), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteInterview(String authorization, UUID interviewId) {
        interviewService.delete(interviewId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterviewListDto> findAllInterviews(String authorization, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(interviewService.findAllInterview(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterviewDto> findInterviewById(String authorization, UUID interviewId) {
        return new ResponseEntity<>(interviewService.findInterviewById(interviewId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InterviewDto> updateInterview(String authorization, UUID interviewId, InterviewDto interviewDto) {
        return new ResponseEntity<>(interviewService.update(interviewDto, interviewId), HttpStatus.OK);
    }
}
