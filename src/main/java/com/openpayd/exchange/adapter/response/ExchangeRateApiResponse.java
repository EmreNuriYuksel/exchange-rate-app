package com.openpayd.exchange.adapter.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ExchangeRateApiResponse {
    private boolean success;
    private String terms;
    private String privacy;
    private long timestamp;
    private String source;
    private Map<String, BigDecimal> quotes;
}
