package com.example.admin_precyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.admin_precyapp.adapater.PendingListAdapter;
import com.example.admin_precyapp.model.PendingListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PendingReservationList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    PendingListAdapter myAdapter;
    ArrayList<PendingListModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_reservation_list);

        recyclerView = findViewById(R.id.pendingReservationList);
        databaseReference = FirebaseDatabase.getInstance().getReference("PendingReservation");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));
        list = new ArrayList<>();
        myAdapter = new PendingListAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    PendingListModel pendingListModel = dataSnapshot.getValue(PendingListModel.class);
                    list.add(pendingListModel);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
