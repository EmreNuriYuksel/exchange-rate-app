package com.openpayd.exchange.dto;

import lombok.Data;

@Data
public class ConversionResponse {
    private double convertedAmount;
    private String transactionId;
}
