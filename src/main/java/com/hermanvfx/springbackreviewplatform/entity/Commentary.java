package com.hermanvfx.springbackreviewplatform.entity;

import jakarta.persistence.CascadeType;
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

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commentary")
@Entity
public class Commentary {
    @Id
    @Column(name = "commentary_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="uzr_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private Company company;

    @ManyToOne
    @JoinColumn(name="interview_id", nullable=false)
    private Interview interview;

    @ManyToOne
    @JoinColumn(name="sub_commentary_id", nullable=false)
    private Commentary commentary;

    @Column(name = "commentary_text", nullable = false)
    private String text;

    @Column(
            name = "commentary_likes",
            nullable = false,
            columnDefinition = "BIGINT DEFAULT 0"
    )
    private Integer likes;

    @Column(
            name = "commentary_dislikes",
            nullable = false,
            columnDefinition = "BIGINT DEFAULT 0"
    )
    private Integer dislikes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Commentary> commentaries;

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
