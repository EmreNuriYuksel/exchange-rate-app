package com.openpayd.exchange.repository;

import com.openpayd.exchange.entity.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Long> {

    List<CurrencyConversion> findByTransactionId(String transactionId);

    List<CurrencyConversion> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
