package com.skylimit.Skylimit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient restClient(){
        return RestClient.builder().build();
    }

    @Bean
    public WebClient webCilent(){
        return WebClient.builder().build();
    }
}
