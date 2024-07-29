package com.openpayd.exchange.repository;

import com.openpayd.exchange.entity.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, UUID> {

    List<CurrencyConversion> findByTransactionId(UUID transactionId);

    List<CurrencyConversion> findByTransactionDateBetween(Instant startDate, Instant endDate);
}
