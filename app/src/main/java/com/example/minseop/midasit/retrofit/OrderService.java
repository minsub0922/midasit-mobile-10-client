package com.example.minseop.midasit.retrofit;

import com.example.minseop.midasit.model.OrderResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderService {

    @GET("/order/reservations")
    Call<OrderResponse> getAllReservations();

    @GET("/order/reservation/userid/{userId}")
    Call<OrderResponse> getAllReserationByUserId(@Path("userId") int userId);

    @GET("/order/completeds")
    Call<OrderResponse> getAllCompleteds();

}
