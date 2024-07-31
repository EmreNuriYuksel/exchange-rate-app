package com.openpayd.exchange.service;

import com.openpayd.exchange.adapter.ExchangeRateApiAdapter;
import com.openpayd.exchange.dto.ConversionResponseDto;
import com.openpayd.exchange.dto.CurrencyConversionHistoryDto;
import com.openpayd.exchange.entity.CurrencyConversionHistory;
import com.openpayd.exchange.enums.CurrencyCode;
import com.openpayd.exchange.mapper.CurrencyConversionHistoryMapper;
import com.openpayd.exchange.repository.CurrencyConversionHistoryRepository;
import com.openpayd.exchange.request.ConversionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@CacheConfig(cacheNames = {"exchangeRates"})
public class CurrencyConversionService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private final CurrencyConversionHistoryRepository repository;
    private final ExchangeRateApiAdapter exchangeRateApiAdapter;
    private final CurrencyConversionHistoryMapper mapper;

    @Cacheable(cacheNames = "exchangeRates",
            key = "#sourceCurrency.name() + '-' + #targetCurrency.name()",
            unless = "#result == null")
    public BigDecimal getExchangeRate(CurrencyCode sourceCurrency, CurrencyCode targetCurrency) {
        return exchangeRateApiAdapter.getExchangeRate(sourceCurrency.name(), targetCurrency.name());
    }

    @Transactional
    public ConversionResponseDto convertCurrency(ConversionRequest request) {

        BigDecimal actualRate = getExchangeRate(request.getSourceCurrency(), request.getTargetCurrency());

        BigDecimal convertedAmount = request.getAmount().multiply(actualRate);
        UUID transactionId = UUID.randomUUID();

        persistCurrencyConversion(request, convertedAmount, transactionId);

        return ConversionResponseDto.builder()
                .convertedAmount(convertedAmount)
                .transactionId(transactionId)
                .build();
    }

    public List<CurrencyConversionHistoryDto> getConversionHistory(final UUID transactionId,
                                                                   final String startDate,
                                                                   final String endDate) {

        final List<CurrencyConversionHistory> currencyConversionHistoryList;
        if (Objects.nonNull(transactionId)) {
            currencyConversionHistoryList = repository.findByTransactionId(transactionId);
        }
        else {
            currencyConversionHistoryList =
                    repository.findByTransactionDateBetween(getInstant(startDate), getInstant(endDate));
        }
        return mapper.toDtoList(currencyConversionHistoryList);
    }

    private void persistCurrencyConversion(final ConversionRequest request,
                                           final BigDecimal convertedAmount,
                                           final UUID transactionId) {

        final CurrencyConversionHistory currencyConversionHistory = CurrencyConversionHistory.builder()
                .sourceCurrency(request.getSourceCurrency())
                .targetCurrency(request.getTargetCurrency())
                .amount(request.getAmount())
                .convertedAmount(convertedAmount)
                .transactionId(transactionId)
                .transactionDate(Instant.now())
                .build();

        repository.save(currencyConversionHistory);
    }

    private static Instant getInstant(String date) {
        return date != null ?
                LocalDateTime.parse(date, FORMATTER).toInstant(ZoneOffset.UTC)
                : null;
    }
}
