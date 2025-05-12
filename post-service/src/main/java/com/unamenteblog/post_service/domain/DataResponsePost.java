package com.unamenteblog.post_service.domain;

public record DataResponsePost(
        Long id, String title, String subtitle, String content, Long author, String creatAt, String updatAt
) {
}
