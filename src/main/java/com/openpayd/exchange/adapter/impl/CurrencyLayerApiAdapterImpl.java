package com.openpayd.exchange.adapter.impl;

import com.openpayd.exchange.adapter.ExchangeRateApiAdapter;
import com.openpayd.exchange.config.CurrencyLayerApiProperties;
import com.openpayd.exchange.response.CurrencyLayerApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CurrencyLayerApiAdapterImpl implements ExchangeRateApiAdapter {

    private final WebClient webClient;
    private final CurrencyLayerApiProperties apiProperties;

    @Override
    public BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency) {

        CurrencyLayerApiResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("access_key", apiProperties.getAccessKey())
                        .queryParam("currencies", targetCurrency)
                        .queryParam("source", sourceCurrency)
                        .queryParam("format", apiProperties.getFormat())
                        .build())
                .retrieve()
                .bodyToMono(CurrencyLayerApiResponse.class)
                .block();

        return response.getQuotes().get(sourceCurrency + targetCurrency);
    }
}
