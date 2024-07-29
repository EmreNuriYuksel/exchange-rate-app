package com.openpayd.exchange.entity;

import com.openpayd.exchange.enums.CurrencyCode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private CurrencyCode sourceCurrency;
    private CurrencyCode targetCurrency;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
    private UUID transactionId;
    private Instant transactionDate;
}
