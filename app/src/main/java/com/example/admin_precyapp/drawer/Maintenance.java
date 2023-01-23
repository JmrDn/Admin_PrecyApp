package com.example.admin_precyapp.drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.admin_precyapp.AdminDashboard;
import com.example.admin_precyapp.ApprovedReservationList;
import com.example.admin_precyapp.FinishedReservationList;
import com.example.admin_precyapp.PendingReservationList;
import com.example.admin_precyapp.R;
import com.google.android.material.navigation.NavigationView;

public class Maintenance extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FrameLayout frameLayout;
    Button next_Btn, viewPackage_Btn, addMenu_Btn;
    EditText packageName;
    public static String packageNameStr;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance2);
        frameLayout = findViewById(R.id.addMenu_FrameLayout);
        next_Btn = findViewById(R.id.next_Btn);
        viewPackage_Btn = findViewById(R.id.view_Btn);
        addMenu_Btn = findViewById(R.id.addMenu_Btn);
        packageName = findViewById(R.id.packageName_EditText);

        frameLayout.setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.maintenance_drawerLayout);
        navigationView = findViewById(R.id.maintenanceNav_View);
        toolbar = findViewById(R.id.maintenance_toolbar);

        setUpDrawer();


        viewPackage_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MaintenanceViewFoodPackage.class));
            }
        });

        addMenu_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.setVisibility(View.VISIBLE);
            }
        });

        next_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (packageName.getText().toString().isEmpty()){
                    packageName.setText("Enter package name");
                }

                else {
                    packageNameStr = packageName.getText().toString();
                    startActivity(new Intent(getApplicationContext(), MaintenanceCreateFoodPackage.class));
                }
            }
        });

    }

    private void setUpDrawer() {


        toolbar.setTitle("Maintenance");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.navDashboard:
                Intent intentDashboard = new Intent(getApplicationContext(), AdminDashboard.class);
                startActivity(intentDashboard);
                break;
            case R.id.navMaintenance:
                Intent intent = new Intent(getApplicationContext(), Maintenance.class);
                startActivity(intent);
                break;
            case R.id.navPendingReservation:
                startActivity(new Intent(getApplicationContext(), PendingReservationList.class));
                break;
            case R.id.navApprovedReservation:
                startActivity(new Intent(getApplicationContext(), ApprovedReservationList.class));
                break;
            case R.id.navFinishedReservation:
                startActivity(new Intent(getApplicationContext(), FinishedReservationList.class));
                break;
        }
        return true;
    }
}