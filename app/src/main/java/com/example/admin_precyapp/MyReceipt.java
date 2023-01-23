package com.example.admin_precyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MyReceipt extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_receipt);

        ImageView imageView = findViewById(R.id.receipt);
        Intent intent = getIntent();
        String x = intent.getStringExtra("image").toString();
         Picasso.get().load(x).placeholder(R.drawable.adminbg).error(R.drawable.adminbg).resize(720,1080).centerCrop().into(imageView);

    }
}