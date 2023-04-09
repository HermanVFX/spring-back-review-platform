package com.hermanvfx.springbackreviewplatform.security.token;

import com.hermanvfx.springbackreviewplatform.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "jwt")
@Entity
public class Token {
    @Id
    @Column(name = "jwt_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "jwt_token", nullable = false)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "jwt_type")
    private TokenType tokenType = TokenType.BEARER;

    @Column(name = "jwt_revoked", nullable = false)
    private boolean revoked;

    @Column(name = "jwt_expired", nullable = false)
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uzr_id")
    private User user;
}
