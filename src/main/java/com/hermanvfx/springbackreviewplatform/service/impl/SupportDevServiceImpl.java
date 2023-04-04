package com.hermanvfx.springbackreviewplatform.service.impl;


import com.hermanvfx.springbackreviewplatform.service.SupportDevService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Slf4j
@Service
@AllArgsConstructor
public class SupportDevServiceImpl implements SupportDevService {

    @Override
    public int getLastIndexElement(Pageable pageable) {
        return pageable.getPageNumber() * pageable.getPageSize();

    }

    @Override
    public int getFirstIndexElement(Pageable pageable, int last) {
        return last - pageable.getPageSize();
    }
}
