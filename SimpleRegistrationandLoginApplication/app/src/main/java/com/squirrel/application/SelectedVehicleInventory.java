package com.squirrel.application;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;

public class SelectedVehicleInventory extends AppCompatActivity {
    TextView ava_drinks, ava_snacks, ava_sandwiches;
    EditText sel_drinks,sel_snacks,sel_sandwiches;
    Button btn_addToCart;
    String MY_PREFS_NAME="MyPrefs";

    DatabaseHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedvehicle_inventory);
        getSupportActionBar().hide();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String loadedString = prefs.getString("name", null);
        SharedPreferences pref = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        final String username=pref.getString("username",null);
        Toast.makeText(this,username,Toast.LENGTH_LONG).show();
        ava_drinks=(TextView)findViewById(R.id.ava_drinks);
        ava_snacks=(TextView)findViewById(R.id.ava_snacks);
        ava_sandwiches=(TextView)findViewById(R.id.ava_sandwich);
        sel_drinks=(EditText)findViewById(R.id.sel_drinks);
        sel_snacks=(EditText)findViewById(R.id.sel_snacks);
        sel_sandwiches=(EditText)findViewById(R.id.sel_sandwiches);
        btn_addToCart=(Button)findViewById(R.id.add_to_cart);
        db=new DatabaseHelper(this);
        Cursor name_quantity= db.getSelectedVehicleInventory(loadedString);
        if(name_quantity.moveToFirst()){
            do{
                String itemType=name_quantity.getString(name_quantity.getColumnIndex("itemtype"));
                String quantity=name_quantity.getString(name_quantity.getColumnIndex("quantity"));
                if(itemType.equals("Drinks")){
                    ava_drinks.setText(quantity);
                }
                else if(itemType.equals("Sandwiches")){
                    ava_sandwiches.setText(quantity);
                }
                else if(itemType.equals("Snacks")){
                    ava_snacks.setText(quantity);
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
                        if(drinks>Integer.parseInt(ava_drinks.toString())){
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
                        if(snacks>Integer.parseInt(ava_snacks.toString())){
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
                        if(sandwiches>Integer.parseInt(ava_sandwiches.toString())){
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
    }
}