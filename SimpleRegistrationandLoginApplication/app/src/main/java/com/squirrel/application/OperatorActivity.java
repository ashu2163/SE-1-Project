package com.squirrel.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import com.squirrel.models.User;
import android.widget.Button;
import java.util.ArrayList;
import java.util.HashMap;

import android.view.View;
import android.widget.Toast;
public class OperatorActivity extends   AppCompatActivity{

    DatabaseHelper databaseHelper;
    ArrayList<User> arrayList;
    ListView listView;
    ArrayList<String> list;
    MyAdapteroperator myAdapter;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;
    public String uid;
    Button btnop;
    Button btnop_del, btnlogout;
    ArrayList<String> al=new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
        getSupportActionBar().hide();
        databaseHelper = new DatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listView);
        Cursor c = databaseHelper.getuserData();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.Profile:
                        Intent intent2=new Intent(OperatorActivity.this,UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.action_recents:
                        Intent intent3=new Intent( OperatorActivity.this, ManagerHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });

        btnlogout = (Button)findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OperatorActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        list=new ArrayList<String>();
        ArrayAdapter<String> adplist=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adplist);
        if(c.moveToFirst()){
            do{
                String fname=c.getString(c.getColumnIndex("fname"));
                String lname=c.getString(c.getColumnIndex("lname"));
                String uname=c.getString(c.getColumnIndex("uname"));
                String vehname=c.getString(c.getColumnIndex("vehname"));
                String locname=c.getString(c.getColumnIndex("locname"));
                String slotend=c.getString(c.getColumnIndex("slotend"));

                String con="First_Name: "+fname+" Last_Name: "+lname+" Vehicle: "+vehname+" Location: "+locname+" Slot_End: "+slotend;

                list.add(con);

                listView.setAdapter(adplist);
            }while(c.moveToNext());
        }



        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        //SharedPreferences.Editor sharedPref=sharedPreferences.edit();
                        String selected=String.valueOf(parent.getItemAtPosition(position));
                        //String[] name=selected.split(": ");
                        //Toast.makeText(getApplicationContext(),name.length,Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPreference = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                        SharedPreferences.Editor sharedPref = sharedPreference.edit();
                        sharedPref.putString("details",selected);
                        sharedPref.commit();
                        Intent intent = new Intent(OperatorActivity.this, EditoperatorActivity.class);
                        startActivity(intent);
                    }
                }
        );

        /*btnop_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " delete clicked", Toast.LENGTH_SHORT).show();

                for (int i = 0; i < MyAdapteroperator.arrayList.size(); i++){
                    if(MyAdapteroperator.arrayList.get(i).getSelected()) {
                        uid = String.valueOf(MyAdapteroperator.arrayList.get(i).getUserid());

                    }
                }

                Toast.makeText(getApplicationContext(), "id"+uid+"", Toast.LENGTH_SHORT).show();
////
                Boolean query = databaseHelper.deleteOperator(uid);
                if(query == true){
                    Toast.makeText(getApplicationContext(), "data changed successfully", Toast.LENGTH_SHORT).show();

                    listView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    Intent intent = new Intent(OperatorActivity.this, OperatorActivity.class);
                    startActivity(intent);
                } else {

                }
//
////
////
            }
        });*/
    }

//    private void loadDataInListView(){
//
//        myAdapter = new MyAdapteroperator(this,c);
//        l1.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();
//    }

}
