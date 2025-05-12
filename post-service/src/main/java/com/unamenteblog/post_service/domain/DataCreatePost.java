package com.unamenteblog.post_service.domain;

import jakarta.validation.constraints.NotBlank;

public record DataCreatePost(
        @NotBlank
         String title,
         @NotBlank String subtitle,
         @NotBlank
         String content,
         @NotBlank
        Long author) {

}
