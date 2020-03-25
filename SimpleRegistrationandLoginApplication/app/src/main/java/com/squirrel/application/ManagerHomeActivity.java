package com.squirrel.application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squirrel.app.R;


public class ManagerHomeActivity extends AppCompatActivity {

        Button btn_logout,btn_assign,btn_vehicle;
        Button btn_operator;
        //TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerhome);


        btn_logout = (Button)findViewById(R.id.logout);
        btn_assign = (Button)findViewById(R.id.btn_assign);
        btn_vehicle=(Button)findViewById(R.id.btn_vehicle);
        //username=(TextView)findViewById(R.id.username);

        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String uname= sharedpreferences.getString("username","User");
        //username.setText(uname);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ManagerHomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(ManagerHomeActivity.this, AssignActivity.class);
                startActivity(intent);
            }
        });
        btn_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(ManagerHomeActivity.this, ViewManagerVehicle.class);
                startActivity(intent);
            }
        });

        btn_operator=(Button)findViewById(R.id.Operator);

        btn_operator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagerHomeActivity.this, OperatorActivity.class);
                startActivity(intent);

            }
        });


    }
}