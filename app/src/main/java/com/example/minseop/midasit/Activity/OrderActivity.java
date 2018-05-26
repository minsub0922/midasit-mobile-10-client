package com.example.minseop.midasit.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.minseop.midasit.R;

public class OrderActivity extends AppCompatActivity {

    private ImageView itemImage;
    private Button sizeTallButton;
    private Button sizeGrandeButton;
    private Button sizeVentiButton;
    private Switch iceSwitch;
    private Switch syrupSwitch;
    private Switch whippingSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setupItemImage();
        setupSizeButtons();
        setupIceSwitch();
        setupSyrupSwitch();
        setupWhippingSwitch();
    }

    private void setupItemImage() {
        itemImage = findViewById(R.id.order_item_image);
        // TODO(@gihwan): 이미지 로드
    }

    private void setupSizeButtons() {
        sizeTallButton = findViewById(R.id.order_size_tall_button);
        sizeTallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO(@gihwan)
            }
        });
        sizeGrandeButton = findViewById(R.id.order_size_grande_button);
        sizeGrandeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO(@gihwan)
            }
        });
        sizeVentiButton = findViewById(R.id.order_size_venti_button);
        sizeVentiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO(@gihwan)
            }
        });
    }

    private void setupIceSwitch() {
        iceSwitch = findViewById(R.id.order_ice_switch);
        iceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                // TODO(@gihwan)
            }
        });
    }

    private void setupSyrupSwitch() {
        syrupSwitch = findViewById(R.id.order_syrup_switch);
        syrupSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                // TODO(@gihwan)
            }
        });
    }

    private void setupWhippingSwitch() {
        whippingSwitch = findViewById(R.id.order_whipping_switch);
        whippingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                // TODO(@gihwan)
            }
        });
    }
}
