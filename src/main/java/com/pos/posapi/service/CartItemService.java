package com.pos.posapi.service;

import com.pos.posapi.dto.CartItemDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface CartItemService {
    CommonResponseDTO addItemToCart(int itemId, int cartId, int quantity, String token);

    CommonResponseDTO deleteCartItem(int id);
    CartItemDto getCartItemById(int id);

    List<CartItemDto> getCartItems();
}
