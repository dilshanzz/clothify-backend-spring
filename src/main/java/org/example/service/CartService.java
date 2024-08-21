package org.example.service;

import org.example.dto.CartDto;
import org.example.entity.Cart;

import java.util.List;

public interface CartService {
    boolean addCart(CartDto cartDto);

    List<CartDto> getAllCartDetails();

    boolean updateStatus(long id);

    CartDto getCartById(long id);

    List<CartDto> getCartByCustomer(long id);

    Boolean deleteItemById(long id);

    Boolean updateAddCart(Long id);

    Boolean updateSubCart(Long id);
}
