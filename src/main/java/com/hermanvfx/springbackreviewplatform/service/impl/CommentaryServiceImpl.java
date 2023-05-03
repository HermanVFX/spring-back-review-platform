package com.hermanvfx.springbackreviewplatform.service.impl;


import com.example.userservice.dto.CommentaryDto;
import com.example.userservice.dto.EditCommentaryDto;
import com.example.userservice.dto.ShortCommentaryDto;
import com.hermanvfx.springbackreviewplatform.entity.Commentary;
import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import com.hermanvfx.springbackreviewplatform.mapper.CommentaryMapper;
import com.hermanvfx.springbackreviewplatform.repository.CommentaryRepository;
import com.hermanvfx.springbackreviewplatform.repository.CompanyRepository;
import com.hermanvfx.springbackreviewplatform.repository.InterviewRepository;
import com.hermanvfx.springbackreviewplatform.repository.UserRepository;
import com.hermanvfx.springbackreviewplatform.service.CommentaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepository commentaryRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final InterviewRepository interviewRepository;

    private final CommentaryMapper commentaryMapper;

    @Override
    public CommentaryDto create(ShortCommentaryDto commentary) {
        Commentary newCommentary = new Commentary();
        newCommentary.setUser(userRepository.findById(commentary.getUserId())
                .orElseThrow(() ->
                        new NotFoundException(
                                "User with id [" + commentary.getUserId() + "] not found")));
        if (commentary.getCompanyId() != null) {
            newCommentary.setCompany(companyRepository.findById(commentary.getCompanyId())
                    .orElseThrow(() ->
                            new NotFoundException(
                                    "Company with id [" + commentary.getCompanyId() + "] not found")));
        }
        if (commentary.getInterviewId() != null) {
            newCommentary.setInterview(interviewRepository.findById(commentary.getInterviewId())
                    .orElseThrow(() ->
                            new NotFoundException(
                                    "Interview with id [" + commentary.getInterviewId() + "] not found")));
        }
        if (commentary.getSubCommentaryId() != null) {
            newCommentary.setCommentary(commentaryRepository.findById(commentary.getSubCommentaryId())
                    .orElseThrow(() ->
                            new NotFoundException(
                                    "Commentary with id [" + commentary.getSubCommentaryId() + "] not found")));
        }
        newCommentary.setText(commentary.getText());
        newCommentary.setCreate(OffsetDateTime.now());
        log.info("-- Commentary [" + newCommentary.getId() + "] was added");
        return commentaryMapper.commentaryToCommentaryDto(commentaryRepository.save(newCommentary));
    }

    @Override
    public CommentaryDto update(EditCommentaryDto commentary) {
        var oldCommentary = commentaryRepository.findById(commentary.getId())
                .orElseThrow(() ->
                        new NotFoundException(
                                "Commentary with id [" + commentary.getId() + "] not found"));
        oldCommentary.setText(commentary.getText());
        log.info("-- Commentary [" + commentary.getId() + "] was updated");
        return commentaryMapper.commentaryToCommentaryDto(commentaryRepository.save(oldCommentary));
    }

    @Override
    public void delete(UUID id) {
        var oldCommentary = commentaryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Commentary with id [" + id + "] not found"));
        oldCommentary.setActive(false);
        oldCommentary.setDelete(OffsetDateTime.now());
        commentaryRepository.save(oldCommentary);
        log.info("-- Commentary [" + id + "] was deleted");
    }

    @Override
    public void deleteFromBd(UUID id) {
        commentaryRepository.delete(commentaryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Commentary with id [" + id + "] not found")));
        log.info("-- ADMIN -- Commentary [" + id + "] was deleted");
    }
}
