package com.example.minseop.midasit.ui.admin;

import android.Manifest;
import android.app.Activity;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.minseop.midasit.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by minseop on 2018-05-26.
 */

public class DetailedMenuManagementActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout linearName, linearCategory, linearPrice;
    private ImageView imageView;
    private final int REQ_CAMERA_SELECT = 100;
    int id ;
    private Button btn_submit;
    String price, category, name;
    TextView txt_name, txt_price, txt_category;
    int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_detail);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        price = String.valueOf( intent.getIntExtra("price",0));
        category = intent.getStringExtra("category");
        name = intent.getStringExtra("name");


        imageView = findViewById(R.id.img_admin_menu_detail);
        imageView.setOnClickListener(this);
        btn_submit = findViewById(R.id.btn_admin_menu_detail_submit);
        btn_submit.setOnClickListener(this);

        txt_name = findViewById(R.id.txt_name_admin_menu_detail);
        txt_category= findViewById(R.id.txt_category_admin_menu_detail);
        txt_price= findViewById(R.id.txt_price_admin_menu_detail);

        if (id == -1 ) {
            type = 2;
            category="";
            name="";
            price="";
            btn_submit.setText("제품 추가하기");
        }

        txt_name.setText(name);
        txt_category.setText(category);
        txt_price.setText(price);

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
            if (type==1) getSupportActionBar().setTitle("상품 정보 변경하기");
            else if(type ==2) getSupportActionBar().setTitle("상품 추가 하기");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (type==1) getMenuInflater().inflate(R.menu.toolbar_admin_menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if (itemId == android.R.id.home){
            finish();
        }else if (itemId == R.id.toolbar_action_trash) {
            //delete in DB
            AlertDialog.Builder builder = new AlertDialog.Builder(DetailedMenuManagementActivity.this);
            builder.setTitle("영구 삭제하시겠습니까 ?");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_admin_menu_detail){

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {

                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                }
            }

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, REQ_CAMERA_SELECT);


        }else if (v.getId() == R.id.btn_admin_menu_detail_submit){
            //DB 작업 및 액티비티 종료

            if (type==1){   //갱신

            }else if (type==2){ //추가

            }
        }
        else {
            Intent intent = new Intent(DetailedMenuManagementActivity.this, TextUpdateActivity.class);
            int reqCode = 0;
            if (v.getId() == R.id.linear_name_admin_menu_detail) {
                intent.putExtra("status", "상품명");
                intent.putExtra("txt", name);
                reqCode = 1;
            } else if (v.getId() == R.id.linear_category_admin_menu_detail) {
                intent.putExtra("status", "카테고리");
                intent.putExtra("txt", category);
                reqCode = 2;
            } else if (v.getId() == R.id.linear_price_admin_menu_detail) {
                intent.putExtra("status", "가격");
                intent.putExtra("txt", price);
                reqCode = 3;
            }
            startActivityForResult(intent, reqCode);
        }
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
        }else if (requestCode == 100){
                if (resultCode == RESULT_OK){
                    try{
                        Glide.with(this).load(getRealPathFromURI(data.getData())).into(imageView);
                        getBase64(imageView.getDrawable());
                    }catch (Exception e){}
                }
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        int column_index=0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }
        return cursor.getString(column_index);
    }

    private String getBase64(Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encoded;
    }
}
