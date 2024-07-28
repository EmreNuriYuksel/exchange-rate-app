package com.openpayd.exchange.service;

import com.openpayd.exchange.dto.ConversionRequest;
import com.openpayd.exchange.dto.ConversionResponse;
import com.openpayd.exchange.dto.ExchangeRateResponse;
import com.openpayd.exchange.entity.CurrencyConversion;
import com.openpayd.exchange.repository.CurrencyConversionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyConversionService {

    private final CurrencyConversionRepository repository;
    private final ExchangeRateService exchangeRateService;

    public ConversionResponse convertCurrency(ConversionRequest request) {
        ExchangeRateResponse exchangeRateResponse =
                exchangeRateService.getExchangeRate(request.getSourceCurrency(), request.getTargetCurrency());

        double convertedAmount = request.getAmount() *
                exchangeRateResponse.getQuotes().get(request.getSourceCurrency() + request.getTargetCurrency());

        String transactionId = UUID.randomUUID().toString();

        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setSourceCurrency(request.getSourceCurrency());
        conversion.setTargetCurrency(request.getTargetCurrency());
        conversion.setAmount(request.getAmount());
        conversion.setConvertedAmount(convertedAmount);
        conversion.setTransactionId(transactionId);
        conversion.setTransactionDate(LocalDateTime.now());

        repository.save(conversion);

        ConversionResponse response = new ConversionResponse();
        response.setConvertedAmount(convertedAmount);
        response.setTransactionId(transactionId);
        return response;
    }

    public List<CurrencyConversion> getConversionHistory(String transactionId, LocalDateTime startDate, LocalDateTime endDate) {
        if (transactionId != null) {
            return repository.findByTransactionId(transactionId);
        } else {
            return repository.findByTransactionDateBetween(startDate, endDate);
        }
    }
}
