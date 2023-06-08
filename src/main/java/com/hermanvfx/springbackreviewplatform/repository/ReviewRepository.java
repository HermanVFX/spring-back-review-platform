package com.hermanvfx.springbackreviewplatform.repository;

import com.hermanvfx.springbackreviewplatform.entity.Review;
import com.hermanvfx.springbackreviewplatform.entity.enums.StatusReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends CrudRepository<Review, UUID> {
    List<Review> findAllByReviewerId(UUID reviewerId);
    List<Review> findAll();
    List<Review> findAllByStudentId(UUID studentId);

    @Query(
            value = "SELECT u FROM Review  u where u.status = :status order by u.create desc "
    )
    Page<Review> findByStatusReviewPage(@Param("status") StatusReview statusReview, Pageable pageable);

    @Query("SELECT p FROM Interview p")
    Page<Review> findPageReview(Pageable pageable);

    @Query(
            value = "SELECT u FROM Review  u where u.student = :usrId order by u.create desc "
    )
    Page<Review> findAllReviewByUser(@Param("usrId") UUID usrId, Pageable pageable);
}
