package com.openpayd.exchange.controller;

import com.openpayd.exchange.dto.ConversionResponseDto;
import com.openpayd.exchange.dto.CurrencyConversionHistoryDto;
import com.openpayd.exchange.enums.CurrencyCode;
import com.openpayd.exchange.mapper.ConversionResponseMapper;
import com.openpayd.exchange.mapper.CurrencyConversionHistoryMapper;
import com.openpayd.exchange.request.ConversionRequest;
import com.openpayd.exchange.response.ConversionResponse;
import com.openpayd.exchange.response.CurrencyConversionHistoryResponse;
import com.openpayd.exchange.service.CurrencyConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CurrencyConverterController {

    private final CurrencyConversionService currencyConversionService;
    private final ConversionResponseMapper conversionResponseMapper;
    private final CurrencyConversionHistoryMapper conversionHistoryMapper;

    @GetMapping("/exchange-rate")
    public ResponseEntity<BigDecimal> getExchangeRate(@RequestParam final CurrencyCode sourceCurrency,
                                                      @RequestParam final CurrencyCode targetCurrency) {

        return ResponseEntity.ok(currencyConversionService.getExchangeRate(sourceCurrency, targetCurrency));
    }

    @PostMapping("/convert")
    public ResponseEntity<ConversionResponse> convertCurrency(@RequestBody final ConversionRequest request) {

        final ConversionResponseDto conversionResponseDto = currencyConversionService.convertCurrency(request);

        return ResponseEntity.ok(conversionResponseMapper.toResponse(conversionResponseDto));
    }

    @GetMapping("/history")
    public ResponseEntity<List<CurrencyConversionHistoryResponse>> getConversionHistory(@RequestParam(required = false)
                                                                            final UUID transactionId,
                                                                                        @RequestParam(required = false)
                                                                            final String startDate,
                                                                                        @RequestParam(required = false)
                                                                            final String endDate) {

        final List<CurrencyConversionHistoryDto> currencyConversionHistoryDtoList =
                currencyConversionService.getConversionHistory(transactionId, startDate, endDate);

        return ResponseEntity.ok(conversionHistoryMapper.toResponseList(currencyConversionHistoryDtoList));
    }
}
