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

public class ViewOperatorInventory extends AppCompatActivity {
    TextView ava_drinks, ava_snacks, ava_sandwich, cost_drinks, cost_sandwich, cost_snacks;
    String MY_PREFS_NAME="MyPrefs";
    Button bt_update;
    Button btn_logout;
    BottomNavigationView bottomNavigationView;

    DatabaseHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_inventory);
        getSupportActionBar().hide();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recents:
                        Intent intent1=new Intent( ViewOperatorInventory.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        break;
                    case R.id.action_home:
                        Intent intent3=new Intent( ViewOperatorInventory.this, OperatorHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
        btn_logout = (Button) findViewById(R.id.btn_logout);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String loadedString = prefs.getString("name", null);
        Toast.makeText(this, loadedString, Toast.LENGTH_LONG).show();


        ava_drinks = (EditText) findViewById(R.id.ava_drinks);
        ava_snacks = (EditText) findViewById(R.id.ava_snacks);
        ava_sandwich = (EditText) findViewById(R.id.ava_sandwich);

        cost_drinks = (TextView) findViewById(R.id.cost_drinks);
        cost_snacks = (TextView) findViewById(R.id.cost_snacks);
        cost_sandwich = (TextView) findViewById(R.id.cost_sandwich);
        bt_update=(Button) findViewById(R.id.btn_update);

        db = new DatabaseHelper(this);
        final int vehId = db.getVehId(loadedString);

        Cursor c = db.getVehicleInventory(loadedString);

        if (c.moveToFirst()) {
            do {
                String itemType = c.getString(c.getColumnIndex("itemtype"));
                String quantity = c.getString(c.getColumnIndex("quantity"));
                String cost = c.getString(c.getColumnIndex("cost"));
                if (itemType.equals("Drinks")) {
                    ava_drinks.setText(quantity);
                    cost_drinks.setText(cost);
                } else if (itemType.equals("Sandwiches")) {
                    ava_sandwich.setText(quantity);
                    cost_sandwich.setText(cost);
                } else if (itemType.equals("Snacks")) {
                    ava_snacks.setText(quantity);
                    cost_snacks.setText(cost);
                }

            } while (c.moveToNext());
        }

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float drinks = Float.parseFloat(ava_drinks.getText().toString());
                float snacks = Float.parseFloat(ava_snacks.getText().toString());
                float sandwiches = Float.parseFloat(ava_sandwich.getText().toString());
                Cursor c = db.getInventoryDetails(vehId);
                if(c.moveToFirst()){
                    do{
                        int itemid=c.getInt(c.getColumnIndex("itemid"));
                        if(itemid==81){
                           db.updateQuantity(vehId,itemid,drinks);
                        }
                        else if(itemid==82){
                            db.updateQuantity(vehId,itemid,sandwiches);
                        }
                        else if(itemid==83){
                            db.updateQuantity(vehId,itemid,snacks);
                        }
                    }while(c.moveToNext());
                }


                Toast.makeText(getBaseContext(), "Quantity Updated Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewOperatorInventory.this, OperatorHomeActivity.class);
                startActivity(intent);

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
                Intent intent = new Intent(ViewOperatorInventory.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}


