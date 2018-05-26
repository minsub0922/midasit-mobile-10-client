package com.example.minseop.midasit.Activity;

/**
 * Created by minseop on 2018-05-25.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minseop.midasit.Item.SimpleUser;
import com.example.minseop.midasit.Item.User;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.Service.IdCheckService;
import com.example.minseop.midasit.Service.SignupService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by minseop on 2018-05-25.
 */

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edt_id, edt_pw, edt_name;
    Button btn_check, btn_submit;
    private static final String BASE = "http://172.30.1.229:3001";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        edt_name = findViewById(R.id.edt_name);
        btn_check = findViewById(R.id.btn_check);
        btn_submit = findViewById(R.id.btn_submit);

        btn_check.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_check){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            IdCheckService service = retrofit.create(IdCheckService.class);
            Call<List<SimpleUser>> call = service.listUser(edt_id.getText(),edt_pw.getText());
            call.enqueue(simpleUser);

        }else if (v.getId() == R.id.btn_submit){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SignupService service = retrofit.create(SignupService.class);
            Call<List<User>> call = service.listUser(edt_id.getText(), edt_pw.getText(), edt_name.getText());
            call.enqueue(users);
        }
    }
    Callback users = new Callback<List<User>>(){
        @Override
        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
            if (response.isSuccessful()) {
                Toast.makeText(SignupActivity.this, "회원가입 되셨습니다.", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("userid",edt_id.getText());
                returnIntent.putExtra("password", edt_pw.getText());
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            } else {
            }
        }

        @Override
        public void onFailure(Call<List<User>> call, Throwable t) {
        }
    };

    Callback simpleUser = new Callback<List<SimpleUser>>(){
        @Override
        public void onResponse(Call<List<SimpleUser>> call, Response<List<SimpleUser>> response) {
            if (response.isSuccessful()) {
                List<SimpleUser> users = response.body();
                StringBuilder builder = new StringBuilder();
                for (SimpleUser user : users) {
                    builder.append(user.getId().toString()+"\n");
                }
                if (users.get(0).getId().contains("there is no account")){
                    Toast.makeText(SignupActivity.this, "사용가능한 ID 입니다.",Toast.LENGTH_SHORT).show();
                    btn_check.setText("확인");
                    btn_check.setBackgroundResource(R.drawable.button_style);
                }else {
                    Toast.makeText(SignupActivity.this, "이미 존재하는 ID입니다.",Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SignupActivity.this, "ID 를 입력하십시오.", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFailure(Call<List<SimpleUser>> call, Throwable t) {
        }
    };


}
