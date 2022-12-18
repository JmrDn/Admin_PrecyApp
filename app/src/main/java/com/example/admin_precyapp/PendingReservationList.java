package com.example.admin_precyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.admin_precyapp.adapater.PendingListAdapter;
import com.example.admin_precyapp.model.PendingListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PendingReservationList extends AppCompatActivity {

    RecyclerView recyclerView;
    PendingListAdapter myAdapter;
    ArrayList<PendingListModel> list;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_reservation_list);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.pendingReservationList);

        firestore = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        myAdapter = new PendingListAdapter(this, list);
        recyclerView.setAdapter(myAdapter);


        firestore.collection("ClientReservationDetails").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.exists()) {
                            list.add(new PendingListModel(document.get("Reservation ID").toString(), document.get("Name").toString(), document.get("Phone Number").toString(), document.get("Event").toString() , document.get("ReservationDate").toString(), document.get("Number of People").toString()));
                            myAdapter.notifyDataSetChanged();
                        }
                    }

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PendingReservationList.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }
}
