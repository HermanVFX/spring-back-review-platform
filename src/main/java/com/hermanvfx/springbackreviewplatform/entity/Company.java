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
@Table(name = "company")
@Entity

public class Company {
    @Id
    @Column(name = "company_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "company_name", nullable = false)
    private String name;

    @Column(name = "company_job_link")
    private String jobLink;

    @Column(
            name = "company_rating"
    )
    private Double rating = 0.0;

    @OneToMany(mappedBy="company")
    private List<Commentary> commentaries;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="company")
    private List<Interview> interviews;

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
