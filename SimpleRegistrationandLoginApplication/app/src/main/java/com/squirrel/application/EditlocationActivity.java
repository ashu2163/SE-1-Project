package com.squirrel.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import com.squirrel.models.Location;

import java.util.ArrayList;

public class EditlocationActivity extends AppCompatActivity {
    MyAdapterlocation myAdapter;
    DatabaseHelper databaseHelper;
    private EditText id,name,dur;
    Button btn_savel;
    public static ArrayList<Location> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editlocation);
        getSupportActionBar().hide();

        databaseHelper = new DatabaseHelper(this);
        id = (EditText) findViewById(R.id.lid);
        name = (EditText) findViewById(R.id.ln);
        dur = (EditText) findViewById(R.id.ld);
//

        for (int i = 0; i < MyAdapterlocation.arrayList.size(); i++){
            if(MyAdapterlocation.arrayList.get(i).getSelected()) {
                id.setText(id.getText() + MyAdapterlocation.arrayList.get(i).getLocid());
                name.setText(name.getText()  + MyAdapterlocation.arrayList.get(i).getLocname());
                dur.setText(String.valueOf(dur.getText()) + MyAdapterlocation.arrayList.get(i).getDuration());

            }
        }

        btn_savel = (Button)findViewById(R.id.button);
        btn_savel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                String locid = id.getText().toString();
                String locname = name.getText().toString();
                String duration = dur.getText().toString();

                Toast.makeText(getApplicationContext(), "name "+locname+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "id"+locid+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "duration"+duration+"", Toast.LENGTH_SHORT).show();

                Boolean query = databaseHelper.editLocation(locid,locname,duration);



                if(query == true){
                    Toast.makeText(getApplicationContext(), "data changed successfully", Toast.LENGTH_SHORT).show();

                    // myAdapter.notifyDataSetChanged();
                    Intent intent = new Intent(EditlocationActivity.this, ViewManagerLocation.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to update data", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditlocationActivity.this, EditlocationActivity.class);
                    startActivity(intent);
                }


            }
        });
    }
}

