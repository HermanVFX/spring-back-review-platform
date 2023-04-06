package com.hermanvfx.springbackreviewplatform.service.impl;


import com.example.userservice.dto.CommentaryDto;
import com.example.userservice.dto.ShortCommentaryDto;
import com.hermanvfx.springbackreviewplatform.repository.CommentaryRepository;
import com.hermanvfx.springbackreviewplatform.service.CommentaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CommentaryServiceImpl implements CommentaryService {

    private final CommentaryRepository commentaryRepository;

    @Override
    public CommentaryDto create(ShortCommentaryDto commentary) {
        return null;
    }
}
