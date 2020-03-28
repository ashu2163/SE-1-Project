package com.squirrel.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;

import java.util.ArrayList;

public class ViewManagerRevenue extends AppCompatActivity {
    private ListView listView;
    Button btn_logout;
    TextView Total_Rev;
    private ArrayList<String> stringArrayList;
    ArrayList<String> list;
    DatabaseHelper db;
    BottomNavigationView bottomNavigationView;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_revenue);
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
                    case R.id.action_cart:
                        Intent intent1=new Intent( ViewManagerRevenue.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.Profile:
                        SharedPreferences.Editor editor = sharedpreference.edit();

                        editor.putString("username",uname );
                        editor.putString("password", pass);
                        editor.putString("role",role );
                        editor.commit();
                        Intent intent2=new Intent(ViewManagerRevenue.this,UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_recents:
                        Intent intent3=new Intent( ViewManagerRevenue.this, ManagerHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
        btn_logout = (Button) findViewById(R.id.btn_logout);
        Total_Rev= (TextView) findViewById(R.id.textView3);
        listView = (ListView) findViewById(R.id.listView);
        list=new ArrayList<String>();

        db = new DatabaseHelper(this);
        Cursor c=db.getRevenue();
        Float total_revenue = 0.0f;
        ArrayAdapter<String> adplist=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adplist);
        if(c.moveToFirst()){
            do{
                String vehname=c.getString(c.getColumnIndex("vehname"));
                String fname=c.getString(c.getColumnIndex("fname"));
                String lname=c.getString(c.getColumnIndex("lname"));
                String date=c.getString(c.getColumnIndex("payment_date"));
                String revenue=c.getString(c.getColumnIndex("total"));
                total_revenue= total_revenue + Float.parseFloat(revenue);
                String con="Vehicle_Name: "+vehname+"\nOperator Name: "+fname+" "+lname+"\nDate :"+date+"\nRevenue: "+revenue;
                list.add(con);
                listView.setAdapter(adplist);
            }while(c.moveToNext());
            Toast.makeText(getApplicationContext(), "total"+total_revenue+"", Toast.LENGTH_SHORT).show();
            Total_Rev.setText(total_revenue.toString());
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ViewManagerRevenue.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
