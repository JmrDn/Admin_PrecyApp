package com.example.admin_precyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin_precyapp.adapater.PendingListAdapter;
import com.example.admin_precyapp.adapater.UserPendingListAdapter;
import com.example.admin_precyapp.model.PendingListModel;
import com.example.admin_precyapp.model.UserPendingListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class ApprovingReservationProcess extends AppCompatActivity {

    RecyclerView recyclerView;
    String userUID;
    Button done_Btn;
    UserPendingListAdapter myAdapter;
    ArrayList<UserPendingListModel> list;
    FirebaseFirestore firestore;
    String reservationID = PendingReservationList.userReservationID;
    public String numOfPendingReservation;
    public String  numOfApprovedReservation;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approving_reservation_process);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.userPending_recyclerView);
        done_Btn = findViewById(R.id.done_Btn);
        userUID = PendingReservationList.userUID;

        firestore = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        myAdapter = new UserPendingListAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(myAdapter);


        firestore.collection("Users")
                .document(userUID)
                .collection("My Reservation")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    list.clear();

                    for (QueryDocumentSnapshot document :value) {
                        if (document.exists()) {
                            list.add(new UserPendingListModel(document.get("Reservation ID").toString(),
                                    document.get("Name").toString(),
                                    document.get("Phone Number").toString(),
                                    document.get("Event").toString() ,
                                    document.get("ReservationDate").toString(),
                                    document.get("Number of People").toString(),
                                    Boolean.parseBoolean(document.get("Status").toString()),
                                    document.get("CompanyName").toString(),
                                    document.get("Venue").toString()));

                            myAdapter.notifyDataSetChanged();
                        }
                    }
                }

            }
        });

        firestore.collection("NumberOfPending"). document("NumberOfPendingReservation") .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }
                if (documentSnapshot.exists()) {
                    numOfPendingReservation = documentSnapshot.get("NumberOfPendingReservation").toString();
                }
            }
        });

        firestore.collection("NumberOfApproved"). document("NumberOfApprovedReservation") .addSnapshotListener(new EventListener<DocumentSnapshot>() {
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

        done_Btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int intNumOfPendingReservation = Integer.parseInt(numOfPendingReservation);
               intNumOfPendingReservation -= 1;

               int intNumOfApprovedReservation = Integer.parseInt(numOfApprovedReservation);
               intNumOfApprovedReservation += 1;

               firestore.collection("PendingReservation").document(reservationID)
                       .delete()
                       .addOnSuccessListener(new OnSuccessListener<Void>() {
                           @Override
                           public void onSuccess(Void unused) {


                           }
                       }).addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Log.d("TAG", "Error", e.getCause());
                           }
                       });

               numOfPendingReservation = Integer.toString(intNumOfPendingReservation);

               HashMap<String, String> number = new HashMap<>();
               number.put("NumberOfPendingReservation", numOfPendingReservation );

               firestore.collection("NumberOfPending").document("NumberOfPendingReservation")
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

               numOfApprovedReservation = Integer.toString(intNumOfApprovedReservation);
               HashMap<String, String> approved = new HashMap<>();
               approved.put("NumberOfApprovedReservation", numOfApprovedReservation );

               firestore.collection("NumberOfApproved").document("NumberOfApprovedReservation")
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
                Toast.makeText(getApplicationContext(), "Reservation " + reservationID + " approved", Toast.LENGTH_LONG).show();
               startActivity(new Intent(getApplicationContext(), AdminDashboard.class));

           }
       });
    }
}