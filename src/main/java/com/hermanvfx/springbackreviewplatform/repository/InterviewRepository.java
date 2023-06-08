package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.Interview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InterviewRepository extends CrudRepository<Interview, UUID> {

    @Query("SELECT p FROM Interview p")
    Page<Interview> findPageInterview(Pageable pageable);

}
