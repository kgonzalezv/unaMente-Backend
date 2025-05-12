package com.unamenteblog.user_service.domain;

public record LoginRequest(String name,String email, String bio, String message, String picture) {
}
