package com.mastercard.developer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "mastercard.api")
public class MastercardProperties {

private String basePath;


}
