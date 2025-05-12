package com.unamenteblog.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class GoogleOAuthService {

    @Autowired
    private  WebClient webClient ;

    public Map<String, Object> verifyAccessToken(String accessToken) {
        return webClient.get()
                .uri("/oauth2/v3/userinfo")
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(Map.class)
                .onErrorResume(e -> Mono.empty()) // Si el token no es válido, retorna null
                .block();
    }
}
