package com.example.minseop.midasit.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.MenuCategory;
import com.example.minseop.midasit.model.MenuListResponseModel;
import com.example.minseop.midasit.model.MenuModel;
import com.example.minseop.midasit.retrofit.MenuService;
import com.example.minseop.midasit.ui.adapter.MenuItemRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by minseop on 2018-05-26.
 */

public class MenuListFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = MenuListFragment.class.getSimpleName();

    private MenuItemRecyclerAdapter coffeeMenuListAdapter;
    private RecyclerView coffeeMenuRecyclerView;
    private TextView moreCoffeeMenuList;

    private MenuItemRecyclerAdapter teaMenuListAdapter;
    private RecyclerView teaMenuRecyclerView;
    private TextView moreTeaMenuList;

    private MenuItemRecyclerAdapter beverageMenuListAdapter;
    private RecyclerView beverageMenuRecyclerView;
    private TextView moreBeverageMenuList;

    private final List<MenuModel> coffeeMenuList = new ArrayList<>();
    private final List<MenuModel> teaMenuList = new ArrayList<>();
    private final List<MenuModel> beverageMenuList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menulist, container, false);

        moreCoffeeMenuList = view.findViewById(R.id.menu_list_more_coffee);
        moreCoffeeMenuList.setOnClickListener(this);
        moreTeaMenuList = view.findViewById(R.id.menu_list_more_tea);
        moreTeaMenuList.setOnClickListener(this);
        moreBeverageMenuList = view.findViewById(R.id.menu_list_more_beverage);
        moreBeverageMenuList.setOnClickListener(this);


        coffeeMenuRecyclerView = view.findViewById(R.id.recyclerview1);
        coffeeMenuListAdapter = new MenuItemRecyclerAdapter(getActivity(), coffeeMenuList);
        coffeeMenuRecyclerView.setAdapter(coffeeMenuListAdapter);
        coffeeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        teaMenuRecyclerView = view.findViewById(R.id.recyclerview2);
        teaMenuListAdapter = new MenuItemRecyclerAdapter(getActivity(), teaMenuList);
        teaMenuRecyclerView.setAdapter(teaMenuListAdapter);
        teaMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        beverageMenuRecyclerView = view.findViewById(R.id.recyclerview3);
        beverageMenuListAdapter = new MenuItemRecyclerAdapter(getActivity(), beverageMenuList);
        beverageMenuRecyclerView.setAdapter(beverageMenuListAdapter);
        beverageMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MidasCafeConstants.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final MenuService menuService = retrofit.create(MenuService.class);
        menuService.getAllMenuByCategory(MenuCategory.COFFEE)
                .enqueue(new Callback<MenuListResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MenuListResponseModel> call, @NonNull Response<MenuListResponseModel> response) {
                        final MenuListResponseModel menuListResponse = response.body();
                        if (menuListResponse == null) {
                            // TODO(@gihwan): check error
                        } else {
                            final List<MenuModel> menus = menuListResponse.getMenus();
                            if (menus == null) {
                                // TODO(@gihwan): check error
                            } else {
                                coffeeMenuList.clear();
                                coffeeMenuList.addAll(menus);
                                coffeeMenuListAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MenuListResponseModel> call, @NonNull Throwable t) {
                        // TODO(@gihwan): check error
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
        menuService.getAllMenuByCategory(MenuCategory.TEA)
                .enqueue(new Callback<MenuListResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MenuListResponseModel> call, @NonNull Response<MenuListResponseModel> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        final MenuListResponseModel menuListResponse = response.body();
                        if (menuListResponse == null) {
                            // TODO(@gihwan): check error
                        } else {
                            final List<MenuModel> menus = menuListResponse.getMenus();
                            if (menus == null) {
                                // TODO(@gihwan): check error
                            } else {
                                teaMenuList.clear();
                                teaMenuList.addAll(menus);
                                teaMenuListAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MenuListResponseModel> call, @NonNull Throwable t) {
                        // TODO(@gihwan): check error
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
        menuService.getAllMenuByCategory(MenuCategory.BEVERAGE)
                .enqueue(new Callback<MenuListResponseModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MenuListResponseModel> call, @NonNull Response<MenuListResponseModel> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                        final MenuListResponseModel menuListResponse = response.body();
                        if (menuListResponse == null) {
                            // TODO(@gihwan): check error
                        } else {
                            final List<MenuModel> menus = menuListResponse.getMenus();
                            if (menus == null) {
                                // TODO(@gihwan): check error
                            } else {
                                beverageMenuList.clear();
                                beverageMenuList.addAll(menus);
                                beverageMenuListAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MenuListResponseModel> call, @NonNull Throwable t) {
                        // TODO(@gihwan): check error
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MenuDetailActivity.class);

        if (v.getId() == R.id.menu_list_more_coffee) {
            intent.putExtra("category", MenuCategory.COFFEE.toString());
            intent.putExtra("title", "Coffee");
        } else if (v.getId() == R.id.menu_list_more_tea) {
            intent.putExtra("category", MenuCategory.TEA.toString());
            intent.putExtra("title", "Tea");
        } else if (v.getId() == R.id.menu_list_more_beverage) {
            intent.putExtra("category", MenuCategory.BEVERAGE.toString());
            intent.putExtra("title", "Beverage");
        }

        startActivity(intent);
    }
}
