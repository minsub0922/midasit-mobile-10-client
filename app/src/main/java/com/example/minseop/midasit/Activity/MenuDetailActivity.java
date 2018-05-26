package com.example.minseop.midasit.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.minseop.midasit.R;
import com.example.minseop.midasit.adapter.MenuItemGridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minseop on 2018-05-26.
 */

public class MenuDetailActivity extends AppCompatActivity {
    private Intent intent;
    private RecyclerView recyclerView;
    private List<com.example.minseop.midasit.Item.MenuItem> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerView = findViewById(R.id.recyclerview);

        MenuItemGridAdapter myAdapter = new MenuItemGridAdapter(MenuDetailActivity.this, data);
        recyclerView.setAdapter(myAdapter);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MenuDetailActivity.this, 2);
        recyclerView.setLayoutManager(mGridLayoutManager);


        for (int i=0; i<5; i++){
            com.example.minseop.midasit.Item.MenuItem item = new com.example.minseop.midasit.Item.MenuItem("",  "아메리카노"+i+"번", 3000);
            data.add(item);
        }


        myAdapter.notifyDataSetChanged();

        intent = getIntent();
        setupAppBar();

    }
    private void setupAppBar() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor( "#673AB7"));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(intent.getStringExtra("title"));
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
