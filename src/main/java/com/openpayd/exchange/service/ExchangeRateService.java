package com.openpayd.exchange.service;

import com.openpayd.exchange.dto.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final RestTemplate restTemplate;

    private static final String API_URL = "http://apilayer.net/api/live?access_key=3274a7afbd62ec1d344349cb7c2d6286&currencies={targetCurrency}&source={sourceCurrency}&format=1";

    public ExchangeRateResponse getExchangeRate(String sourceCurrency, String targetCurrency) {
        return restTemplate.getForObject(API_URL, ExchangeRateResponse.class, targetCurrency, sourceCurrency);
    }
}
