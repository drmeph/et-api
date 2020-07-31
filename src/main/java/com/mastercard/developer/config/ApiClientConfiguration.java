package com.mastercard.developer.config;

import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;

@Slf4j
@Configuration
@EnableConfigurationProperties(MastercardProperties.class)
public class ApiClientConfiguration {

    @Bean
    public ApiClient apiClient(@Autowired MastercardProperties mastercardProperties) {

        ApiClient apiClient = new ApiClient();

        try {
            PrivateKey signingKey = AuthenticationUtils.loadSigningKey(
                    mastercardProperties.getKeyFile().getFile().getAbsolutePath(),
                    mastercardProperties.getKeystoreAlias(),
                    mastercardProperties.getKeystorePassword()
            );
            apiClient.setBasePath(mastercardProperties.getBasePath());
            apiClient.setDebugging(true);
            apiClient.setReadTimeout(4000);

            return apiClient.setHttpClient(
                    apiClient.getHttpClient().newBuilder().addInterceptor(
                            new OkHttpOAuth1Interceptor(mastercardProperties.getConsumerKey(), signingKey)
                    ).build()
            );
        } catch (Exception e) {
            log.error("Error occurred while configuring ApiClient", e);
        }
        return apiClient;
    }
}
