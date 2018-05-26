package com.example.minseop.midasit.ui.customer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.MenuCategory;
import com.example.minseop.midasit.model.MenuListResponseModel;
import com.example.minseop.midasit.model.MenuModel;
import com.example.minseop.midasit.retrofit.MenuService;
import com.example.minseop.midasit.ui.adapter.MenuItemGridAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by minseop on 2018-05-26.
 */

public class MenuDetailActivity extends AppCompatActivity {

    private static final String TAG = MenuDetailActivity.class.getSimpleName();

    private Intent intent;
    private RecyclerView recyclerView;
    private MenuItemGridAdapter adapter;
    private List<MenuModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerView = findViewById(R.id.recyclerview);

        adapter = new MenuItemGridAdapter(MenuDetailActivity.this, data);
        recyclerView.setAdapter(adapter);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MenuDetailActivity.this, 2);
        recyclerView.setLayoutManager(mGridLayoutManager);

        intent = getIntent();
        setupAppBar();
    }

    @Override
    protected void onStart() {
        super.onStart();

        final MenuCategory category = MenuCategory.valueOf(getIntent().getStringExtra("category"));
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MidasCafeConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final MenuService menuService = retrofit.create(MenuService.class);
        menuService.getAllMenuByCategory(category)
                .enqueue(new Callback<MenuListResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MenuListResponseModel> call, @NonNull Response<MenuListResponseModel> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        final MenuListResponseModel menuListResponse = response.body();
                        if (menuListResponse == null) {
                            // TODO(@gihwan): check error
                        } else {
                            final List<MenuModel> menus = menuListResponse.getMenus();
                            if (menus == null) {
                                // TODO(@gihwan): check error
                            } else {
                                data.clear();
                                data.addAll(menus);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MenuListResponseModel> call, @NonNull Throwable t) {
                        // TODO(@gihwan): check error
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
    }

    private void setupAppBar() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#673AB7"));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(intent.getStringExtra("title"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
