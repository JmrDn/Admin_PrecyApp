package com.example.admin_precyapp;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin_precyapp.adapater.PendingListAdapter;
import com.example.admin_precyapp.model.PendingListModel;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PendingReservationList extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText userUID_EditTxt, userReservationID_EditTxt;
    Button enter;
    PendingListAdapter myAdapter;
    ArrayList<PendingListModel> list;
    FirebaseFirestore firestore;
    public static String userUID;
    public static String userReservationID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_reservation_list);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.pendingReservationList);
        enter = findViewById(R.id.button);
        userUID_EditTxt = findViewById(R.id.userUID);
        userReservationID_EditTxt = findViewById(R.id.userReservationID);


        firestore = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        myAdapter = new PendingListAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(myAdapter);




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
}




// databaseReference.addValueEventListener(new ValueEventListener() {
//@Override
//public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//        for (DataSnapshot pendingReservation : snapshot.child("PendingReservation").getChildren()){
//
//        if (pendingReservation.hasChild("Reservation ID")
//        && pendingReservation.hasChild("Name")
//        && pendingReservation.hasChild("Phone Number")
//        && pendingReservation.hasChild("ReservationDate")
//        && pendingReservation.hasChild("Event")
//        && pendingReservation.hasChild("Number of People")){
//
//final  String getReservationID = pendingReservation.child("Reservation ID").getValue(String.class);
//final  String getName = pendingReservation.child("Name").getValue(String.class);
//final  String getMobileNum = pendingReservation.child("Phone Number").getValue(String.class);
//final  String getReservationDate = pendingReservation.child("ReservationDate").getValue(String.class);
//final  String getEvent = pendingReservation.child("Event").getValue(String.class);
//final  String getNumOfPeople = pendingReservation.child("Number of People").getValue(String.class);
//
//        PendingListModel myList = new PendingListModel(getReservationID, getName, getMobileNum, getEvent, getReservationDate, getNumOfPeople);
//        list.add(myList);
//
//
//        }
//
//
//
//        }
//
//
//
//        }
//
//@Override
//public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//        });
