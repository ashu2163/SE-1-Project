package com.squirrel.application;

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

public class CartDetailsActivity extends AppCompatActivity {
    EditText qua_drinks,qua_snacks,qua_sandwich;
    TextView et_c_drinks,et_c_snacks,et_c_sandwich,et_cost;
    Button btn_update;
    DatabaseHelper db;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_details);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Intent intent1=new Intent( CartDetailsActivity.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        break;
                    case R.id.action_home:
                        Intent intent3=new Intent( CartDetailsActivity.this, UserHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });

        SharedPreferences pref = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        final String username=pref.getString("username",null);

        db = new DatabaseHelper(this);

        qua_drinks=(EditText)findViewById(R.id.qua_drinks);
        qua_snacks=(EditText)findViewById(R.id.qua_snacks);
        qua_sandwich=(EditText)findViewById(R.id.qua_sandwich);

        et_c_drinks=(TextView)findViewById(R.id.et_c_drinks);
        et_c_snacks=(TextView)findViewById(R.id.et_c_snacks);
        et_c_sandwich=(TextView)findViewById(R.id.et_c_sandwich);
        et_cost=(TextView)findViewById(R.id.et_cost);

        final int userId = db.getUserId(username);
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
        et_c_drinks=(TextView)findViewById(R.id.et_c_drinks);
        et_c_snacks=(TextView)findViewById(R.id.et_c_snacks);
        et_c_sandwich=(TextView)findViewById(R.id.et_c_sandwich);

        float total_cost=Float.parseFloat(et_c_drinks.getText().toString()) + Float.parseFloat(et_c_snacks.getText().toString()) + Float.parseFloat(et_c_sandwich.getText().toString());
        et_cost.setText(String.valueOf(total_cost));

        btn_update=(Button) findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qua_drinks=(EditText)findViewById(R.id.qua_drinks);
                qua_snacks=(EditText)findViewById(R.id.qua_snacks);
                qua_sandwich=(EditText)findViewById(R.id.qua_sandwich);

                float drinks = Float.parseFloat(qua_drinks.getText().toString());
                float snacks = Float.parseFloat(qua_snacks.getText().toString());
                float sandwiches = Float.parseFloat(qua_sandwich.getText().toString());

                SharedPreferences prefs = getSharedPreferences(FoodCartFragment.MyPREFERENCES, MODE_PRIVATE);
                String loadedString = prefs.getString("vehname", null);
                int vehId=db.getVehId(loadedString);

                String str=" ";

                float quan=db.getItemQuantity(vehId,81);
                if(drinks>quan){
                    str+=" Please add drinks less than available quantity ";
                }
                else if(db.findItemInCart(userId,81)){
                    if(db.updateCart(userId,81,drinks)){
                        str+="Quantity of Drinks updated successfully";
                    }
                }


                quan=db.getItemQuantity(vehId,83);
                if(snacks>quan){
                    str+=" Please add snacks less than available quantity ";
                }
                else if(db.findItemInCart(userId,83)){
                    if(db.updateCart(userId,83,snacks)){
                        str+=" Quantity of snacks updated successfully";
                    }
                }


                quan=db.getItemQuantity(vehId,82);
                if(sandwiches>quan){
                    str+=" Please add sandwiches less than available quantity ";
                }
                else if(db.findItemInCart(userId,82)) {
                    if(db.updateCart(userId, 82, sandwiches)){
                        str+=" Quantity of sandwiches updated successfully";
                    }
                }

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
                et_c_drinks=(TextView)findViewById(R.id.et_c_drinks);
                et_c_snacks=(TextView)findViewById(R.id.et_c_snacks);
                et_c_sandwich=(TextView)findViewById(R.id.et_c_sandwich);

                float total_cost=Float.parseFloat(et_c_drinks.getText().toString()) + Float.parseFloat(et_c_snacks.getText().toString()) + Float.parseFloat(et_c_sandwich.getText().toString());
                et_cost.setText(String.valueOf(total_cost));

                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
                }

        });



    }
}