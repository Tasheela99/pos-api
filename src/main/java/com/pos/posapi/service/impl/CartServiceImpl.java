package com.pos.posapi.service.impl;

import com.pos.posapi.dto.CartDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.repo.CartRepo;
import com.pos.posapi.service.CartService;

import com.pos.posapi.util.mapper.CartMapper;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;
    private final CartMapper cartMapper;


    public CartServiceImpl(CartRepo cartRepo, CartMapper cartMapper) {
        this.cartRepo = cartRepo;
        this.cartMapper = cartMapper;
    }

    @Override
    public CommonResponseDTO initializeCart() {
        CartDto cartDto = new CartDto(
                1,
                "POS SYSTEM CART"
        );
        if (!cartRepo.existsById(cartDto.getCartId())){
            cartRepo.save(cartMapper.toCart(cartDto));
        }
        return new CommonResponseDTO(
                201,
                "CART AUTOMATICALLY CREATED",
                cartDto.getCartId(),
                new ArrayList<>()
        );
    }
}
