package com.hermanvfx.springbackreviewplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private Company company;

    @ManyToOne
    @JoinColumn(name="uzr_id", nullable=false)
    private User user;

    @Column(name = "interview_description")
    private String description;

    @Column(name = "interview_money")
    private Integer money;

    @OneToMany(mappedBy="interview")
    private List<Commentary> commentaries;

    @Column(name = "interview_video_link")
    private String videoLink;

    @Column(name = "create_time", nullable = false)
    private Date create;
    @Column(name = "update_time")
    private Date update;
    @Column(name = "delete_time")
    private Date delete;
    @Column(
            name = "is_active",
            nullable = false,
            columnDefinition = "BOOLEAN DEFAULT TRUE"
    )
    private boolean isActive;
}
