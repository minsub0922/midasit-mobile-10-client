package com.example.minseop.midasit.retrofit;

import com.example.minseop.midasit.model.Order;
import com.example.minseop.midasit.model.OrderResponse;
import com.example.minseop.midasit.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrderService {

    @GET("/order/all")
    Call<OrderResponse> getAllOrders();

    @GET("/order/all/shoppingcart")
    Call<OrderResponse> getAllShoppingCart();

    @GET("/order/all/reservation")
    Call<OrderResponse> getAllReservations();

    @GET("/order/all/completed")
    Call<OrderResponse> getAllCompletedOrders();

    @GET("/order/all/userid/{userId}")
    Call<OrderResponse> getAllOrdersByUserId(@Path("userId") int userId);

    @GET("/order/all/shoppingcart/userid/{userId}")
    Call<OrderResponse> getAllShoppingCartByUserId(@Path("userId") int userId);

    @GET("/order/all/reservation/userid/{userId}")
    Call<OrderResponse> getAllReservationsByUserId(@Path("userId") int userId);

    @GET("/order/all/completed/userid/{userId}")
    Call<OrderResponse> getAllCompletedOrdersByUserId(@Path("userId") int userId);

    @POST("/order")
    Call<ResponseModel> insertOrder(@Body Order order);

    @PUT("/orderid/{orderId}")
    Call<ResponseModel> updateOrder(@Path("orderId") int orderId, @Body Order order);

    @DELETE("/orderid/{orderId}")
    Call<ResponseModel> deleteOrder(@Path("orderId") int orderId);

}
