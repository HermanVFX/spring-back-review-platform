package com.hermanvfx.springbackreviewplatform.service;

import com.example.userservice.dto.CommentaryDto;
import com.example.userservice.dto.ShortCommentaryDto;
import com.example.userservice.dto.ShortCompanyDto;

public interface CommentaryService {

    CommentaryDto create(ShortCommentaryDto commentary);
}
