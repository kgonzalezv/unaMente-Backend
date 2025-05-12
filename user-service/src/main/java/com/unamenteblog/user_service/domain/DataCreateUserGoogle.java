package com.unamenteblog.user_service.domain;

import jakarta.validation.constraints.NotBlank;

public record DataCreateUserGoogle(
        @NotBlank
         String name,
         @NotBlank
         String email,
        String password, String authProvider, String googleId, String picture) {

}
