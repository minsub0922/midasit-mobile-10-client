package com.example.minseop.midasit.retrofit;

import com.example.minseop.midasit.model.Account;
import com.example.minseop.midasit.model.AccountResponse;
import com.example.minseop.midasit.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccountManagementService {

    @GET("/account/all")
    Call<AccountResponse> getAllAccounts();

    @GET("/account/all/customer")
    Call<AccountResponse> getAllCustomers();

    @GET("/account/all/administrator")
    Call<AccountResponse> getAllAdmins();

    @GET("/account/all/superviser")
    Call<AccountResponse> getAllSupervisers();

    @POST("/account")
    Call<ResponseModel> insertAccount(@Body Account account);

//    @PUT("/account/password/userid/{userId}")
//    Call<ResponseModel> updatePassword(@Path("userId") int userId);
//
//    @PUT("/account/username/userid/{userId}")
//    Call<ResponseModel> updateUsername(@Path("userId") int userId);
//
//    @PUT("/account/admin/userid/{userId}")
//    Call<ResponseModel> updateAdmin(@Path("userId") int userId);

    @DELETE("/account/userid/{userId}")
    Call<ResponseModel> deleteAccount(@Path("userId") int userId);

}
