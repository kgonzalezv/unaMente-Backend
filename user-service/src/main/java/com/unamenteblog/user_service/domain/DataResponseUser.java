package com.unamenteblog.user_service.domain;

public record DataResponseUser(
        Long id, String name, String email, String password, String bio, String creatAt
) {
}
