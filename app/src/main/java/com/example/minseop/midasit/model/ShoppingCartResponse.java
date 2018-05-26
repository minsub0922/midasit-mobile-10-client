package com.example.minseop.midasit.model;

import java.util.List;

public class ShoppingCartResponse extends ResponseModel {

    private List<ShoppingCartItem> shoppingcart;

    public List<ShoppingCartItem> getShoppingcart() {
        return shoppingcart;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShoppingCartResponse{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append(", shoppingcart=").append(shoppingcart);
        sb.append('}');
        return sb.toString();
    }
}
