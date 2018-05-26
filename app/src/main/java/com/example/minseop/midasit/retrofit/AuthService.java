package com.example.minseop.midasit.retrofit;

import com.example.minseop.midasit.model.AuthCheckExistenceModel;
import com.example.minseop.midasit.model.AuthModel;
import com.example.minseop.midasit.model.AuthRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthService {

    @POST("/auth/signin")
    Call<AuthModel> signin(@Body AuthRequest request);

    @POST("/auth/signup")
    Call<AuthModel> signup(@Body AuthRequest request);

    @GET("/auth/check/employeeNumber/{employeeNumber}")
    Call<AuthCheckExistenceModel> checkEmployeeNumberExistence(@Path("employeeNumber") String employeeNumber);

    @GET("/auth/check/username/{username}")
    Call<AuthCheckExistenceModel> checkUsernameExistence(@Path("username") String username);

}
