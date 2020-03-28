package com.squirrel.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
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

public class SelectedVehicleInventory extends AppCompatActivity {
    TextView ava_drinks, ava_snacks, ava_sandwiches,c_drinks,c_snacks,c_sandwiches;
    EditText sel_drinks,sel_snacks,sel_sandwiches;
    Button btn_addToCart, btn_logout;
    String MY_PREFS_NAME="MyPrefs";
    BottomNavigationView bottomNavigationView;
    DatabaseHelper db;
    //SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedvehicle_inventory);
        getSupportActionBar().hide();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Intent intent1=new Intent( SelectedVehicleInventory.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        Intent intent2=new Intent(SelectedVehicleInventory.this,UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_home:
                        Intent intent3=new Intent( SelectedVehicleInventory.this, UserHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String loadedString = prefs.getString("vehname", null);
        db=new DatabaseHelper(this);

        //Set operatorID ---------------------------------
        int opid=db.getOperatorId(loadedString);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("opid",String.valueOf(opid));
        editor.apply();
        Toast.makeText(this,String.valueOf(opid),Toast.LENGTH_LONG).show();



        SharedPreferences pref = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        final String username=pref.getString("username",null);
        //Toast.makeText(this,username,Toast.LENGTH_LONG).show();
        ava_drinks=(TextView)findViewById(R.id.ava_drinks);
        ava_snacks=(TextView)findViewById(R.id.ava_snacks);
        ava_sandwiches=(TextView)findViewById(R.id.ava_sandwich);
        sel_drinks=(EditText)findViewById(R.id.sel_drinks);
        sel_snacks=(EditText)findViewById(R.id.sel_snacks);
        sel_sandwiches=(EditText)findViewById(R.id.sel_sandwiches);
        btn_addToCart=(Button)findViewById(R.id.add_to_cart);
        btn_logout = (Button) findViewById(R.id.logout);
        c_drinks=(TextView)findViewById(R.id.c_drinks);
        c_snacks=(TextView)findViewById(R.id.c_snacks);
        c_sandwiches=(TextView)findViewById(R.id.c_sandwiches);
        db=new DatabaseHelper(this);
        Cursor name_quantity= db.getSelectedVehicleInventory(loadedString);
        if(name_quantity.moveToFirst())
        {
            do{
                String itemType=name_quantity.getString(name_quantity.getColumnIndex("itemtype"));
                String quantity=name_quantity.getString(name_quantity.getColumnIndex("quantity"));
                if(itemType.equals("Drinks")){
                    ava_drinks.setText(quantity);
                    float c_d=db.getCost(81);
                    c_drinks.setText(String.valueOf(c_d));
                }
                else if(itemType.equals("Sandwiches")){
                    ava_sandwiches.setText(quantity);
                    float c_san=db.getCost(82);
                    c_sandwiches.setText(String.valueOf(c_san));
                }
                else if(itemType.equals("Snacks")){
                    ava_snacks.setText(quantity);
                    float c_s=db.getCost(83);
                    c_snacks.setText(String.valueOf(c_s));
                }
            }while(name_quantity.moveToNext());
        }



        btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int drinks = Integer.parseInt(sel_drinks.getText().toString());
                int snacks = Integer.parseInt(sel_snacks.getText().toString());
                int sandwiches = Integer.parseInt(sel_sandwiches.getText().toString());
                String str="";
                int userId = db.getUserId(username);
                if (userId != -1) {
                    if (drinks != 0 ) {
                        if(drinks>Integer.parseInt(String.valueOf(ava_drinks.getText()))){
                            str+=" Please add drinks less than available quantity ";
                        }
                        else if (db.insertToCart(userId, 81, drinks)) {

                            str=" Drinks successfully added ";
                        }
                        else{
                            str=" Drinks already added ";
                        }
                    }
                    if (snacks != 0) {
                        if(snacks>Integer.parseInt(String.valueOf(ava_snacks.getText()))){
                            str+=" Please add snacks less than available quantity ";
                        }
                        else if (db.insertToCart(userId, 83, snacks)) {

                            str+=" Snacks successfully added ";
                        }
                        else{
                            str+=" Snacks already added ";
                        }
                    }
                    if (sandwiches != 0) {
                        if(sandwiches>Integer.parseInt(String.valueOf(ava_sandwiches.getText()))){
                            str+=" Please add sandwiches less than available quantity ";
                        }
                        if (db.insertToCart(userId, 82, sandwiches)) {
                            str+=" Sandwiches successfully added ";
                        }
                        else{
                            str+=" Sandwiches already added ";
                        }
                    }

                    Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
                }
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
                Intent intent = new Intent(SelectedVehicleInventory.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}