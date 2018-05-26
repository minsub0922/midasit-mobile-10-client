package com.example.minseop.midasit.ui.customer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.minseop.midasit.MidasCafeApplication;
import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.Order;
import com.example.minseop.midasit.model.ResponseModel;
import com.example.minseop.midasit.retrofit.OrderService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderActivity extends AppCompatActivity {

    private ImageView itemImage;
    private Button sizeTallButton;
    private Button sizeGrandeButton;
    private Button sizeVentiButton;
    private Switch iceSwitch;
    private Switch syrupSwitch;
    private Switch whippingSwitch;
    private EditText countEditText;

    private Button payNowButton;
    private Button storeShooppingButton;
    private int size = 0;//tall = 0, grande = 1, venti = 2
    private boolean ice = false;
    private boolean syrup = false;
    private boolean whipping = false;
    private static final String TAG = OrderActivity.class.getSimpleName();
    private String name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");

        setupItemImage();
        setupSizeButtons();
        setupIceSwitch();
        setupSyrupSwitch();
        setupWhippingSwitch();
        setupButtons();
        setupAppBar();
    }

    private void setupAppBar() {
        Toolbar toolbar = findViewById(R.id.order_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(name+"주문");
        }
    }


    private void setupButtons() {
        payNowButton = findViewById(R.id.order_pay_now_button);
        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        storeShooppingButton = findViewById(R.id.order_store_shopping_cart_button);
        storeShooppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int count = Integer.parseInt(countEditText.getText().toString());

                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MidasCafeConstants.SERVER_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                retrofit.create(OrderService.class)
                        .insertOrder(new Order(
                                MidasCafeApplication.getInstance().getAuthModel().getId(),
                                1,
                                Order.ORDER_STATUS_SHOPPINGCART,
                                count,
                                iceSwitch.isChecked(),
                                syrupSwitch.isChecked(),
                                whippingSwitch.isChecked()
                        ))
                        .enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                                finish();
                            }

                            @Override
                            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                                Log.e(TAG, "onFailure: ", t);
                            }
                        });
            }
        });

    }

    private void setupItemImage() {
        itemImage = findViewById(R.id.order_item_image);
        // TODO(@gihwan): 이미지 로드
    }

    private void setupSizeButtons() {
        sizeTallButton = findViewById(R.id.order_size_tall_button);
        sizeGrandeButton = findViewById(R.id.order_size_grande_button);
        sizeVentiButton = findViewById(R.id.order_size_venti_button);
        sizeGrandeButton.setTextColor(Color.LTGRAY);
        sizeVentiButton.setTextColor(Color.LTGRAY);
        size = 0;

        sizeTallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO(@gihwan)
                size = 0;
                sizeTallButton.setTextColor(Color.WHITE);
                sizeGrandeButton.setTextColor(Color.LTGRAY);
                sizeVentiButton.setTextColor(Color.LTGRAY);
            }
        });

        sizeGrandeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO(@gihwan)
                size = 1;
                sizeTallButton.setTextColor(Color.LTGRAY);
                sizeGrandeButton.setTextColor(Color.WHITE);
                sizeVentiButton.setTextColor(Color.LTGRAY);
            }
        });

        sizeVentiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO(@gihwan)
                size = 2;
                sizeTallButton.setTextColor(Color.LTGRAY);
                sizeGrandeButton.setTextColor(Color.LTGRAY);
                sizeVentiButton.setTextColor(Color.WHITE);
            }
        });
    }

    private void setupIceSwitch() {
        iceSwitch = findViewById(R.id.order_ice_switch);
        iceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                // TODO(@gihwan)
                if (isChecked) {
                    ice = true;
                } else {
                    ice = false;
                }
            }
        });
    }

    private void setupSyrupSwitch() {
        syrupSwitch = findViewById(R.id.order_syrup_switch);
        syrupSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                // TODO(@gihwan)
                if (isChecked) {
                    syrup = true;
                } else {
                    syrup = false;
                }
            }
        });
    }

    private void setupWhippingSwitch() {
        whippingSwitch = findViewById(R.id.order_whipping_switch);
        whippingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                // TODO(@gihwan)
                if (isChecked) {
                    whipping = true;
                } else {
                    whipping = false;
                }
            }
        });
    }


}
