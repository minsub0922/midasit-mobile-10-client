package com.example.minseop.midasit.ui.customer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.minseop.midasit.R;

/**
 * Created by minseop on 2018-05-25.
 */

public class CustomerMainActivity extends AppCompatActivity {

    private static final String TAG = CustomerMainActivity.class.getSimpleName();

    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupAppBar();
        setupBottomNavigation();

        replaceViewPager(new MenuListFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == R.id.toolbar_action_notification) {
            return true;
        } else if (itemId == R.id.toolbar_action_shopping_cart) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean replaceViewPager(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit();
        return true;
    }

    private void setupAppBar() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void setupBottomNavigation() {
        bottomNavigation = findViewById(R.id.main_bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final int itemId = item.getItemId();

                if (itemId == R.id.bottom_navigation_action_home) {
                    return replaceViewPager(new MenuListFragment());
                } else if (itemId == R.id.bottom_navigation_action_menu) {
                    return replaceViewPager(new MenuListFragment());
                } else if (itemId == R.id.bottom_navigation_action_mypage) {
                    return replaceViewPager(new MenuListFragment());
                }

                return false;
            }
        });
    }

}
