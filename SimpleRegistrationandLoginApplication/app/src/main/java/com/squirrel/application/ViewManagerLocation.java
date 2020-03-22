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
import android.widget.ListView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import com.squirrel.models.Location;

import java.util.ArrayList;

public class ViewManagerLocation extends AppCompatActivity {
    ListView l1;

    DatabaseHelper databaseHelper;
    ArrayList<Location> arrayList;
    MyAdapterlocation myAdapter;
    public String lid;
    Button btnedit;
    Button btnop_del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerlocation);

        getSupportActionBar().hide();

        databaseHelper = new DatabaseHelper(this);
        l1 = (ListView) findViewById(R.id.listview2);
        arrayList = new ArrayList<>();
        loadDataInListView();

        btnedit = (Button) findViewById(R.id.btn);
        btnop_del = (Button) findViewById(R.id.delete);


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(ViewManagerLocation.this,EditlocationActivity.class);
                startActivity(intent1);

                l1.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();


            }
        });



        btnop_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), " delete clicked", Toast.LENGTH_SHORT).show();



                for (int i = 0; i < MyAdapterlocation.arrayList.size(); i++){
                    if(MyAdapterlocation.arrayList.get(i).getSelected()) {
                        lid = String.valueOf(MyAdapterlocation.arrayList.get(i).getLocid());

                    }
                }


                Boolean query = databaseHelper.deleteleLocation(lid);
                if(query == true){
                    Toast.makeText(getApplicationContext(), "location deleted successfully", Toast.LENGTH_SHORT).show();

                    l1.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                    Intent intent = new Intent(ViewManagerLocation.this, ViewManagerLocation.class);
                    startActivity(intent);
                } else {

                }


            }
        });
    }

    private void loadDataInListView(){


        arrayList = databaseHelper.getAllData();
        myAdapter = new MyAdapterlocation(this,arrayList);
        l1.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }


}
