package com.example.minseop.midasit.Service;

import com.example.minseop.midasit.model.AuthModel;
import com.example.minseop.midasit.model.ShoppingListModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by WON on 2018-05-26.
 */

public interface ShoppingListService {

    @POST("/shoppinglist")
    Call<ShoppingListModel> add(@Body ShoppingListModel shoppingListModel);

}
