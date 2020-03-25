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


public class OperatorHomeActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> stringArrayList;
    ArrayList<String> list;
    DatabaseHelper db;
    Button btn_logout;

    BottomNavigationView bottomNavigationView;
    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operatorhome);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Intent intent1=new Intent( OperatorHomeActivity.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        break;
                    case R.id.action_home:
                        Intent intent3=new Intent( OperatorHomeActivity.this, OperatorHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
        btn_logout = (Button) findViewById(R.id.btn_logout);
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String uname= sharedpreferences.getString("username","User");
        Toast.makeText(this,uname,Toast.LENGTH_LONG).show();


        listView = (ListView) findViewById(R.id.listView);
        list=new ArrayList<String>();

        db = new DatabaseHelper(this);
        Cursor c=db.getOperatorVehicle(uname);

        ArrayAdapter<String> adplist=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adplist);

        if(c.moveToFirst()){
            do{
                String vehname=c.getString(c.getColumnIndex("vehname"));
                String vehType=c.getString(c.getColumnIndex("vehtype"));
                String locname=c.getString(c.getColumnIndex("locname"));
                String slotbegin=c.getString(c.getColumnIndex("slotbegin"));
                String slotend=c.getString(c.getColumnIndex("slotend"));
                String fname=c.getString(c.getColumnIndex("fname"));
                String lname=c.getString(c.getColumnIndex("lname"));
                String con="Name: "+vehname+ " \nVehicleType: "+vehType+"\nFirst Name: "+fname+"\nLast Name: "+lname+"\nTime: "+slotbegin+" to "+slotend+"\nLocation: "+locname;
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
                        Intent intent= new Intent(getApplicationContext(), ViewOperatorInventory.class);
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
                Intent intent = new Intent(OperatorHomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}