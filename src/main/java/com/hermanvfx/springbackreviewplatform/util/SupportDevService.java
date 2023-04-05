package com.hermanvfx.springbackreviewplatform.util;

import org.springframework.data.domain.Pageable;

public interface SupportDevService {
    int getLastIndexElement(Pageable pageable);
    int getFirstIndexElement(Pageable pageable, int last);
}
