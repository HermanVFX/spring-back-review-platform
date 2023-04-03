package com.hermanvfx.springbackreviewplatform.entity;

import com.hermanvfx.springbackreviewplatform.entity.enums.Speciality;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "uzr")
@Entity
public class User {
    @Id
    @Column(name = "uzr_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "uzr_first_name", nullable = false)
    private String firstName;

    @Column(name = "uzr_last_name")
    private String lastName;

    @Column(name = "uzr_email", nullable = false)
    private String email;

    @Column(name = "uzr_password", nullable = false)
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "uzr_role",
            joinColumns = @JoinColumn(name = "uzr_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    @Column(name = "uzr_specialities", nullable = false)
    private Speciality specialities;

    @OneToMany(
            fetch = FetchType.LAZY
//            mappedBy="uzr"
    )
    private List<Review> reviewsReceiving;

    @OneToMany(
            fetch = FetchType.LAZY
//            mappedBy="uzr"
    )
    private List<Review> reviewsStudent;

    @OneToMany(
            fetch = FetchType.LAZY
//            mappedBy="uzr"
    )
    private List<Social> socials;

    @OneToMany(
            fetch = FetchType.LAZY
//            mappedBy="uzr"
    )
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
