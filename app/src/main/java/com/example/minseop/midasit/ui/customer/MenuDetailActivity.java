package com.example.minseop.midasit.ui.customer;

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

    private MenuCategory category;
    private RecyclerView recyclerView;
    private MenuItemGridAdapter adapter;
    private List<MenuModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        category = MenuCategory.valueOf(getIntent().getStringExtra("category"));

        setupAppBar();
        setupRecycler();
    }

    @Override
    protected void onStart() {
        super.onStart();

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
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            switch (category) {
                case COFFEE:
                    getSupportActionBar().setTitle(R.string.coffee);
                    break;
                case TEA:
                    getSupportActionBar().setTitle(R.string.tea);
                    break;
                case BEVERAGE:
                    getSupportActionBar().setTitle(R.string.beverage);
                    break;
            }
        }
    }

    private void setupRecycler() {
        recyclerView = findViewById(R.id.detail_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(MenuDetailActivity.this, 2));

        adapter = new MenuItemGridAdapter(MenuDetailActivity.this, data);
        recyclerView.setAdapter(adapter);
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
