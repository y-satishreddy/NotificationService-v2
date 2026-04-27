package com.skylimit.Skylimit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WebClientConfig  {

    @Bean
    public RestClient restClientBuilder() {
        return RestClient.builder().build();
    }
}