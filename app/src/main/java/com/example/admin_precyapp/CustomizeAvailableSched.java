package com.example.admin_precyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CustomizeAvailableSched extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener {

    ArrayList<CustomizeModel> customizeModels = new ArrayList<CustomizeModel>();
    RecyclerView recyclerView;
    CustomizeAdapter customizeAdapter;
    int year, month, day;
    int myYear, myday, myMonth;
    FirebaseFirestore firestore;


    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_available_sched);


        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        btn   = findViewById(R.id.btn);
        calendarMethod();

//        ArrayList<String> sampleSChedule = new ArrayList<>();
//        sampleSChedule.add("3");
//        sampleSChedule.add("4");
//        sampleSChedule.add("7");
//        sampleSChedule.add("10");
//        sampleSChedule.add("12");
//        sampleSChedule.add("14");
//        sampleSChedule.add("17");
//        sampleSChedule.add("20");
//
//
//        for (int i = 0; i<sampleSChedule.size(); i++){
//
//            customizeModels.add( new CustomizeModel(sampleSChedule.get(i)));
//        }

        customizeAdapter = new CustomizeAdapter(CustomizeAvailableSched.this, customizeModels);
        recyclerView.setAdapter(customizeAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager( CustomizeAvailableSched.this, 3));


        firestore.collection("AvailableSched").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                        for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                            //Log.d("TAG", "data" + documentSnapshot.get("day"));

                           customizeModels.add( new CustomizeModel(Integer.parseInt(documentSnapshot.get("day").toString())));
                            customizeAdapter.notifyDataSetChanged();
                        }


                }else{
                    Log.d("TAG", "not successful");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "data get" + e);
            }
        });

    }

    private void firebaseMethod(int day) {
        HashMap<String, Integer> saveDay = new HashMap();
        saveDay.put("day", day);

        firestore.collection("AvailableSched")
                .add(saveDay)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "successfull");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "data sending" + e);
                    }
                });

    }

    private void calendarMethod() {

        btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar = Calendar.getInstance();
                        year = calendar.get(Calendar.YEAR);
                        month = calendar.get(Calendar.MONTH);
                        day = calendar.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(CustomizeAvailableSched.this, CustomizeAvailableSched.this, year, month, day);

                        datePickerDialog.show();
                    }
                });


            }


    //date method
    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = dayOfMonth;
        myMonth = month;

        //Firebase

        firebaseMethod(dayOfMonth);
        customizeModels.add( new CustomizeModel(myday));
        customizeAdapter.notifyDataSetChanged();



    }


        }


