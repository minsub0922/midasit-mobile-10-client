package com.example.minseop.midasit;

import android.app.Application;
import android.util.Log;

import com.example.minseop.midasit.model.AuthModel;

public class MidasCafeApplication extends Application {

    private static final String TAG = MidasCafeApplication.class.getSimpleName();
    private static MidasCafeApplication instance;

    public static MidasCafeApplication getInstance() {
        return instance;
    }

    private AuthModel authModel;

    public AuthModel getAuthModel() {
        return authModel;
    }

    public void setAuthModel(AuthModel authModel) {
        this.authModel = authModel;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");

        instance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate() called");
    }
}
