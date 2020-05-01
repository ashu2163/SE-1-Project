package com.squirrel.application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squirrel.app.R;


public class ManagerHomeActivity extends AppCompatActivity {

        Button btn_logout,btn_assign,btn_vehicle,btn_location,btn_revenue, btn_operator;
    BottomNavigationView bottomNavigationView;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreference;
        //TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerhome);


        btn_logout = (Button)findViewById(R.id.logout);
        btn_assign = (Button)findViewById(R.id.btn_assign);
        btn_vehicle=(Button)findViewById(R.id.btn_vehicle);
        btn_location=(Button)findViewById(R.id.Location);
        btn_revenue=(Button)findViewById(R.id.Revenue);
        //username=(TextView)findViewById(R.id.username);

        sharedpreference = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        final String uname= sharedpreferences.getString("username","User");

        final String role= sharedpreferences.getString("role","User");
        final String pass=sharedpreferences.getString("password","User");

        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("username",uname );
        editor.putString("password", pass);
        editor.putString("role",role );
        editor.commit();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recents:
                        //CODE THIS
                        Intent intent3=new Intent( ManagerHomeActivity.this, ManagerHomeActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.Profile:
//                        SharedPreferences.Editor editor = sharedpreference.edit();
//
//                        editor.putString("username",uname );
//                        editor.putString("password", pass);
//                        editor.putString("role",role );
//                        editor.commit();
                        Intent intent2=new Intent(ManagerHomeActivity.this,UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;

//
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
                Intent intent = new Intent(ManagerHomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedpreferences.edit();
//                editor.clear();
//                editor.commit();
                Intent intent = new Intent(ManagerHomeActivity.this, AssignActivity.class);
                startActivity(intent);
            }
        });
        btn_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedpreferences.edit();
//                editor.clear();
//                editor.commit();
                Intent intent = new Intent(ManagerHomeActivity.this, ViewManagerLocation.class);
                startActivity(intent);
            }
        });

        btn_revenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedpreferences.edit();
//                editor.clear();
//                editor.commit();
                Intent intent = new Intent(ManagerHomeActivity.this, ViewManagerRevenue.class);
                startActivity(intent);
            }
        });
    }
}