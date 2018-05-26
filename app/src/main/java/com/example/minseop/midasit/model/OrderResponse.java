package com.example.minseop.midasit.model;

import java.util.List;

public class OrderResponse extends ResponseModel {

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderResponse{");
        sb.append("time=").append(time);
        sb.append(", success=").append(success);
        sb.append(", orders=").append(orders);
        sb.append('}');
        return sb.toString();
    }
}
