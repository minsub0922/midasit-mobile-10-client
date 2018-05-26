package com.example.minseop.midasit.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.example.minseop.midasit.R;

/**
 * Created by minseop on 2018-05-27.
 */

public class TextUpdateActivity extends AppCompatActivity {

    TextView txt_txtupdate;
    EditText edt_txtupdate;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textupdate);
        txt_txtupdate = findViewById(R.id.txt_textupdate);
        edt_txtupdate = findViewById(R.id.edt_textupdate);
        intent = getIntent();
        txt_txtupdate.setText(intent.getStringExtra("status"));
        edt_txtupdate.setText(intent.getStringExtra("txt"));

        setupAppBar();
    }

    private void setupAppBar() {
        Toolbar toolbar = findViewById(R.id.textupdate_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(intent.getStringExtra("status"));
        }
    }
}
