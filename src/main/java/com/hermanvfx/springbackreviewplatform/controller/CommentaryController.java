package com.hermanvfx.springbackreviewplatform.controller;

import com.example.userservice.controller.CommentaryApi;
import com.example.userservice.dto.CommentaryDto;
import com.example.userservice.dto.EditCommentaryDto;
import com.example.userservice.dto.ShortCommentaryDto;
import com.hermanvfx.springbackreviewplatform.service.CommentaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class CommentaryController implements CommentaryApi {

    private final CommentaryService commentaryService;

    @Override
    public ResponseEntity<CommentaryDto> createCommentary(String authorization, ShortCommentaryDto shortCommentaryDto) {
        return new ResponseEntity<>( commentaryService.create(shortCommentaryDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCommentary(String authorization, UUID commentId) {
        commentaryService.delete(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteCommentaryFromDb(String authorization, UUID commentId) {
        commentaryService.deleteFromBd(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<CommentaryDto> updateCommentary(String authorization, EditCommentaryDto editCommentaryDto) {
        return new ResponseEntity<>( commentaryService.update(editCommentaryDto), HttpStatus.CREATED);
    }
}
