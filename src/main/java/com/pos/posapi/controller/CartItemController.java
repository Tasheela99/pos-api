package com.pos.posapi.controller;

import com.pos.posapi.dto.CartItemDto;
import com.pos.posapi.dto.ItemDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.service.CartItemService;
import com.pos.posapi.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-item")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping(params = {"itemId", "cartId", "quantity"})
    public ResponseEntity<StandardResponse> addItemToCart(
            @RequestParam(value = "itemId") int itemId,
            @RequestParam(value = "cartId") int cartId,
            @RequestParam(value = "quantity") int quantity,
            @RequestHeader("Authorization") String token
    ) {
        CommonResponseDTO commonResponseDTO = cartItemService.addItemToCart(itemId, cartId, quantity, token);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.CREATED
        );
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<StandardResponse> deleteCartItem(
            @RequestParam(value = "id") int id
    ) {
        CommonResponseDTO commonResponseDTO = cartItemService.deleteCartItem(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        commonResponseDTO.getCode(),
                        commonResponseDTO.getMessage(),
                        commonResponseDTO.getData()
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<StandardResponse> getCartItemById(
            @RequestParam(value = "id") int id
    ) {
        CartItemDto cartItemDto = cartItemService.getCartItemById(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "ALl Items of Id " + id,
                        cartItemDto
                ), HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<StandardResponse> get(){
        List<CartItemDto> carttItemDtoList = cartItemService.getCartItems();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Cart Items",
                        carttItemDtoList
                ), HttpStatus.OK
        );
    }
}
