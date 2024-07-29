package com.openpayd.exchange.repository;

import com.openpayd.exchange.entity.CurrencyConversionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface CurrencyConversionHistoryRepository extends JpaRepository<CurrencyConversionHistory, UUID> {

    List<CurrencyConversionHistory> findByTransactionId(UUID transactionId);

    List<CurrencyConversionHistory> findByTransactionDateBetween(Instant startDate, Instant endDate);
}
