package com.example.admin_precyapp.drawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.admin_precyapp.R;
import com.example.admin_precyapp.adapater.FoodPackageAdapter;
import com.example.admin_precyapp.adapater.SelectListener;
import com.example.admin_precyapp.model.FoodPackageModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class MaintenanceViewFoodPackage extends AppCompatActivity {
    ArrayList<FoodPackageModel> list;
    RecyclerView recyclerView;
    FoodPackageAdapter foodPackageAdapter;

    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_view_food_package);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.foodPackage_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));
        foodPackageAdapter = new FoodPackageAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(foodPackageAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();


        setUpFoodPackageRecyclerView();


    }

    private void setUpFoodPackageRecyclerView() {

        firebaseFirestore.collection("FoodPackageList").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Toast.makeText(MaintenanceViewFoodPackage.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
                else{
                    list.clear();

                    for (QueryDocumentSnapshot document :value) {

                        if (document.exists()) {
                            list.add(new FoodPackageModel(document.get("PackageName").toString(),
                                    document.get("Price").toString(),
                                    document.get("FoodNo1").toString(),
                                    document.get("FoodNo2").toString() ,
                                    document.get("FoodNo3").toString(),
                                    document.get("FoodNo4").toString()));

                            foodPackageAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });
    }


}