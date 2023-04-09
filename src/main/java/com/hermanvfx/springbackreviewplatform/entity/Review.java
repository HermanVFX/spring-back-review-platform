package com.hermanvfx.springbackreviewplatform.entity;

import com.hermanvfx.springbackreviewplatform.entity.enums.Speciality;
import com.hermanvfx.springbackreviewplatform.entity.enums.StatusReview;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
@Entity
public class Review {
    @Id
    @Column(name = "review_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "review_theme", nullable = false)
    private String theme;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_passed", nullable = false)
    private StatusReview status = StatusReview.TOBE;

    @ManyToOne
    @JoinColumn(name="review_receiving_id", nullable=false)
    private User reviewer;

    @ManyToOne
    @JoinColumn(name="review_student_id")
    private User student;

    @Column(name = "review_time", nullable = false)
    private OffsetDateTime time;

    @Column(name = "review_link", nullable = false)
    private String link;

    @Enumerated(EnumType.STRING)
    @Column(name = "review_speciality")
    private Speciality speciality;

    @Column(name = "create_time", nullable = false)
    private LocalDate create;
    @Column(name = "update_time")
    private LocalDate update;
    @Column(name = "delete_time")
    private LocalDate delete;
    @Column(
            name = "is_active",
            nullable = false
    )
    private boolean isActive = true;
}
