package com.example.minseop.midasit.ui.admin;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.minseop.midasit.MidasCafeConstants;
import com.example.minseop.midasit.R;
import com.example.minseop.midasit.model.MenuCategory;
import com.example.minseop.midasit.model.MenuListResponseModel;
import com.example.minseop.midasit.model.MenuModel;
import com.example.minseop.midasit.retrofit.MenuService;
import com.example.minseop.midasit.ui.adapter.MenuItemManagementRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuManagementFragment extends Fragment {

    private static final String TAG = MenuManagementFragment.class.getSimpleName();

    private FloatingActionButton faAddMenu;
    private ScrollView scrollView;

    private MenuItemManagementRecyclerAdapter coffeeMenuListAdapter;
    private RecyclerView coffeeMenuRecyclerView;

    private MenuItemManagementRecyclerAdapter teaMenuListAdapter;
    private RecyclerView teaMenuRecyclerView;

    private MenuItemManagementRecyclerAdapter beverageMenuListAdapter;
    private RecyclerView beverageMenuRecyclerView;

    private final List<MenuModel> coffeeMenuList = new ArrayList<>();
    private final List<MenuModel> teaMenuList = new ArrayList<>();
    private final List<MenuModel> beverageMenuList = new ArrayList<>();

    TextView txt_title1, txt_title2, txt_title3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu_management, container, false);

        faAddMenu = (FloatingActionButton) view.findViewById(R.id.admin_menu_management_add_button);
        scrollView = view.findViewById(R.id.scv_menu_management);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY - oldScrollY > 0 && faAddMenu.getVisibility() == View.VISIBLE)
                        faAddMenu.hide();
                    else if (scrollY - oldScrollY < 0 && faAddMenu.getVisibility() != View.VISIBLE)
                        faAddMenu.show();
                }
            });
        }

        txt_title1 = view.findViewById(R.id.txt_title1);
        txt_title2 = view.findViewById(R.id.txt_title2);
        txt_title3 = view.findViewById(R.id.txt_title3);


        coffeeMenuRecyclerView = view.findViewById(R.id.menu_mgm_recyclerview1);
        coffeeMenuListAdapter = new MenuItemManagementRecyclerAdapter(getActivity(), coffeeMenuList);
        coffeeMenuRecyclerView.setAdapter(coffeeMenuListAdapter);
        coffeeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        coffeeMenuRecyclerView.setNestedScrollingEnabled(false);
        coffeeMenuRecyclerView.setVisibility(View.GONE);
        txt_title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coffeeMenuRecyclerView.getVisibility() == View.VISIBLE) {
                    coffeeMenuRecyclerView.setVisibility(View.GONE);
                } else {
                    coffeeMenuRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
        coffeeMenuRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        teaMenuRecyclerView = view.findViewById(R.id.menu_mgm_recyclerview2);
        teaMenuListAdapter = new MenuItemManagementRecyclerAdapter(getActivity(), teaMenuList);
        teaMenuRecyclerView.setAdapter(teaMenuListAdapter);
        teaMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        teaMenuRecyclerView.setNestedScrollingEnabled(false);
        teaMenuRecyclerView.setVisibility(View.GONE);
        txt_title2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (teaMenuRecyclerView.getVisibility() == View.VISIBLE) {
                    teaMenuRecyclerView.setVisibility(View.GONE);
                } else {
                    teaMenuRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

        beverageMenuRecyclerView = view.findViewById(R.id.menu_mgm_recyclerview3);
        beverageMenuListAdapter = new MenuItemManagementRecyclerAdapter(getActivity(), beverageMenuList);
        beverageMenuRecyclerView.setAdapter(beverageMenuListAdapter);
        beverageMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        beverageMenuRecyclerView.setNestedScrollingEnabled(false);
        beverageMenuRecyclerView.setVisibility(View.GONE);
        txt_title3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beverageMenuRecyclerView.getVisibility() == View.VISIBLE) {
                    beverageMenuRecyclerView.setVisibility(View.GONE);
                } else {
                    beverageMenuRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

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
}

