package com.pos.posapi.enity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_address")
    private String customerAddress;
    @Column(name = "customer_mobile")
    private String customerMobile;
    @Column(name = "activeState")
    private boolean activeState;
}
