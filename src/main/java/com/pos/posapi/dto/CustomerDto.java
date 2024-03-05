package com.pos.posapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerDto {
    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerMobile;
    private boolean activeState;

    public CustomerDto(String customerName, String customerAddress, String customerMobile, boolean activeState) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerMobile = customerMobile;
        this.activeState = activeState;
    }
}
