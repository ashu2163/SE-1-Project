package com.squirrel.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.squirrel.application.CartDetailsActivity.*;

public class Conformation extends AppCompatActivity {
    Button btn_logout;
    BottomNavigationView bottomNavigationView;
    TextView conformation_num;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_conformation);

        btn_logout = (Button) findViewById(R.id.btn_logout);
        conformation_num = (TextView) findViewById(R.id.conformation_num);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        SharedPreferences pref = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        final String username=pref.getString("username",null);
        Intent intent = getIntent();
        String oi = intent.getStringExtra("order_id");
        int orderid=Integer.parseInt(oi);
        Float tcost=intent.getFloatExtra("tcost",0.0f);
//        Float tc=Float.parseFloat(tcost);
        conformation_num.setText("Order ID: "+oi);
        db = new DatabaseHelper(this);
        final int userId = db.getUserId(username);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Intent intent1 = new Intent(Conformation.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        Intent intent2 = new Intent(Conformation.this, UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_home:
                        Intent intent3 = new Intent(Conformation.this, UserHomeActivity.class);
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
                        Intent intent = new Intent(Conformation.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });



        SharedPreferences prefs = getSharedPreferences(FoodCartFragment.MyPREFERENCES, MODE_PRIVATE);
        String loadedString = prefs.getString("vehname", null);
        int vehId=db.getVehIdUser(loadedString);


        final Cursor c = db.getCartDetails(userId);


        int count_Drinks = 0;
        int count_Sandwiches = 0;
        int count_Snacks = 0;


        if(c.moveToFirst()){
            do{

                int itemid=c.getInt(c.getColumnIndex("itemid"));
                int buy_quantity=c.getInt(c.getColumnIndex("buy_quantity"));
                if(itemid==81){
                    count_Drinks = buy_quantity;
                    int qua = db.getQua_Payment(vehId,itemid);
//                    Log.d("quantity","ELLLOOOOOOOOOOOO" + qua);
                    db.payment_updateInventory(qua,count_Drinks,vehId,itemid);
                }
                else if(itemid==82){
                    count_Sandwiches = buy_quantity;
                    int qua = db.getQua_Payment(vehId,itemid);
                    db.payment_updateInventory(qua,count_Sandwiches,vehId,itemid);
                }
                else if(itemid==83){
                    count_Snacks = buy_quantity;
                    int qua = db.getQua_Payment(vehId,itemid);
                    db.payment_updateInventory(qua,count_Snacks,vehId,itemid);
                }
            }while(c.moveToNext());
        }



        int opid=db.getOpId1(vehId);
        db.insertPayment(orderid,userId,vehId,opid,tcost);
        // CLear Cart
        Boolean query = db.deleteCartEntry(userId);




    }
}




