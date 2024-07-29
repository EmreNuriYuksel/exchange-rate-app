package com.openpayd.exchange.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "currencylayer")
public class CurrencyLayerApiProperties {

    private String baseUrl;
    private String accessKey;
    private int format;
}
