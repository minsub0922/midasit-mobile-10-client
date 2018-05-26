package com.example.minseop.midasit.retrofit;

import com.example.minseop.midasit.model.ShoppingCartItem;
import com.example.minseop.midasit.model.ShoppingCartResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by WON on 2018-05-26.
 */

public interface ShoppingCartService {

    @POST("/shoppingcart/{userId}/add")
    Call<ShoppingCartItem> addShoppingCartItem(@Path("userId") String userId, @Body ShoppingCartItem shoppingCartItem);

    @GET("/shoppingcart/userid/{userId}")
    Call<ShoppingCartResponse> requestShoppingCartItemList(@Path("userId") int userId);

}
