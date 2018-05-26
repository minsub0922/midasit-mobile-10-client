package com.example.minseop.midasit.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.minseop.midasit.Item.MenuItem;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.adapter.MenuItemRecyclerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minseop on 2018-05-26.
 */

public class MenuListFragment extends Fragment {
    private MenuItemRecyclerAdapter adapter;
    private List<MenuItem> data = new ArrayList<>();
    private RecyclerView recyclerView;
    private static final String TAG = "Success????";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menulist,null);
        Log.d(TAG, "done???");

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        adapter = new MenuItemRecyclerAdapter(getActivity(), data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        for (int i=0; i<5; i++){
            MenuItem item = new MenuItem("",  "아메리카노"+i+"번", 3000+1000*i);
            data.add(item);
        }

        adapter.notifyDataSetChanged();

        return view;
    }


}
