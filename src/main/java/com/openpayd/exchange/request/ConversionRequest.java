package com.openpayd.exchange.request;

import com.openpayd.exchange.enums.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConversionRequest {
    private BigDecimal amount;
    private CurrencyCode sourceCurrency;
    private CurrencyCode targetCurrency;
}
