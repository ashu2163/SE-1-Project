package com.squirrel.application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import java.util.ArrayList;


public class OperatorHomeActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> stringArrayList;
    ArrayList<String> list;
    DatabaseHelper db;
    Button btn_logout;
    TabLayout tabLayout;
    TabItem Vehicle;
    TabItem Inventory;
    BottomNavigationView bottomNavigationView;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operatorhome);
        sharedpreference = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        final String uname= sharedpreferences.getString("username","User");
        final String role= sharedpreferences.getString("role","User");
        final String pass=sharedpreferences.getString("password","User");
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.Profile:
                        SharedPreferences.Editor editor = sharedpreference.edit();

                        editor.putString("username",uname );
                        editor.putString("password", pass);
                        editor.putString("role",role );
                        editor.commit();
                        Intent intent2=new Intent(OperatorHomeActivity.this,UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_recents:
                        Intent intent3=new Intent( OperatorHomeActivity.this, OperatorHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
        btn_logout = (Button) findViewById(R.id.btn_logout);
        //username=(TextView)findViewById(R.id.username);

        tabLayout = findViewById(R.id.tabLayout_operator);
        Vehicle = findViewById(R.id.et_vehicle);
        Inventory = findViewById(R.id.et_inventory);

        Fragment fragment = new OperatorVehicleFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.simpleFrameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = new OperatorVehicleFragment();
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new OperatorVehicleFragment();
                        break;
                   case 1:
                        fragment = new OperatorInventoryFragment();
                        break;
                    default:
                        fragment = null;

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
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OperatorHomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}