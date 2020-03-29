package com.squirrel.application;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;


    public class CheckOutActivity extends AppCompatActivity {


        DatabaseHelper db;
        BottomNavigationView bottomNavigationView;
        TextView item_title, quantity_title, totalcost_title, checkout_title, drinks_text, drinks_quantity, sandwiches_quantity, snacks_quantity, total_price, sandwiches_title, snacks_title;

        Button btn_payment;
        Button btn_logout;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_checkout);

            bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_cart:
                            Intent intent1 = new Intent(CheckOutActivity.this, CartDetailsActivity.class);
                            startActivity(intent1);
                            break;
                        case R.id.action_profile:
                            Intent intent2 = new Intent(CheckOutActivity.this, UpdateProfileActivity.class);
                            startActivity(intent2);
                            break;
                        case R.id.action_home:
                            Intent intent3 = new Intent(CheckOutActivity.this, UserHomeActivity.class);
                            startActivity(intent3);
                            break;
                    }

                    return false;
                }
            });


            btn_payment = (Button) findViewById(R.id.btn_payment);

            btn_payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Toast.makeText(getApplicationContext(), "Going to Checkout", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CheckOutActivity.this, PaymentCardInfo.class);
                    startActivity(intent);
                }
            });

        }
    }