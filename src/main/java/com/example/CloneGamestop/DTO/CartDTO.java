package com.example.CloneGamestop.DTO;

import com.example.CloneGamestop.Model.Cart;

import java.time.LocalDateTime;

public class CartDTO {

    private Long idCart;
    private String shippingAddress;
    private LocalDateTime dateOfAddition;

    public CartDTO() {

    }

    public Long getIdCart() {
        return idCart;
    }

    public void setIdCart(Long idCart) {
        this.idCart = idCart;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(LocalDateTime dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public static CartDTO fromCart(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setIdCart(cart.getIdCart());
        cartDTO.setDateOfAddition(cart.getDateOfAddition());
        cartDTO.setShippingAddress(cart.getShippingAddress());
        return cartDTO;
    }
}
