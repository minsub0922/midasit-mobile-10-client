package com.example.minseop.midasit.ui.admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.Order;
import com.example.minseop.midasit.model.OrderResponse;
import com.example.minseop.midasit.retrofit.OrderService;
import com.example.minseop.midasit.ui.adapter.ReservationRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationManagementFragment extends Fragment {

    private static final String TAG = ReservationManagementFragment.class.getSimpleName();

    private RecyclerView doingRecycler;
    private RecyclerView.Adapter doingRecyclerAdapter;
    private RecyclerView doneRecycler;
    private RecyclerView.Adapter doneRecyclerAdapter;
    private Button toggleButton;

    private boolean showingDoneRecycler = false;
    private final List<Order> doingOrders = new ArrayList<>();
    private final List<Order> doneOrders = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_reservation_management, container, false);

        setupDoingRecycler(view);
        setupDoneRecycler(view);
        setupToggleButton(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MidasCafeConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(OrderService.class)
                .getAllReservations()
                .enqueue(new Callback<OrderResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderResponse> call, @NonNull Response<OrderResponse> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        final OrderResponse orderResponse = response.body();
                        if (orderResponse == null) {
                            // TODO(@gihwan)
                        } else {
                            doingOrders.clear();
                            doingOrders.addAll(orderResponse.getOrders());

                            doingRecyclerAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderResponse> call, @NonNull Throwable t) {
                        // TODO(@gihwan)
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
    }

    private void setupDoingRecycler(@NonNull View view) {
        doingRecycler = view.findViewById(R.id.admin_reservation_management_doing_recycler);
        doingRecycler.setHasFixedSize(true);
        doingRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        doingRecyclerAdapter = new ReservationRecyclerAdapter(doingOrders);
        doingRecycler.setAdapter(doingRecyclerAdapter);
    }

    private void setupDoneRecycler(@NonNull View view) {
        doneRecycler = view.findViewById(R.id.admin_reservation_management_done_recycler);
        doneRecycler.setHasFixedSize(true);
        doneRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        doneRecyclerAdapter = new ReservationRecyclerAdapter(doneOrders);
        doneRecycler.setAdapter(doneRecyclerAdapter);
    }

    private void setupToggleButton(@NonNull View view) {
        toggleButton = view.findViewById(R.id.admin_reservation_management_toggle_button);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showingDoneRecycler) {
                    showingDoneRecycler = false;
                    doneRecycler.setVisibility(View.GONE);
                    toggleButton.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,
                            R.drawable.baseline_expand_more_24, 0);
                } else {
                    showingDoneRecycler = true;
                    doneRecycler.setVisibility(View.VISIBLE);
                    toggleButton.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,
                            R.drawable.baseline_expand_less_24, 0);
                }
            }
        });
    }

}
