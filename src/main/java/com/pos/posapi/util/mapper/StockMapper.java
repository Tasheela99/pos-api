package com.pos.posapi.util.mapper;

import com.pos.posapi.dto.StockDto;
import com.pos.posapi.enity.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockDto toStockDto(Stock stock);

    Stock toStock(StockDto stockDto1);
}
