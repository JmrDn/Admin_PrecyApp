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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin_precyapp.adapater.ApprovedListAdapter;
import com.example.admin_precyapp.adapater.PendingListAdapter;
import com.example.admin_precyapp.adapater.SelectListener;
import com.example.admin_precyapp.adapater.UserPendingListAdapter;
import com.example.admin_precyapp.drawer.Maintenance;
import com.example.admin_precyapp.model.ApprovedListModel;
import com.example.admin_precyapp.model.PendingListModel;
import com.example.admin_precyapp.model.UserPendingListModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class ApprovedReservationList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SelectListener{

    RecyclerView recyclerView;
    ApprovedListAdapter myAdapter;
    ArrayList<ApprovedListModel> list;
    FirebaseFirestore firebaseFirestore;

    Button finishedBtn;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    public String name, reservationID, venue, timeOfReservation, dateOfReservation, event,
            mobileNum, reservationDate, companyName, userID, numOfPeople;

    public String numOfFinishedReservation, numOfApprovedReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_reservation_list);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.approvedRecyclerView);

        finishedBtn = findViewById(R.id.finished_Btn);

        drawerLayout = findViewById(R.id.approvedList_drawerLayout);
        navigationView = findViewById(R.id.approvedListNav_View);
        toolbar = findViewById(R.id.approvedList_toolbar);


        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        finishedBtn.setVisibility(View.VISIBLE);
        setUpDrawer();

        myAdapter = new ApprovedListAdapter(getApplicationContext(), list, (SelectListener) this);
        recyclerView.setAdapter(myAdapter);

        firebaseFirestore.collection("ApprovedReservationListData").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                            list.add(new ApprovedListModel(document.get("Reservation ID").toString(),
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

        firebaseFirestore.collection("NumberOfFinished"). document("NumberOfFinishedReservation") .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
                if (documentSnapshot.exists()) {
                    numOfFinishedReservation = documentSnapshot.get("NumberOfFinishedReservation").toString();
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
                    numOfApprovedReservation = documentSnapshot.get("NumberOfApprovedReservation").toString();
                }
            }
        });


        setUpFinishedButton();
    }



    private void setUpDrawer() {


        toolbar.setTitle("Approved Reservation");
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

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onItemClicked(int pos) {

        if(list.get(pos).getStatus().equals(false)){
            for(int i=0; i<list.size(); i++){
                list.get(i).setStatus(false);
            }
            list.get(pos).setStatus(true);

            myAdapter.notifyDataSetChanged();
        }

        reservationID = list.get(pos).getReservationID();
        name = list.get(pos).getName();
        mobileNum = list.get(pos).getMobilenum();
        reservationDate = list.get(pos).getReservationDate();
        event = list.get(pos).getEvent();
        numOfPeople = list.get(pos).getNumofPeople();
        userID = list.get(pos).getUserID();
        timeOfReservation = list.get(pos).getTimeOfReservation();
        dateOfReservation = list.get(pos).getDateOfReservation();
        companyName = list.get(pos).getCompanyName();
        venue = list.get(pos).getVenue();


    }

    private void setUpFinishedButton() {

        finishedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numOfFinishReservation = Integer.parseInt(numOfFinishedReservation);
                numOfFinishReservation +=1;

                int numOfApproveReservation = Integer.parseInt(numOfApprovedReservation);
                numOfApproveReservation -=1;



                HashMap<String, Object> ReservationDetails = new HashMap<>();
                ReservationDetails.put("Reservation ID", reservationID);
                ReservationDetails.put("Name", name);
                ReservationDetails.put("Phone Number", mobileNum);
                ReservationDetails.put("ReservationDate", reservationDate);
                ReservationDetails.put("Event", event);
                ReservationDetails.put("Number of People", numOfPeople);
                ReservationDetails.put("User ID", userID);
                ReservationDetails.put("Time of Reservation", timeOfReservation);
                ReservationDetails.put("Date of Reservation", dateOfReservation);
                ReservationDetails.put("CompanyName", companyName);
                ReservationDetails.put("Venue", venue);
                ReservationDetails.put("Status", false);

                firebaseFirestore.collection("FinishedReservationListData").document(reservationID)
                        .set(ReservationDetails)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "SUCCESS DATA UPLOAD");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "data sending" + e);
                            }
                        });

                firebaseFirestore.collection("ApprovedReservationListData").document(reservationID)
                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "Error", e.getCause());
                            }
                        });

                numOfFinishedReservation = Integer.toString(numOfFinishReservation);

                HashMap<String, String> number = new HashMap<>();
                number.put("NumberOfFinishedReservation", numOfFinishedReservation );

                firebaseFirestore.collection("NumberOfFinished").document("NumberOfFinishedReservation")
                        .set(number)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "SUCCESS DATA UPLOAD");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "data sending" + e);
                            }
                        });

                numOfApprovedReservation = Integer.toString(numOfApproveReservation);
                HashMap<String, String> approved = new HashMap<>();
                approved.put("NumberOfApprovedReservation", numOfApprovedReservation );

                firebaseFirestore.collection("NumberOfApproved").document("NumberOfApprovedReservation")
                        .set(approved)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "SUCCESS DATA UPLOAD");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "data sending" + e);
                            }
                        });
                Toast.makeText(getApplicationContext(), "Reservation " + reservationID + " done", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), AdminDashboard.class));

            }
        });
    }
}