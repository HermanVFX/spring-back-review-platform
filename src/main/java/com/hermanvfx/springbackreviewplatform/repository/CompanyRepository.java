package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface CompanyRepository extends CrudRepository<Company, UUID> {
    List<Company> findAll();
}