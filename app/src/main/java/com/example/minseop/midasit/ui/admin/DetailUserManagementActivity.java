package com.example.minseop.midasit.ui.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.Account;
import com.example.minseop.midasit.model.AuthRequest;
import com.example.minseop.midasit.model.ResponseModel;
import com.example.minseop.midasit.retrofit.AccountManagementService;
import com.example.minseop.midasit.retrofit.AuthService;

import org.joda.time.DateTime;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailUserManagementActivity extends AppCompatActivity {

    EditText username,password,employeenumber;
    Switch adminSwitch;
    Button deleteButton, fixButton, cancelButton;
    int id,admin;
    String Spassword, Susername,SemployeeNumber;
    private static final String TAG = DetailUserManagementActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_management);

        setupWidget();
        initVal();
    }

    void initVal()
    {
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        Spassword = intent.getStringExtra("password");
        Susername = intent.getStringExtra("username");
        SemployeeNumber = intent.getStringExtra("employeenumber");

        adminSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton button, boolean isChecked) {
            // TODO(@gihwan)
            if (isChecked) {
                admin = 1;
            } else {
                admin = 0;
            }
        }
    });

        username.setText(Susername);
        password.setText(Spassword);
        employeenumber.setText(SemployeeNumber);
    }


    void setupWidget()
    {
        username = findViewById(R.id.user_name_editText);
        password = findViewById(R.id.password_editText);
        employeenumber = findViewById(R.id.employee_number_editText);
        adminSwitch = findViewById(R.id.PerSwitch);
        deleteButton = findViewById(R.id.detail_delete);
        fixButton = findViewById(R.id.detail_fix);
        cancelButton = findViewById(R.id.detail_cancel);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MidasCafeConstants.SERVER_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final AccountManagementService accountManagementService = retrofit.create(AccountManagementService.class);
                final Call<ResponseModel> responseModelCall = accountManagementService.deleteAccount(id);
                responseModelCall.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        Intent intent = new Intent(DetailUserManagementActivity.this, AdminMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
            }
        });

        fixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SemployeeNumber = employeenumber.getText().toString();
                Susername = username.getText().toString();
                Account account = new Account(id,SemployeeNumber,Susername,admin, DateTime.now());

                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MidasCafeConstants.SERVER_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final AccountManagementService accountManagementService = retrofit.create(AccountManagementService.class);
                final Call<ResponseModel> responseModelCall = accountManagementService.deleteAccount(id);
                responseModelCall.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        Intent intent = new Intent(DetailUserManagementActivity.this, AdminMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });

                final Call<ResponseModel> responseModelCall2 = accountManagementService.insertAccount(account);
                responseModelCall2.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        Intent intent = new Intent(DetailUserManagementActivity.this, AdminMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),AdminMainActivity.class);
                startActivity(intent);
            }
        });
    }

}
