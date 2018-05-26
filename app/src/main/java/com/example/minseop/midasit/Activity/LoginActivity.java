package com.example.minseop.midasit.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minseop.midasit.Item.SimpleUser;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.Service.LoginService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String BASE = "192.168.0.13:3001";

    Button btn_login;
    TextView txt_info, txt_signup;
    EditText edt_id, edt_pw;
    String id = null,pwd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        edt_id = findViewById(R.id.id);
        edt_pw = findViewById(R.id.pw);
        txt_info =findViewById(R.id.txt_info);
        txt_signup = findViewById(R.id.txt_signup);

        btn_login.setOnClickListener(this);
        txt_signup.setOnClickListener(this);
        txt_signup.setText(Html.fromHtml("<u>" + "Click" + "</u>"));
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_login) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LoginService service = retrofit.create(LoginService.class);
            Call<List<SimpleUser>> call = service.listUser(edt_id.getText(), edt_pw.getText());
            call.enqueue(simpleUsers);
        }else if(view.getId()== R.id.txt_signup){
            startActivityForResult(new Intent(LoginActivity.this, SignupActivity.class),1);
        }
    }

    Callback simpleUsers = new Callback<List<SimpleUser>>(){
        @Override
        public void onResponse(Call<List<SimpleUser>> call, Response<List<SimpleUser>> response) {
            if (response.isSuccessful()) {
                List<SimpleUser> users = response.body();
                StringBuilder builder = new StringBuilder();
                for (SimpleUser user : users) {
                    builder.append(user.getId().toString()+"\n");
                }
                if (users.get(0).getId().contains("there is no account")){
                    Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다. ID/PW 를 확인하십시오.",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            } else
            {
                Toast.makeText(LoginActivity.this, "ID/PW 를 모두 입력하십시오.", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<List<SimpleUser>> call, Throwable t) {
            txt_info.setText("Fail");
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                id=data.getStringExtra("userid");
                pwd= data.getStringExtra("password");
                txt_info.setText("회원가입 성공적");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(id != null && pwd != null)
        {
            edt_id.setText(id,TextView.BufferType.EDITABLE);
            edt_pw.setText(pwd,TextView.BufferType.EDITABLE);
        }
    }
}
