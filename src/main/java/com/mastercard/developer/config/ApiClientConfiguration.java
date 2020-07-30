package com.mastercard.developer.config;

import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(MastercardProperties.class)
public class ApiClientConfiguration {

    @Bean
    public ApiClient apiClient(@Autowired MastercardProperties mastercardProperties) {

        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(mastercardProperties.getBasePath());
        apiClient.setDebugging(true);
        apiClient.setReadTimeout(4000);

        return apiClient;
    }
}
