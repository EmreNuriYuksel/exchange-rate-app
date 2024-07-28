package com.openpayd.exchange.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CurrencyConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceCurrency;
    private String targetCurrency;
    private double amount;
    private double convertedAmount;
    private String transactionId;
    private LocalDateTime transactionDate;
}
