package com.example.minseop.midasit.ui.customer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.minseop.midasit.MidasCafeApplication;
import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.Order;
import com.example.minseop.midasit.model.OrderResponse;
import com.example.minseop.midasit.retrofit.OrderService;
import com.example.minseop.midasit.ui.adapter.CustomerShoppingCartRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerShoppingCartActivity extends AppCompatActivity {

    private static final String TAG = CustomerShoppingCartActivity.class.getSimpleName();

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private final List<Order> shoppingCart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_shopping_cart);

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
        retrofit.create(OrderService.class)
                .getAllShoppingCartByUserId(MidasCafeApplication.getInstance().getAuthModel().getId())
                .enqueue(new Callback<OrderResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderResponse> call, @NonNull Response<OrderResponse> response) {
                        final OrderResponse orderResponse = response.body();
                        if (orderResponse == null) {
                            Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        } else {
                            shoppingCart.clear();
                            shoppingCart.addAll(orderResponse.getOrders());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderResponse> call, @NonNull Throwable t) {
                        // TODO(@gihwan): check error
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupAppBar() {
        Toolbar toolbar = findViewById(R.id.customer_shopping_cart_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupRecycler() {
        recycler = findViewById(R.id.customer_shopping_cart_recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustomerShoppingCartRecyclerAdapter(shoppingCart);
        recycler.setAdapter(adapter);
    }
}
