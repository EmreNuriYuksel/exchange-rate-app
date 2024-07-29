package com.openpayd.exchange.service;

import com.openpayd.exchange.adapter.ExchangeRateApiAdapter;
import com.openpayd.exchange.entity.CurrencyConversion;
import com.openpayd.exchange.repository.CurrencyConversionRepository;
import com.openpayd.exchange.request.ConversionRequest;
import com.openpayd.exchange.response.ConversionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyConversionService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final CurrencyConversionRepository repository;
    private final ExchangeRateApiAdapter exchangeRateApiAdapter;

    //@Cacheable
    public BigDecimal getExchangeRate(String sourceCurrency, String targetCurrency) {
        return exchangeRateApiAdapter.getExchangeRate(sourceCurrency, targetCurrency);
    }

    public ConversionResponse convertCurrency(ConversionRequest request) {
        BigDecimal actualRate = getExchangeRate(request.getSourceCurrency(), request.getTargetCurrency());

        BigDecimal convertedAmount = request.getAmount().multiply(actualRate);

        UUID transactionId = UUID.randomUUID();

        CurrencyConversion conversion = new CurrencyConversion(); //builder
        conversion.setSourceCurrency(request.getSourceCurrency());
        conversion.setTargetCurrency(request.getTargetCurrency());
        conversion.setAmount(request.getAmount());
        conversion.setConvertedAmount(convertedAmount);
        conversion.setTransactionId(transactionId);
        conversion.setTransactionDate(Instant.now());

        repository.save(conversion);

        ConversionResponse response = new ConversionResponse(); //builder
        response.setConvertedAmount(convertedAmount);
        response.setTransactionId(transactionId);
        return response;
    }

    public List<CurrencyConversion> getConversionHistory(UUID transactionId, String startDate, String endDate) {

        if (Objects.nonNull(transactionId)) {
            return repository.findByTransactionId(transactionId);
        }
        else {
            return repository.findByTransactionDateBetween(getInstant(startDate), getInstant(endDate));
        }
    }

    private static Instant getInstant(String date) {
        return date != null ?
                LocalDateTime.parse(date, FORMATTER).toInstant(ZoneOffset.UTC)
                : null;
    }
}
