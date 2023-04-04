package com.hermanvfx.springbackreviewplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "social")
@Entity
public class Social {
    @Id
    @Column(name = "social_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "social_name", nullable = false)
    private String name;

    @Column(name = "social_link", nullable = false)
    private String link;

    @ManyToOne
    @JoinColumn(name="uzr_id", nullable=false)
    private User user;

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
