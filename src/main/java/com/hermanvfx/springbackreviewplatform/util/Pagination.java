package com.hermanvfx.springbackreviewplatform.util;

import com.hermanvfx.springbackreviewplatform.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class Pagination<T> {

    public Page<T> addPagination(List<T> list, Pageable pageable) {
        int last = pageable.getPageNumber() * pageable.getPageSize();
        int first = last - pageable.getPageSize();

        if (list.size() < first) {
            throw new NotFoundException("Review not found");
        } else if (list.size() < last) {
            last = list.size();
        }
        return new PageImpl<>(list.subList(first, last), pageable, list.size());
    }

}
