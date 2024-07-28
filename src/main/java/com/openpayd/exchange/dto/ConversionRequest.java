package com.openpayd.exchange.dto;

import lombok.Data;

@Data
public class ConversionRequest {
    private double amount;
    private String sourceCurrency;
    private String targetCurrency;
}
