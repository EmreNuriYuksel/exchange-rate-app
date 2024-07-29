package com.openpayd.exchange.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ConversionResponseDto {

    private BigDecimal convertedAmount;
    private UUID transactionId;
}



