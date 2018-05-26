package com.example.minseop.midasit.ui.customer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.HomeAdModel;
import com.example.minseop.midasit.ui.adapter.CustomerHomeRecyclerAdpater;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minseop on 2018-05-27.
 */

public class CustomerHomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomerHomeRecyclerAdpater adapter;
    private final List<HomeAdModel> modelList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new CustomerHomeRecyclerAdpater(modelList);
        recyclerView.setAdapter(adapter);
        modelList.add(new HomeAdModel("HiHI", "특별 할인 중 !!"));
        modelList.add(new HomeAdModel("HiHI", "특별 할인 중 !!"));
        modelList.add(new HomeAdModel("HiHI", "특별 할인 중 !!"));
        adapter.notifyDataSetChanged();

        return view;
    }
}
