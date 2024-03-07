package com.pos.posapi.service;

import com.pos.posapi.dto.StockDto;
import com.pos.posapi.dto.requestdto.RequestStockDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface StockService {
    CommonResponseDTO createStock(RequestStockDto stockDto);

    StockDto getStockById(int id);

    CommonResponseDTO deleteStock(int id);

    List<StockDto> getStocks();
}
