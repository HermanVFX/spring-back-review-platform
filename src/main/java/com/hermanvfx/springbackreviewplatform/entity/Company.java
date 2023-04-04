package com.hermanvfx.springbackreviewplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @OneToMany(mappedBy="company")
    private List<Interview> interviews;

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
