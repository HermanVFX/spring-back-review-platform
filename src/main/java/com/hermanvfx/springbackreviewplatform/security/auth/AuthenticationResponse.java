package com.hermanvfx.springbackreviewplatform.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private UUID id;

    private String firstName;
    private String lastName;
    private String avatar;
    private String email;

    private String accessToken;
    private String refreshToken;
}