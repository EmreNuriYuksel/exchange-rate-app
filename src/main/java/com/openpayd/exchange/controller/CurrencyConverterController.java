package com.openpayd.exchange.controller;

import com.openpayd.exchange.entity.CurrencyConversion;
import com.openpayd.exchange.request.ConversionRequest;
import com.openpayd.exchange.response.ConversionResponse;
import com.openpayd.exchange.service.CurrencyConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CurrencyConverterController {

    private final CurrencyConversionService currencyConversionService;

    @GetMapping("/exchange-rate")
    public BigDecimal getExchangeRate(@RequestParam String sourceCurrency, @RequestParam String targetCurrency) {
        return currencyConversionService.getExchangeRate(sourceCurrency, targetCurrency);
    }

    @PostMapping("/convert")
    public ConversionResponse convertCurrency(@RequestBody ConversionRequest request) {
        return currencyConversionService.convertCurrency(request);
    }

    @GetMapping("/history")
    public List<CurrencyConversion> getConversionHistory(@RequestParam(required = false) final UUID transactionId,
                                                         @RequestParam(required = false) final String startDate,
                                                         @RequestParam(required = false) final String endDate) {

        return currencyConversionService.getConversionHistory(transactionId, startDate, endDate);
    }
}
