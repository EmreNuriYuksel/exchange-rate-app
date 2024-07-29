package com.openpayd.exchange.mapper;

import com.openpayd.exchange.dto.CurrencyConversionHistoryDto;
import com.openpayd.exchange.entity.CurrencyConversionHistory;
import com.openpayd.exchange.response.CurrencyConversionHistoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CurrencyConversionHistoryMapper {

    List<CurrencyConversionHistoryDto> toDtoList(List<CurrencyConversionHistory> currencyConversionHistoryList);

    List<CurrencyConversionHistoryResponse> toResponseList(
            List<CurrencyConversionHistoryDto> currencyConversionHistoryDtoList);
}
