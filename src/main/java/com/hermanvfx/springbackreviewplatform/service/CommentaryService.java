package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.CommentaryDto;
import com.example.userservice.dto.EditCommentaryDto;
import com.example.userservice.dto.ShortCommentaryDto;

import java.util.UUID;

public interface CommentaryService {

    CommentaryDto create(ShortCommentaryDto commentary);

    CommentaryDto update(EditCommentaryDto commentary);

    void delete(UUID id);

    void deleteFromBd(UUID id);
}
