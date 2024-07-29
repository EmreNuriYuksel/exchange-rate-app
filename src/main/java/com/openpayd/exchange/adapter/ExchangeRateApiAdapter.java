package com.openpayd.exchange.adapter;

import java.math.BigDecimal;

public interface ExchangeRateApiAdapter {

    BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency);
}
