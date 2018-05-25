package com.example.minseop.midasit.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.minseop.midasit.R;

/**
 * Created by minseop on 2018-05-25.
 */

public class TestActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Test!");
    }
}
