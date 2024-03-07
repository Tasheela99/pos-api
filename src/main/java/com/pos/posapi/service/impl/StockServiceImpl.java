package com.pos.posapi.service.impl;

import com.pos.posapi.dto.StockDto;
import com.pos.posapi.dto.requestdto.RequestStockDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.enity.Item;
import com.pos.posapi.enity.Stock;
import com.pos.posapi.exception.EntryNotFoundException;
import com.pos.posapi.repo.StockRepo;
import com.pos.posapi.service.StockService;
import com.pos.posapi.util.mapper.StockMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepo stockRepo;
    private final StockMapper stockMapper;

    public StockServiceImpl(StockRepo stockRepo, StockMapper stockMapper) {
        this.stockRepo = stockRepo;
        this.stockMapper = stockMapper;
    }

    @Override
    public CommonResponseDTO createStock(RequestStockDto stockDto) {
        StockDto stockDto1 = new StockDto(
                stockDto.getName(),
                stockDto.getQty());
        if (!stockRepo.existsById(stockDto1.getStock_id())) {
            stockRepo.save(stockMapper.toStock(stockDto1));
        }
        return new CommonResponseDTO(
                201,
                "STOCK CREATED SUCCESSFULLY",
                stockDto1.getStock_id(),
                new ArrayList<>());
    }

    @Override
    public StockDto getStockById(int id) {
        Stock stock = stockRepo.getById(id);
        return stockMapper.toStockDto(stock);
    }

    @Override
    public CommonResponseDTO deleteStock(int id) {
        if (!stockRepo.existsById(id)) {
            throw new EntryNotFoundException("Stock Not Found");
        }

        stockRepo.deleteById(id);
        return new CommonResponseDTO(
                204,
                "Stock Deleted",
                "",
                new ArrayList<>());
    }

    @Override
    public List<StockDto> getStocks() {
        List<Stock> stocks = stockRepo.findAll();
        if (stocks.isEmpty()) {
            throw new EntryNotFoundException("No Stocks Found");
        }
        return stockMapper.toStockDtoList(stocks);
    }
}
