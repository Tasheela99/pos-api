package com.pos.posapi.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestItemSaveDto {
    private String itemName;
    private String itemDescription;
    private int itemQuantity;
    private double itemUnitPrice;
    private Date itemManufacturedDate;
    private Date itemExpiredDate;
    private boolean activeState;
}
