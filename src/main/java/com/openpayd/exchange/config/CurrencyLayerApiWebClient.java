package com.openpayd.exchange.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class CurrencyLayerApiWebClient {

    private final CurrencyLayerApiProperties apiProperties;

    @Bean
    public WebClient currencyLayerWebClient() {
        return WebClient.builder()
                .baseUrl(apiProperties.getBaseUrl())
                .build();
    }
}
