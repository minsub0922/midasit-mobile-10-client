package com.example.minseop.midasit.Service;

/**
 * Created by minseop on 2018-05-25.
 */

import android.text.Editable;

import com.example.minseop.midasit.Item.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SignupService{
    @GET("/signup/{userid}/{password}/{username}")
    Call<List<User>> listUser(@Path("userid") Editable uid, @Path("password") Editable pwd, @Path("username") Editable username);
}
