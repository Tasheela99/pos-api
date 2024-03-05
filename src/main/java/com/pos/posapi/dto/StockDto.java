package com.pos.posapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StockDto {
    private int stock_id;

    private String name;

    public StockDto(String name) {
        this.name = name;
    }
}
