package com.example.minseop.midasit.ui.admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.minseop.midasit.R;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        setupBottomNavigation();

        replaceFragment(new ReservationManagementFragment());
    }

    private boolean replaceFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.admin_main_fragment_container, fragment)
                .commit();
        return true;
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.admin_main_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final int itemId = item.getItemId();

                if (itemId == R.id.admin_bottom_navigation_action_reservaion_management) {
                    return replaceFragment(new ReservationManagementFragment());
                } else if (itemId == R.id.admin_bottom_navigation_action_menu_management) {
                    return replaceFragment(new MenuManagementFragment());
                } else if (itemId == R.id.admin_bottom_navigation_action_user_management) {
                    return replaceFragment(new UserManagementFragment());
                }

                return false;
            }
        });
    }
}
