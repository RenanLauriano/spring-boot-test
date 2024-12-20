package com.javatest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        String randomUserEndpointUrl = "https://randomuser.me/api";
        WebClient webClient = org.springframework.web.reactive.function.client.WebClient.create(randomUserEndpointUrl);
        return webClient;
    }
}
