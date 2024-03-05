package com.pos.posapi.service.impl;

import com.pos.posapi.dto.CartItemDto;
import com.pos.posapi.dto.responsedto.ResponseUserDataDTO;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.enity.Cart;
import com.pos.posapi.enity.CartItem;
import com.pos.posapi.enity.Item;
import com.pos.posapi.enity.User;
import com.pos.posapi.exception.EntryNotFoundException;
import com.pos.posapi.repo.CartItemRepo;
import com.pos.posapi.repo.CartRepo;
import com.pos.posapi.repo.ItemRepo;
import com.pos.posapi.repo.UserRepo;
import com.pos.posapi.service.CartItemService;
import com.pos.posapi.service.UserService;
import com.pos.posapi.util.mapper.CartItemMapper;
import com.pos.posapi.util.mapper.CartMapper;
import com.pos.posapi.util.mapper.ItemMapper;
import com.pos.posapi.util.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepo cartItemRepo;
    private final CartItemMapper cartItemMapper;
    private final ItemRepo itemRepo;
    private final CartRepo cartRepo;
    private final UserService userService;
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    public CartItemServiceImpl(CartItemRepo cartItemRepo, CartItemMapper cartItemMapper, ItemRepo itemRepo, CartRepo cartRepo, UserService userService, UserRepo userRepo, UserMapper userMapper, ItemMapper itemMapper, CartMapper cartMapper) {
        this.cartItemRepo = cartItemRepo;
        this.cartItemMapper = cartItemMapper;
        this.itemRepo = itemRepo;
        this.cartRepo = cartRepo;
        this.userService = userService;
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.itemMapper = itemMapper;
        this.cartMapper = cartMapper;
    }

//    @Override
//    @Transactional
//    public CommonResponseDTO addItemToCart(int itemId, int cartId, int quantity, String token) {
//        Optional<Item> item = itemRepo.findById(itemId);
//        if (item.isEmpty()) {
//            throw new EntryNotFoundException("No Items Found");
//        }
//        Optional<Cart> cart = cartRepo.findById(cartId);
//        if (cart.isEmpty()) {
//            throw new EntryNotFoundException("Cart Empty");
//        }
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//
//        ResponseUserDataDTO userData = userService.getAllUserData(token);
//        Optional<User> user = userRepo.findById(userData.getUserId());
//        if (user.isEmpty()) {
//            throw new EntryNotFoundException("User Not Found");
//        }
//        CartItemDto cartItemDto = new CartItemDto(
//                itemMapper.toItemDto(item.get()),
//                cartMapper.toCartDto(cart.get()),
//                userMapper.toUserDto(user.get()),
//                quantity
//        );
//        if (!cartItemRepo.existsById(cartItemDto.getCartItemId())) {
//            cartItemRepo.save(cartItemMapper.toCartItem(cartItemDto));
//        }
//
//        Optional<CartItem> cartItem = cartItemRepo.findById(cartItemDto.getCartItemId());
//        if (cartItem.isPresent()) {
//            cartItem.get().setQuantity(cartItem.get().getQuantity() + quantity);
//            cartItemRepo.save(cartItem.get());
//        }
//        return new CommonResponseDTO(
//                201,
//                "ITEM ADDED TO CART SUCCESSFULLY",
//                cartItemDto.getCartItemId(),
//                new ArrayList<>()
//        );
//
//    }

    @Override
    @Transactional
    public CommonResponseDTO addItemToCart(int itemId, int cartId, int quantity, String token) {
        Optional<Item> item = itemRepo.findById(itemId);
        if (item.isEmpty()) {
            throw new EntryNotFoundException("No Items Found");
        }

        Optional<Cart> cart = cartRepo.findById(cartId);
        if (cart.isEmpty()) {
            throw new EntryNotFoundException("Cart Empty");
        }

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        ResponseUserDataDTO userData = userService.getAllUserData(token);
        Optional<User> user = userRepo.findUserById(userData.getUserId());
        if (user.isEmpty()) {
            throw new EntryNotFoundException("User Not Found");
        }

        // Check if the cart item already exists
        Optional<CartItem> existingCartItem = cartItemRepo.findByItemIdAndCartId(itemId, cartId);
        if (existingCartItem.isPresent()) {
            // Update the quantity of the existing cart item
            existingCartItem.get().setQuantity(existingCartItem.get().getQuantity() + quantity);
            cartItemRepo.save(existingCartItem.get());
        } else {
            // Create a new cart item with the given quantity
            CartItemDto cartItemDto = new CartItemDto(
                    itemMapper.toItemDto(item.get()),
                    cartMapper.toCartDto(cart.get()),
                    userMapper.toUserDto(user.get()),
                    quantity
            );
            cartItemRepo.save(cartItemMapper.toCartItem(cartItemDto));
        }

        return new CommonResponseDTO(
                201,
                "ITEM ADDED TO CART SUCCESSFULLY",
                null, // Provide the appropriate data if needed
                new ArrayList<>()
        );
    }

    @Override
    public CommonResponseDTO deleteCartItem(int id) {
        Optional<CartItem> cartItem = cartItemRepo.findById(id);
        if (cartItem.isEmpty()) {
            throw new EntryNotFoundException("No Cart Item Found");
        }
        cartItemRepo.delete(cartItem.get());
        return new CommonResponseDTO(
                204,
                "CART ITEM DELETED SUCCESSFULLY",
                cartItem.get().getCartItemId(),
                new ArrayList<>()
        );
    }

    @Override
    public CartItemDto getCartItemById(int id) {
        CartItem cartItem = cartItemRepo.findCartItemById(id);
        return cartItemMapper.toCartItemDto(cartItem);
    }
}
