package com.pos.posapi.controller;

import com.pos.posapi.dto.ItemDto;
import com.pos.posapi.dto.StockDto;
import com.pos.posapi.dto.requestdto.RequestStockDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.service.StockService;
import com.pos.posapi.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity<StandardResponse> createStock(
            @RequestBody RequestStockDto stockDto
    ){
        CommonResponseDTO commonResponseDTO = stockService.createStock(stockDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(), commonResponseDTO.getMessage(), commonResponseDTO.getData()
                ), HttpStatus.CREATED
        );
    }
    @GetMapping(path = "/by-id",params = {"id"})
    public ResponseEntity<StandardResponse> getStockById(
            @RequestParam(value = "id") int id
    ) {
        StockDto stockDto = stockService.getStockId(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "ALl Stock of Id " + id,
                        stockDto
                ), HttpStatus.OK
        );
    }
}
