package com.openpayd.exchange.controller;

import com.openpayd.exchange.dto.ConversionRequest;
import com.openpayd.exchange.dto.ConversionResponse;
import com.openpayd.exchange.dto.ExchangeRateResponse;
import com.openpayd.exchange.entity.CurrencyConversion;
import com.openpayd.exchange.service.CurrencyConversionService;
import com.openpayd.exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CurrencyConverterController {

    private final ExchangeRateService exchangeRateService;
    private final CurrencyConversionService currencyConversionService;

    @GetMapping("/exchange-rate")
    public ExchangeRateResponse getExchangeRate(@RequestParam String sourceCurrency, @RequestParam String targetCurrency) {
        return exchangeRateService.getExchangeRate(sourceCurrency, targetCurrency);
    }

    @PostMapping("/convert")
    public ConversionResponse convertCurrency(@RequestBody ConversionRequest request) {
        return currencyConversionService.convertCurrency(request);
    }

    @GetMapping("/history")
    public List<CurrencyConversion> getConversionHistory(@RequestParam(required = false) String transactionId,
                                                         @RequestParam(required = false) LocalDateTime startDate,
                                                         @RequestParam(required = false) LocalDateTime endDate) {
        return currencyConversionService.getConversionHistory(transactionId, startDate, endDate);
    }
}
