package com.example.minseop.midasit.ui.admin;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minseop.midasit.R;

/**
 * Created by minseop on 2018-05-26.
 */

public class DetailedMenuManagementActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout linearName, linearCategory, linearPrice;
    int id ;
    String price, category, name;
    TextView txt_name, txt_price, txt_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_detail);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        price = String.valueOf( intent.getIntExtra("price",0));
        category = intent.getStringExtra("category");
        name = intent.getStringExtra("name");


        txt_name = findViewById(R.id.txt_name_admin_menu_detail);
        txt_category= findViewById(R.id.txt_category_admin_menu_detail);
        txt_price= findViewById(R.id.txt_price_admin_menu_detail);
        txt_name.setText(name);
        txt_category.setText(category);
        txt_price.setText( price);

        linearName = findViewById(R.id.linear_name_admin_menu_detail);
        linearCategory = findViewById(R.id.linear_category_admin_menu_detail);
        linearPrice = findViewById(R.id.linear_price_admin_menu_detail);
        linearName.setOnClickListener(this);
        linearCategory.setOnClickListener(this);
        linearPrice.setOnClickListener(this);

        setupAppBar();
    }
    private void setupAppBar() {
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("상품 정보 변경하기");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_admin_menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == R.id.toolbar_action_trash) {
            //delete in DB

            AlertDialog.Builder builder = new AlertDialog.Builder(DetailedMenuManagementActivity.this);

            // Set a title for alert dialog
            builder.setTitle("영구 삭제하시겠습니까 ?");

            // Ask the final question

            // Set the alert dialog yes button click listener
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when user clicked the Yes button
                    // Set the TextView visibility GONE
                }
            });

            // Set the alert dialog no button click listener
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when No button clicked
                }
            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DetailedMenuManagementActivity.this, TextUpdateActivity.class);
        int reqCode = 0;
        if (v.getId()==R.id.linear_name_admin_menu_detail){
            intent.putExtra("status", "상품명");
            intent.putExtra("txt", name);
            reqCode = 1;
        }else if (v.getId()==R.id.linear_category_admin_menu_detail){
            intent.putExtra("status", "카테고리");
            intent.putExtra("txt", category);
            reqCode = 2;
        }else if (v.getId()==R.id.linear_price_admin_menu_detail){
            intent.putExtra("status", "가격");
            intent.putExtra("txt", price);
            reqCode = 3;
        }
        startActivityForResult(intent,reqCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String result=data.getStringExtra("result");
                Log.d("Success????",result);
                txt_name.setText(result);
            }
        }else if (requestCode == 2){
            if (resultCode == RESULT_OK){
                String result = data.getStringExtra("result");
                txt_category.setText(result);
            }
        }else if (requestCode == 3){
            if (resultCode ==   RESULT_OK){
                String result = data.getStringExtra("result");
                txt_price.setText(result);
            }
        }
    }
}
