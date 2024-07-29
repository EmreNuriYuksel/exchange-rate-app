package com.openpayd.exchange.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ConversionResponse {

    private BigDecimal convertedAmount;
    private UUID transactionId;
}
