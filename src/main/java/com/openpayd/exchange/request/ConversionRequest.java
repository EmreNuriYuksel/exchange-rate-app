package com.openpayd.exchange.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConversionRequest {
    private BigDecimal amount;
    private String sourceCurrency; //curr enum
    private String targetCurrency;
}
