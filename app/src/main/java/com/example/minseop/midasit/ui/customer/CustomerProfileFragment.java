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
import com.example.minseop.midasit.model.ReservationStatusModel;
import com.example.minseop.midasit.ui.adapter.CustomerProfileRecyclerAdpater;
import com.example.minseop.midasit.ui.adapter.CustomerShoppingCartRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerProfileFragment extends Fragment {

    RecyclerView recyclerView1, recyclerView2;
    private RecyclerView.Adapter adapter1, adapter2;
    private final List<ReservationStatusModel> modelList1 = new ArrayList<>(), modelList2 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        recyclerView1 = view.findViewById(R.id.recyclerview_profile1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView1.setNestedScrollingEnabled(false);

        adapter1 = new CustomerProfileRecyclerAdpater(modelList1);
        recyclerView1.setAdapter(adapter1);

        recyclerView2 = view.findViewById(R.id.recyclerview_profile2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter2 = new CustomerProfileRecyclerAdpater(modelList2);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setNestedScrollingEnabled(false);

        modelList1.add(new ReservationStatusModel("HiHI", "", "24000","14일 금요일"));
        modelList1.add(new ReservationStatusModel("HiHI", "", "24000","14일 금요일"));
        modelList1.add(new ReservationStatusModel("HiHI", "", "24000","14일 금요일"));
        modelList1.add(new ReservationStatusModel("HiHI", "", "24000","14일 금요일"));
        modelList1.add(new ReservationStatusModel("HiHI", "", "24000","14일 금요일"));

        adapter1.notifyDataSetChanged();
        return view;
    }

}
