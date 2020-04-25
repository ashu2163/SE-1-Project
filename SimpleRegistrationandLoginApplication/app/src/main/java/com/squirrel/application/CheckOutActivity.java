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
        TextView  drinks_quantity, sandwiches_quantity, snacks_quantity, total_price;

        Button btn_payment;
        Button btn_logout;

        float count = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_checkout);

            drinks_quantity=(TextView)findViewById(R.id.drinks_quantity);
            sandwiches_quantity=(TextView)findViewById(R.id.sandwiches_quantity);
            snacks_quantity=(TextView)findViewById(R.id.snacks_quantity);
            total_price=(TextView)findViewById(R.id.total_price);
            btn_payment = (Button) findViewById(R.id.btn_payment);
            btn_logout = (Button) findViewById(R.id.btn_logout);
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

            SharedPreferences pref = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
            final String username=pref.getString("username",null);


            db = new DatabaseHelper(this);
            final int userId = db.getUserId(username);
            Cursor c= db.getCartDetails(userId);

            if(c.moveToFirst()){
                do{
                    int itemid=c.getInt(c.getColumnIndex("itemid"));
                    float cost=db.getCost(itemid);
                    int buy_quantity=c.getInt(c.getColumnIndex("buy_quantity"));
                    if(itemid==81){
                        drinks_quantity.setText(String.valueOf(buy_quantity));
                        cost = cost * buy_quantity;
                        count = count + cost;
                    }
                    else if(itemid==82){
                        sandwiches_quantity.setText(String.valueOf(buy_quantity));
                        cost = cost* buy_quantity;
                        count = count + cost;
                    }
                    else if(itemid==83){
                        snacks_quantity.setText(String.valueOf(buy_quantity));
                        cost = cost* buy_quantity;
                        count = count + cost;
                    }
                }while(c.moveToNext());
            }

            total_price.setText(String.valueOf(count));

            btn_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CheckOutActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });


            btn_payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(CheckOutActivity.this, PaymentCardInfo.class);
                   String tcost=  Float.toString(count);
                    intent.putExtra("tcost",count);
                    startActivity(intent);
                }
            });

        }
    }