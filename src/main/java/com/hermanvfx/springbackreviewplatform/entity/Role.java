package com.hermanvfx.springbackreviewplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@Entity
public class Role {
    @Id
    @Column(name = "roles_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @Column(name = "roles_name", nullable = false)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Column(name = "create_time", nullable = false)
    private LocalDate create;
    @Column(name = "update_time")
    private LocalDate update;
    @Column(name = "delete_time")
    private LocalDate delete;
    @Column(
            name = "is_active",
            nullable = false,
            columnDefinition = "BOOLEAN DEFAULT TRUE"
    )
    private boolean isActive;
}
