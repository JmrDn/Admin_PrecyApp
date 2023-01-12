package com.example.admin_precyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin_precyapp.adapater.ApprovedListAdapter;
import com.example.admin_precyapp.adapater.PendingListAdapter;
import com.example.admin_precyapp.adapater.UserPendingListAdapter;
import com.example.admin_precyapp.model.ApprovedListModel;
import com.example.admin_precyapp.model.PendingListModel;
import com.example.admin_precyapp.model.UserPendingListModel;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ApprovedReservationList extends AppCompatActivity {

    RecyclerView recyclerView;
    ApprovedListAdapter myAdapter;
    ArrayList<ApprovedListModel> list;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_reservation_list);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.approvedRecyclerView);


        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        myAdapter = new ApprovedListAdapter(getApplicationContext(), list);
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
                                    document.get("Venue").toString()));

                            myAdapter.notifyDataSetChanged();
                        }

                    }
                }

            }
        });
    }
}