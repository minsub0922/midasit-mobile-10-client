package com.example.minseop.midasit.ui.customer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.example.minseop.midasit.model.ShoppingCartItem;
import com.example.minseop.midasit.retrofit.ShoppingCartService;

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

                countEditText = findViewById(R.id.order_count_editText);

                ShoppingCartItem tmpshoppingCartItem = new ShoppingCartItem();
                tmpshoppingCartItem.setMenuId(1);
                tmpshoppingCartItem.setCount(Integer.parseInt(countEditText.getText().toString()));
                tmpshoppingCartItem.setSize(size);
                tmpshoppingCartItem.setWhipping(whipping);
                tmpshoppingCartItem.setIce(ice);
                tmpshoppingCartItem.setSyrup(syrup);

                Log.d("int"," "+Integer.parseInt(countEditText.getText().toString()));

               // final Call<ShoppingCartItem> ShoppingListCall = ShoppingService.addShoppingCartItem( MidasCafeApplication.getInstance().getAuthModel().getId(),tmpshoppingCartItem);

                final Call<ShoppingCartItem> ShoppingListCall = ShoppingService.addShoppingCartItem( 1,tmpshoppingCartItem);
                ShoppingListCall.enqueue(new Callback<ShoppingCartItem>() {
                    @Override
                    public void onResponse(Call<ShoppingCartItem> call, Response<ShoppingCartItem> response) {
                        final ShoppingCartItem shoppingCartItem1 = response.body();
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                    }

                    @Override
                    public void onFailure(Call<ShoppingCartItem> call, Throwable t) {
                        Log.d(TAG, "fail");
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
