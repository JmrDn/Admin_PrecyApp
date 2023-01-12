package com.example.admin_precyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class AdminDashboard extends AppCompatActivity {

   Button pendingBtn, approvedBtn;
   TextView pendingTxtView, approvedTxtView;
   FirebaseFirestore firebaseFirestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        pendingBtn = findViewById(R.id.pendingBtn);
        approvedBtn = findViewById(R.id.approvedBtn);
        pendingTxtView = findViewById(R.id.pendingTxtView);
        approvedTxtView = findViewById(R.id.approvedTxtView);
        firebaseFirestore = FirebaseFirestore.getInstance();

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



        firebaseFirestore.collection("ApprovedReservationListData"). document("NumberOfApprovedReservation") .addSnapshotListener(new EventListener<DocumentSnapshot>() {
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

    }
}