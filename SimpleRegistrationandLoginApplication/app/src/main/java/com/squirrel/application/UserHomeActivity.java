package com.squirrel.application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.TabLayout;

import com.squirrel.app.R;
import com.squirrel.app.R;


public class UserHomeActivity extends AppCompatActivity {

    Button btn_logout;
    TextView username;
    TabLayout tabLayout;
    TabItem foodTruck;
    TabItem foodCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);


        btn_logout = (Button)findViewById(R.id.logout);
        //username=(TextView)findViewById(R.id.username);

        tabLayout=findViewById(R.id.tl_student);
        foodTruck=findViewById(R.id.et_truck);
        foodCart=findViewById(R.id.et_cart);

        Fragment fragment=new FoodTruckFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.simpleFrameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment=new FoodTruckFragment();
                switch(tab.getPosition()){
                    case 0:
                        fragment= new FoodTruckFragment();
                        break;
                    case 1:
                        fragment=new FoodCartFragment();
                        break;
                    default:
                        fragment=null;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
                Intent intent = new Intent(UserHomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
