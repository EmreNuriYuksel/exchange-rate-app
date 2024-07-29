package com.openpayd.exchange.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class CurrencyConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
    private UUID transactionId;
    private Instant transactionDate;
}
