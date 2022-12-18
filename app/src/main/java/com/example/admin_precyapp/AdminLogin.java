package com.example.admin_precyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    Button login_btn;
    EditText email, password;
    ProgressBar myProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        login_btn = findViewById(R.id.login_btn);
        email = findViewById(R.id.admin_email);
        password = findViewById(R.id.admin_password);
        myProgressBar = findViewById(R.id.login_progressBar);

        password.setTransformationMethod(new PasswordTransformationMethod());
        myProgressBar.setVisibility(View.GONE);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login_btn.setVisibility(View.GONE);
                myProgressBar.setVisibility(View.VISIBLE);

                if (email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(getApplicationContext(), "Successfuly log in", Toast.LENGTH_SHORT).show();
                    login_btn.setVisibility(View.GONE);
                    myProgressBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(getApplicationContext(), AdminDashboard.class));

                } else {
                    Toast.makeText(getApplicationContext(), "Log in Failed", Toast.LENGTH_SHORT).show();
                    login_btn.setVisibility(View.VISIBLE);
                    myProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}