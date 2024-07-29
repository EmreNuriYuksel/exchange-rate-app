package com.openpayd.exchange.mapper;

import com.openpayd.exchange.dto.ConversionResponseDto;
import com.openpayd.exchange.response.ConversionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConversionResponseMapper {

    ConversionResponse toResponse(ConversionResponseDto conversionResponseDto);
}
