package com.pos.posapi.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseItemDto {
    private int itemId;
    private String itemName;
    private int itemQuantity;
    private double itemUnitPrice;
}
