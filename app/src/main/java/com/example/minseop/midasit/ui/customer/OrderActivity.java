package com.example.minseop.midasit.ui.customer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.ShoppingCartModel;
import com.example.minseop.midasit.retrofit.ShoppingCartService;

import retrofit2.Call;
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

    private Button payNowButton;
    private Button storeShooppingButton;
    private int size = 0;//tall = 0, grande = 1, venti = 2
    boolean ice = false;
    boolean syrup = false;
    boolean cream = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setupItemImage();
        setupSizeButtons();
        setupIceSwitch();
        setupSyrupSwitch();
        setupWhippingSwitch();
        setupButtons();
    }

    private void setupButtons() {
        payNowButton = findViewById(R.id.order_pay_now_button);
        storeShooppingButton = findViewById(R.id.order_store_shopping_cart_button);

        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        storeShooppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MidasCafeConstants.SERVER_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final ShoppingCartService ShoppingService = retrofit.create(ShoppingCartService.class);

                ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
                shoppingCartModel.setSize(size);
                shoppingCartModel.setCream(cream);
                shoppingCartModel.setIce(ice);
                shoppingCartModel.setSyrup(syrup);
                final Call<ShoppingCartModel> ShoppingListCall = ShoppingService.add(shoppingCartModel);
                //enqueue


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
                    cream = true;
                } else {
                    cream = false;
                }
            }
        });
    }


}
