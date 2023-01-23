package com.example.admin_precyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin_precyapp.drawer.Maintenance;
import com.example.admin_precyapp.drawer.MaintenanceCreateFoodPackage;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AdminDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button pendingBtn, approvedBtn, finishedBtn;
    TextView pendingTxtView, approvedTxtView, finishedTextView;
    FirebaseFirestore firebaseFirestore;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        pendingBtn = findViewById(R.id.pendingBtn);
        approvedBtn = findViewById(R.id.approvedBtn);
        finishedBtn = findViewById(R.id.finishedBtn);
        //receiptBtn = findViewById(R.id.seeReceipt_Btn);
        pendingTxtView = findViewById(R.id.pendingTxtView);
        approvedTxtView = findViewById(R.id.approvedTxtView);
        finishedTextView = findViewById(R.id.finished_TxtView);
        firebaseFirestore = FirebaseFirestore.getInstance();

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_View);
        toolbar = findViewById(R.id.toolbar);




        setUpDrawer();

        firebaseFirestore.collection("NumberOfPending"). document("NumberOfPendingReservation") .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }

                if (documentSnapshot.exists()) {
                    pendingTxtView.setText(documentSnapshot.get("NumberOfPendingReservation").toString());
                }
            }
        });



        firebaseFirestore.collection("NumberOfApproved"). document("NumberOfApprovedReservation") .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
                if (documentSnapshot.exists()) {
                    approvedTxtView.setText(documentSnapshot.get("NumberOfApprovedReservation").toString());
                }
            }
        });

        firebaseFirestore.collection("NumberOfFinished"). document("NumberOfFinishedReservation") .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
                if (documentSnapshot.exists()) {
                    finishedTextView.setText(documentSnapshot.get("NumberOfFinishedReservation").toString());
                }
            }
        });

        pendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PendingReservationList.class));
            }
        });

        approvedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ApprovedReservationList.class));
            }
        });

        finishedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FinishedReservationList.class));
            }
        });



    }



    private void setUpDrawer() {


        toolbar.setTitle("Dashboard");
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


        }
        return true;
    }
}