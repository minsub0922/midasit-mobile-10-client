package com.example.minseop.midasit.retrofit;

import com.example.minseop.midasit.model.MenuCategory;
import com.example.minseop.midasit.model.MenuListResponseModel;
import com.example.minseop.midasit.model.MenuModel;
import com.example.minseop.midasit.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MenuService {

    @GET("/menu/category/{category}")
    Call<MenuListResponseModel> getAllMenuByCategory(@Path("category") MenuCategory category);

    @POST("/menu")
    Call<ResponseModel> insertMenu(@Body MenuModel menu);

}
