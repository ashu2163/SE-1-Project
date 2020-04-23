package com.squirrel.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;

public class Conformation extends AppCompatActivity {
    Button btn_logout;
    BottomNavigationView bottomNavigationView;
    TextView conformation_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_conformation);

        btn_logout = (Button) findViewById(R.id.btn_logout);
        conformation_num = (TextView) findViewById(R.id.conformation_num);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Intent intent1 = new Intent(Conformation.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        Intent intent2 = new Intent(Conformation.this, UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_home:
                        Intent intent3 = new Intent(Conformation.this, UserHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });


                btn_logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.clear();
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Conformation.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });




    }
}




