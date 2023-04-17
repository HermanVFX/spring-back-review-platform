package com.hermanvfx.springbackreviewplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
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


    @ManyToOne
    @JoinColumn(name="uzr_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name="interview_id")
    private Interview interview;

    @ManyToOne
    @JoinColumn(name="sub_commentary_id")
    private Commentary commentary;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Commentary> answers;


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
