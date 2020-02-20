package com.example.squirrel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StudentHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_student_home);
    }


}

