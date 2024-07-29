package com.openpayd.exchange.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class CurrencyConversionHistoryDto {

    private UUID id;
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
    private UUID transactionId;
    private Instant transactionDate;
}
