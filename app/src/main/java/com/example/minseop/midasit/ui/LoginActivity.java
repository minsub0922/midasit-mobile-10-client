package com.example.minseop.midasit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.minseop.midasit.MidasCafeApplication;
import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.Realm.autoLoginModel;
import com.example.minseop.midasit.model.AdministratorLevel;
import com.example.minseop.midasit.model.AuthModel;
import com.example.minseop.midasit.model.AuthRequest;
import com.example.minseop.midasit.retrofit.AuthService;
import com.example.minseop.midasit.ui.admin.AdminMainActivity;
import com.example.minseop.midasit.ui.customer.CustomerMainActivity;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private TextInputEditText employeeNumber;
    private TextInputEditText password;
    private Button signinButton;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupInputs();
        setupSigninButton();
        Realm.init(this);
        checkToken();
    }

    private void checkToken()    {
        realm = Realm.getDefaultInstance();

       /* realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                autoLoginModel autoLoginModel = realm.where(autoLoginModel.class).findFirst();
                autoLoginModel.deleteFromRealm();
            }
        });*/
        autoLoginModel autoLoginModel = realm.where(autoLoginModel.class).findFirst();

        if(autoLoginModel != null)
        {
            AuthModel tmpAuthModel = new AuthModel();
            tmpAuthModel.setAdmin(autoLoginModel.getAdmin());
            tmpAuthModel.setEmployeeNumber(autoLoginModel.getEmployeeNumber());
            tmpAuthModel.setId(autoLoginModel.getId());
            tmpAuthModel.setToken(autoLoginModel.getToken());
            tmpAuthModel.setUsername(autoLoginModel.getUsername());
            MidasCafeApplication.getInstance().setAuthModel(tmpAuthModel);

            if (autoLoginModel.getAdmin() == AdministratorLevel.EMPLOYEE.getValue()) {
                Intent intent = new Intent(LoginActivity.this, CustomerMainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // 관리자
                Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }

    private void setupInputs() {
        employeeNumber = findViewById(R.id.login_employee_number);
        password = findViewById(R.id.login_password);
    }

    private void setupSigninButton() {
        signinButton = findViewById(R.id.login_signin_button);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MidasCafeConstants.SERVER_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final AuthService authService = retrofit.create(AuthService.class);
                final Call<AuthModel> signinCall = authService.signin(new AuthRequest(
                        employeeNumber.getText().toString(), password.getText().toString()));
                signinCall.enqueue(new Callback<AuthModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AuthModel> call, @NonNull Response<AuthModel> response) {
                        final AuthModel authModel = response.body();
                        if (authModel == null) {
                            Log.d(TAG, "onResponse: Failed to signin. call=" + call + ", response=" + response);
                            Snackbar.make(employeeNumber, R.string.failed_to_signin, Snackbar.LENGTH_SHORT)
                                    .show();
                        } else {
                            if (authModel.getResult()) {
                                // 로그인 성공
                                MidasCafeApplication.getInstance().setAuthModel(authModel);

                                realm.beginTransaction();
                                autoLoginModel autoLoginModel = realm.createObject(com.example.minseop.midasit.Realm.autoLoginModel.class,authModel.getId());
                                autoLoginModel.setEmployeeNumber(authModel.getEmployeeNumber());
                                autoLoginModel.setAdmin(authModel.getAdmin());
                                autoLoginModel.setUsername(authModel.getUsername());
                                autoLoginModel.setToken(authModel.getToken());
                                realm.commitTransaction();

                                if (authModel.getAdmin() == AdministratorLevel.EMPLOYEE.getValue()) {
                                    Intent intent = new Intent(LoginActivity.this, CustomerMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // 관리자
                                    Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                // 로그인 실패
                                Snackbar.make(employeeNumber, R.string.incorrect_password, Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AuthModel> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                        Snackbar.make(employeeNumber, R.string.failed_to_signin, Snackbar.LENGTH_SHORT)
                                .show();
                    }
                });
            }
        });
    }

}
