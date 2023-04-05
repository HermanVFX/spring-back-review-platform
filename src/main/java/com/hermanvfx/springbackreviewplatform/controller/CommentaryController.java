package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.CommentaryApi;
import com.example.userservice.dto.CommentaryDto;
import com.example.userservice.dto.ShortCommentaryDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommentaryController implements CommentaryApi {


    @Override
    public ResponseEntity<CommentaryDto> createCommentary(ShortCommentaryDto shortCommentaryDto) {
        return null;
    }
}
