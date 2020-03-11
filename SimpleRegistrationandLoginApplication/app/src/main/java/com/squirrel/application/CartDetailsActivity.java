package com.squirrel.application;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;

public class CartDetailsActivity extends AppCompatActivity {
    EditText qua_drinks,qua_snacks,qua_sandwich,et_c_drinks,et_c_snacks,et_c_sandwich,et_cost;
    Button btn_update;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);
        SharedPreferences pref = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        final String username=pref.getString("username",null);

        db = new DatabaseHelper(this);

        qua_drinks=(EditText)findViewById(R.id.qua_drinks);
        qua_snacks=(EditText)findViewById(R.id.qua_snacks);
        qua_sandwich=(EditText)findViewById(R.id.qua_sandwich);

        et_c_drinks=(EditText)findViewById(R.id.et_c_drinks);
        et_c_snacks=(EditText)findViewById(R.id.et_c_snacks);
        et_c_sandwich=(EditText)findViewById(R.id.et_c_sandwich);
        et_cost=(EditText)findViewById(R.id.et_cost);

        int userId = db.getUserId(username);
        Cursor c= db.getCartDetails(userId);

        if(c.moveToFirst()){
            do{
                int itemid=c.getInt(c.getColumnIndex("itemid"));
                float cost=db.getCost(itemid);
                float buy_quantity=c.getInt(c.getColumnIndex("buy_quantity"));
                if(itemid==81){
                    qua_drinks.setText(String.valueOf(buy_quantity));
                    cost = cost * buy_quantity;
                    et_c_drinks.setText(String.valueOf(cost));
                }
                else if(itemid==82){
                    qua_sandwich.setText(String.valueOf(buy_quantity));
                    cost = cost* buy_quantity;
                    et_c_sandwich.setText(String.valueOf(cost));
                }
                else if(itemid==83){
                    qua_snacks.setText(String.valueOf(buy_quantity));
                    cost = cost* buy_quantity;
                    et_c_snacks.setText(String.valueOf(cost));
                }
            }while(c.moveToNext());
        }
        et_c_drinks=(EditText)findViewById(R.id.et_c_drinks);
        et_c_snacks=(EditText)findViewById(R.id.et_c_snacks);
        et_c_sandwich=(EditText)findViewById(R.id.et_c_sandwich);

        float total_cost=Float.parseFloat(et_c_drinks.getText().toString()) + Float.parseFloat(et_c_snacks.getText().toString()) + Float.parseFloat(et_c_sandwich.getText().toString());
        et_cost.setText(String.valueOf(total_cost));
    }
}