package com.example.admin_precyapp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin_precyapp.adapater.PendingListAdapter;
import com.example.admin_precyapp.drawer.Maintenance;
import com.example.admin_precyapp.model.PendingListModel;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PendingReservationList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    RecyclerView recyclerView;
    EditText userUID_EditTxt, userReservationID_EditTxt;
    Button enter;
    PendingListAdapter myAdapter;
    ArrayList<PendingListModel> list;
    FirebaseFirestore firestore;
    public static String userUID;
    public static String userReservationID;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_reservation_list);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.pendingReservationList);
        enter = findViewById(R.id.button);
        userUID_EditTxt = findViewById(R.id.userUID);
        userReservationID_EditTxt = findViewById(R.id.userReservationID);

        drawerLayout = findViewById(R.id.pendingList_drawerLayout);
        navigationView = findViewById(R.id.pendingListNav_View);
        toolbar = findViewById(R.id.pendingList_toolbar);


        firestore = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        myAdapter = new PendingListAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(myAdapter);

        setUpDrawer();


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userReservationID_EditTxt.length()== 0){
                    userReservationID_EditTxt.setError(" Reservation ID required");
                }
                else if (userUID_EditTxt.length()== 0){
                    userUID_EditTxt.setError("User UID required");
                }

                else{
                    userUID = userUID_EditTxt.getText().toString();
                    userReservationID = userReservationID_EditTxt.getText().toString();

                    startActivity(new Intent(getApplicationContext(), ApprovingReservationProcess.class));
                }
            }
        });


        firestore.collection("PendingReservation").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Toast.makeText(PendingReservationList.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        else{
                            list.clear();

                            for (QueryDocumentSnapshot document :value) {

                                if (document.exists()) {
                                    list.add(new PendingListModel(document.get("Reservation ID").toString(),
                                            document.get("Name").toString(),
                                            document.get("Phone Number").toString(),
                                            document.get("Event").toString() ,
                                            document.get("ReservationDate").toString(),
                                            document.get("Number of People").toString(),
                                            Boolean.parseBoolean(document.get("Status").toString()),
                                            document.get("User ID").toString(),
                                            document.get("Time of Reservation").toString(),
                                            document.get("Date of Reservation").toString(),
                                            document.get("CompanyName").toString(),
                                            document.get("Venue").toString()));

                                    myAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                });
    }

    private void setUpDrawer() {


        toolbar.setTitle("Pending Reservation");
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
