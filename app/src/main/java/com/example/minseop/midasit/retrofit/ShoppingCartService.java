package com.example.minseop.midasit.retrofit;

import com.example.minseop.midasit.model.ShoppingCartItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by WON on 2018-05-26.
 */

public interface ShoppingCartService {

    @POST("/shoppingCart/{userId}/add")
    Call<ShoppingCartItem> addShoppingCartItem(@Path("userId") String userId ,@Body ShoppingCartItem shoppingCartItem);

    @GET("/shoppingCart/{userId}")
    Call<List<ShoppingCartItem>> requestShoppingCartItemList(@Path("userId") String userId);

}
