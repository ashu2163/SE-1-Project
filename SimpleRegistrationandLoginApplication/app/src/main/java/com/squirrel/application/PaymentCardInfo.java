package com.squirrel.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;



public class PaymentCardInfo extends AppCompatActivity {


    DatabaseHelper db;
    BottomNavigationView bottomNavigationView;
//    TextView item_title, quantity_title, totalcost_title, checkout_title, drinks_text, drinks_quantity, sandwiches_quantity, snacks_quantity, total_price, sandwiches_title, snacks_title;
//
//    Button btn_payment;
//    Button btn_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_info);

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


        Toast.makeText(getApplicationContext(), "Conformation Number will be given after place order on third iteration", Toast.LENGTH_SHORT).show();
    }
}