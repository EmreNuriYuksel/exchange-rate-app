package com.openpayd.exchange.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CurrencyLayerApiResponse {
    private boolean success;
    private String terms;
    private String privacy;
    private long timestamp;
    private String source;
    private Map<String, BigDecimal> quotes;
}
