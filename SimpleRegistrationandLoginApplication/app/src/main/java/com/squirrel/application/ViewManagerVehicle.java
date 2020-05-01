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
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import java.util.ArrayList;

public class ViewManagerVehicle extends AppCompatActivity {
    private ListView listView;
    Button btn_logout;
    private ArrayList<String> stringArrayList;
    ArrayList<String> list;
    DatabaseHelper db;
    BottomNavigationView bottomNavigationView;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreference = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        final String uname= sharedpreferences.getString("username","User");
//        Toast.makeText(getApplicationContext(), "fname" + uname, Toast.LENGTH_SHORT).show();
        final String role= sharedpreferences.getString("role","User");
        final String pass=sharedpreferences.getString("password","User");

        setContentView(R.layout.activity_manager_vehicle);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Intent intent1=new Intent( ViewManagerVehicle.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.Profile:
                        SharedPreferences.Editor editor = sharedpreference.edit();

                        editor.putString("username",uname );
                        editor.putString("password", pass);
                        editor.putString("role",role );
                        editor.commit();

                        Intent intent2=new Intent(ViewManagerVehicle.this,UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_recents:
                        Intent intent3=new Intent( ViewManagerVehicle.this, ManagerHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
        btn_logout = (Button) findViewById(R.id.btn_logout);
        listView = (ListView) findViewById(R.id.listView);
        list=new ArrayList<String>();

        db = new DatabaseHelper(this);
        Cursor c=db.getVehicleList();

        ArrayAdapter<String> adplist=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adplist);
        if(c.moveToFirst()){
            do{
                //String vehid=c.getString(c.getColumnIndex("vehid"));
                String vehname=c.getString(c.getColumnIndex("vehname"));
                String vehtype=c.getString(c.getColumnIndex("vehtype"));
                String operatorfname=c.getString(c.getColumnIndex("fname"));
                String operatorlname=c.getString(c.getColumnIndex("lname"));
                String locname=c.getString(c.getColumnIndex("locname"));
                String date=c.getString(c.getColumnIndex("scheduled_date"));
                String slotbegin=c.getString(c.getColumnIndex("slotbegin"));
                String slotend=c.getString(c.getColumnIndex("slotend"));
                String con="Name: "+vehname+" Type:"+vehtype+"\nDate :"+date+" Time: "+slotbegin+" to "+slotend+"\nOperator Name: "+operatorfname+" "+operatorlname+"\nLocation: "+locname;
                list.add(con);
                listView.setAdapter(adplist);
            }while(c.moveToNext());
        }
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String selected=String.valueOf(parent.getItemAtPosition(position));
                        String[] name=selected.split(" ");
                        //Toast.makeText(getContext(),selected,Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor sharedPref=getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
                        sharedPref.putString("name",name[1]);
                        sharedPref.apply();
                        Intent intent= new Intent(getApplicationContext(), ViewManagerInventory.class);
                        startActivity(intent);
                    }
                }
        );
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ViewManagerVehicle.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
