package com.example.minseop.midasit.retrofit;

import com.example.minseop.midasit.model.ShoppingCartModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by WON on 2018-05-26.
 */

public interface ShoppingCartService {

    @POST("/shoppinglist")
    Call<ShoppingCartModel> add(@Body ShoppingCartModel shoppingCartModel);

}
