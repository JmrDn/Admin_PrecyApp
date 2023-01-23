package com.example.admin_precyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.admin_precyapp.adapater.ApprovedListAdapter;
import com.example.admin_precyapp.adapater.FinishedListAdapter;
import com.example.admin_precyapp.adapater.SelectListener;
import com.example.admin_precyapp.drawer.Maintenance;
import com.example.admin_precyapp.model.ApprovedListModel;
import com.example.admin_precyapp.model.FinishedListModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FinishedReservationList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;
    FinishedListAdapter myAdapter;
    ArrayList<FinishedListModel> list;
    FirebaseFirestore firebaseFirestore;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_reservation_list);

        recyclerView = findViewById(R.id.finished_recyclerView);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        drawerLayout = findViewById(R.id.finished_drawerLayout);
        navigationView = findViewById(R.id.finishedListNav_View);
        toolbar = findViewById(R.id.finished_ToolBar);


        myAdapter = new FinishedListAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(myAdapter);
        firebaseFirestore = FirebaseFirestore.getInstance();



        firebaseFirestore.collection("FinishedReservationListData").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    list.clear();

                    for (QueryDocumentSnapshot document :value) {
                        if (document.exists()) {
                            list.add(new FinishedListModel(document.get("Reservation ID").toString(),
                                    document.get("Name").toString(),
                                    document.get("Phone Number").toString(),
                                    document.get("Event").toString() ,
                                    document.get("ReservationDate").toString(),
                                    document.get("Number of People").toString(),
                                    document.get("User ID").toString(),
                                    document.get("Time of Reservation").toString(),
                                    document.get("Date of Reservation").toString(),
                                    document.get("CompanyName").toString(),
                                    document.get("Venue").toString()
                            ));

                            myAdapter.notifyDataSetChanged();
                        }

                    }
                }

            }
        });

        setUpDrawer();
    }

    private void setUpDrawer() {


        toolbar.setTitle("Finished Reservation");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
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
        return false;
    }
}