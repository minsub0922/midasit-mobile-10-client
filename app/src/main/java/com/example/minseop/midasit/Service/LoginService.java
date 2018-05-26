package com.example.minseop.midasit.Service;

/**
 * Created by minseop on 2018-05-25.
 */
import android.text.Editable;

import com.example.minseop.midasit.Item.SimpleUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginService {
    @POST("/login/{userid}/{password}")
    Call<List<SimpleUser>> listUser(@Path("userid") Editable uid, @Path("password") Editable pwd);
}