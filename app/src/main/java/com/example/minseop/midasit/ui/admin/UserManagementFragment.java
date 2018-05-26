package com.example.minseop.midasit.ui.admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.UserResponseModel;
import com.example.minseop.midasit.retrofit.AccountManagementService;
import com.example.minseop.midasit.ui.adapter.UserRecyclerAdapter;
import com.example.minseop.midasit.user.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserManagementFragment extends Fragment {

    private static final String TAG = UserManagementFragment.class.getSimpleName();

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton addButton;

    private final List<User> users = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user_management, container, false);

        setupRecycler(view);
        setupAddButton(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MidasCafeConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofit.create(AccountManagementService.class)
                .getAllCustomers()
                .enqueue(new Callback<UserResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UserResponseModel> call, @NonNull Response<UserResponseModel> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        final UserResponseModel userResponse = response.body();
                        if (userResponse == null) {
                            // TODO(@gihwa)
                        } else {
                            users.clear();
                            users.addAll(userResponse.getAccounts());
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserResponseModel> call, @NonNull Throwable t) {
                        // TODO(@gihwan)
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
    }

    private void setupRecycler(@NonNull final View view) {
        recycler = view.findViewById(R.id.admin_user_management_recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 4));

        adapter = new UserRecyclerAdapter(users);
        recycler.setAdapter(adapter);
    }

    private void setupAddButton(@NonNull final View view) {
        addButton = view.findViewById(R.id.admin_user_management_add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
