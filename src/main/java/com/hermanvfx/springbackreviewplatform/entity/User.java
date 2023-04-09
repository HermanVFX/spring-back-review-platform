package com.hermanvfx.springbackreviewplatform.entity;

import com.hermanvfx.springbackreviewplatform.entity.enums.Role;
import com.hermanvfx.springbackreviewplatform.entity.enums.Speciality;
import com.hermanvfx.springbackreviewplatform.security.token.Token;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "uzr")
@Builder
@Entity
public class User implements UserDetails {
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

    @Column(name = "uzr_avatar")
    private String avatar;

    @Column(name = "uzr_password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "uzr_role")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Token> tokens;

    @Enumerated(EnumType.STRING)
    @Column(name = "uzr_specialities")
    private Speciality specialities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reviewer")
    private List<Review> reviewsReceiving;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Review> reviewsStudent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Social> socials;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Commentary> commentaries;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
