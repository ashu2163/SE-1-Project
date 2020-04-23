package com.squirrel.application;

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
import com.squirrel.dao.DatabaseHelper;



public class PaymentCardInfo extends AppCompatActivity {


    DatabaseHelper db;
    BottomNavigationView bottomNavigationView;

    Button btn_place_order;
    Button btn_modify;
    Button btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_info);

        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_place_order = (Button) findViewById(R.id.place_order);
        btn_modify = (Button) findViewById(R.id.btn_modify);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Intent intent1 = new Intent(PaymentCardInfo.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        Intent intent2 = new Intent(PaymentCardInfo.this, UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_home:
                        Intent intent3 = new Intent(PaymentCardInfo.this, UserHomeActivity.class);
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
                Intent intent = new Intent(PaymentCardInfo.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentCardInfo.this, Conformation.class);
                startActivity(intent);
            }
        });



        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentCardInfo.this, UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        Toast.makeText(getApplicationContext(), "Conformation Number will be given after place order on third iteration", Toast.LENGTH_SHORT).show();
    }
}