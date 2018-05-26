package com.example.minseop.midasit.retrofit;

import com.example.minseop.midasit.model.UserResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountManagementService {

    @GET("/management/account/customers")
    Call<UserResponseModel> getAllCustomers();

    @GET("/management/account/admins")
    Call<UserResponseModel> getAllAdmins();

    @GET("/management/account/supervisers")
    Call<UserResponseModel> getAllSupervisers();

}
