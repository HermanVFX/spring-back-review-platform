package com.hermanvfx.springbackreviewplatform.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interview")
@Entity
public class Interview {
    @Id
    @Column(name = "interview_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "interview_job_title", nullable = false)
    private String jobTitle;

    @Column(name = "interview_job_link")
    private String jobLink;

    @Column(name = "interview_description")
    private String description;

    @Column(name = "interview_money")
    private Integer money;

    @Column(name = "interview_video_link")
    private String videoLink;

    @Column(name = "interview_date_time")
    private OffsetDateTime date;

    @Column(name = "interview_structure")
    private String structure;
    @Column(name = "interview_substructure")
    private String substructure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name="uzr_id")
    private User user;

    @OneToMany(mappedBy="interview")
    private List<Commentary> commentaries;

    @Column(name = "create_time", nullable = false)
    private OffsetDateTime create;
    @Column(name = "update_time")
    private OffsetDateTime update;
    @Column(name = "delete_time")
    private OffsetDateTime delete;
    @Column(
            name = "is_active",
            nullable = false
    )
    private boolean isActive = true;
}
