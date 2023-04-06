package com.hermanvfx.springbackreviewplatform.util;


import org.springframework.data.domain.Pageable;

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
