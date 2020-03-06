package com.squirrel.application;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;

public class SelectedVehicleInventory extends AppCompatActivity {
    TextView ava_drinks, ava_snacks, ava_sandwiches;
    String MY_PREFS_NAME="MyPrefs";

    DatabaseHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectedvehicle_inventory);
        getSupportActionBar().hide();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String loadedString = prefs.getString("name", null);
        Toast.makeText(this,loadedString,Toast.LENGTH_LONG).show();
        ava_drinks=(TextView)findViewById(R.id.ava_drinks);
        ava_snacks=(TextView)findViewById(R.id.ava_snacks);
        ava_sandwiches=(TextView)findViewById(R.id.ava_sandwich);
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

    }
}