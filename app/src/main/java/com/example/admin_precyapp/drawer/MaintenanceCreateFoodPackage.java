package com.example.admin_precyapp.drawer;

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
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin_precyapp.PendingReservationList;
import com.example.admin_precyapp.R;
import com.example.admin_precyapp.adapater.FoodPackageAdapter;
import com.example.admin_precyapp.adapater.PendingListAdapter;
import com.example.admin_precyapp.model.FoodPackageModel;
import com.example.admin_precyapp.model.PendingListModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class MaintenanceCreateFoodPackage extends AppCompatActivity {

    EditText  price, foodNo1, foodNo2, foodNo3, foodNo4;
    Button addNewFoodPackage_Btn, add_Btn, view_Btn;
    TextView foodPackageName;
    FirebaseFirestore firebaseFirestore;
    public String foodPackageNameStr = Maintenance.packageNameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        foodPackageName = findViewById(R.id.packageName_TextView);
        price = findViewById(R.id.price_EditText);
        foodNo1 = findViewById(R.id.foodNo1_EditText);
        foodNo2 = findViewById(R.id.foodNo2_EditText);
        foodNo3 = findViewById(R.id.foodNo3_EditText);
        foodNo4 = findViewById(R.id.foodNo4_EditText);
        addNewFoodPackage_Btn = findViewById(R.id.addMenu_Btn);
        add_Btn = findViewById(R.id.add_Btn);

        firebaseFirestore = FirebaseFirestore.getInstance();

        foodPackageName.setText(foodPackageNameStr);

        setUpAddBtn();
    }




    private void setUpAddBtn() {

        add_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodPackageName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter the Food Package Name", Toast.LENGTH_LONG).show();
                }
                else if (price.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Price", Toast.LENGTH_LONG).show();
                }
                else if (foodNo1.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Food name", Toast.LENGTH_LONG).show();
                }
                else if (foodNo2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter the Food  name", Toast.LENGTH_LONG).show();
                }
                else if (foodNo3.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter the Food name", Toast.LENGTH_LONG).show();
                }
                else if (foodNo4.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter the Food name", Toast.LENGTH_LONG).show();
                }

                else{
                    setUpFoodPackage();
                    Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MaintenanceViewFoodPackage.class));
                }
            }
        });

    }


    private void setUpFoodPackage() {

        HashMap<String, Object> foodPackage = new HashMap<>();

        foodPackage.put("PackageName", foodPackageName.getText().toString());
        foodPackage.put("Price", price.getText().toString());
        foodPackage.put("FoodNo1", foodNo1.getText().toString());
        foodPackage.put("FoodNo2", foodNo2.getText().toString());
        foodPackage.put("FoodNo3", foodNo3.getText().toString());
        foodPackage.put("FoodNo4", foodNo4.getText().toString());
        foodPackage.put("Status", false);

        firebaseFirestore.collection("FoodPackageList").document(foodPackageName.getText().toString())
                .set(foodPackage)
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
    }





}