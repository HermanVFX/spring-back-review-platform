package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.Interview;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InterviewRepository extends CrudRepository<Interview, UUID> {
}
