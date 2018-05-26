package com.example.minseop.midasit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minseop.midasit.Activity.MenuDetailActivity;
import com.example.minseop.midasit.Item.MenuItem;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.adapter.MenuItemRecyclerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minseop on 2018-05-26.
 */

public class MenuListFragment extends Fragment implements View.OnClickListener{
    private MenuItemRecyclerAdapter adapter1, adapter2, adapter3;
    private List<MenuItem> data1 = new ArrayList<>(),  data2 = new ArrayList<>(),  data3 = new ArrayList<>();
    private RecyclerView recyclerView1, recyclerView2, recyclerView3;
    private static final String TAG = "Success????";
    TextView more1, more2, more3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menulist,container, false);
        Log.d(TAG, String.valueOf(getActivity()));

        more1 = view.findViewById(R.id.more1);
        more2 = view.findViewById(R.id.more2);
        more3 = view.findViewById(R.id.more3);

        more1.setOnClickListener(this);
        more2.setOnClickListener(this);
        more3.setOnClickListener(this);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager3
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerview1);
            adapter1 = new MenuItemRecyclerAdapter(getActivity(), data1);
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(layoutManager1);

        recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerview2);
        adapter2 = new MenuItemRecyclerAdapter(getActivity(), data2);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(layoutManager2);

        recyclerView3 = (RecyclerView) view.findViewById(R.id.recyclerview3);
        adapter3 = new MenuItemRecyclerAdapter(getActivity(), data3);
        recyclerView3.setAdapter(adapter3);
        recyclerView3.setLayoutManager(layoutManager3);




        for (int i=0; i<5; i++){
            MenuItem item = new MenuItem("",  "아메리카노"+i+"번", 3000);
            data1.add(item);
        }
        for (int i=0; i<5; i++){
            MenuItem item = new MenuItem("",  "아메리카노"+i+"번", 3000);
            data2.add(item);
        }

        for (int i=0; i<5; i++){
            MenuItem item = new MenuItem("",  "아메리카노"+i+"번", 3000);
            data3.add(item);
        }

        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();

        return view;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MenuDetailActivity.class);
        if (v.getId()==R.id.more1){
            intent.putExtra("title","Coffee");
        }else if(v.getId() ==R.id.more2){
            intent.putExtra("title","Tea");
        }else if (v.getId() == R.id.more3){
            intent.putExtra("title","Beverage");
        }
        startActivity(intent);
    }
}
