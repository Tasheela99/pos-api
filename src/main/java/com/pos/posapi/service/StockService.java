package com.pos.posapi.service;

import com.pos.posapi.dto.StockDto;
import com.pos.posapi.dto.requestdto.RequestStockDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

public interface StockService {
    CommonResponseDTO createStock(RequestStockDto stockDto);

    StockDto getStockId(int id);
}
