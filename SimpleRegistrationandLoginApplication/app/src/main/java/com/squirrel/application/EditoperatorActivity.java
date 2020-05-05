package com.squirrel.application;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import com.squirrel.models.Vehicle;
import android.widget.Button;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class EditoperatorActivity  extends AppCompatActivity{

    DatabaseHelper db;
    EditText uid,firstn,lastn, uname, ed_vehname,ed_locname,ed_slotend;
    Button btn_vehupdate, btn_locupdate;
    String MY_PREFS_NAME="MyPrefs";
    Spinner sp_vehicle;
    int vehid;
    BottomNavigationView bottomNavigationView;
    Button btn_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editoperator);
        getSupportActionBar().hide();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String details=prefs.getString("details",null);
        String arr[]=details.split(": ");
        final String fname=arr[1].split(" ")[0];
        final String lname=arr[2].split(" ")[0];
        final String vehname=arr[3].split(" ")[0];
        String locname=arr[4].split("Slot_End")[0].trim();
        final String slotend=arr[5];
        final ArrayList<String> spVehicleList=new ArrayList<String>();
        final ArrayAdapter<String> adapterVehicle=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,spVehicleList);
        //Toast.makeText(getApplicationContext(),details,Toast.LENGTH_LONG).show();

        db = new DatabaseHelper(this);


        firstn = (EditText)findViewById(R.id.ed_fname);
        firstn.setFocusable(false);
        firstn.setBackgroundColor(Color.TRANSPARENT);
        lastn = (EditText)findViewById(R.id.ed_lname);
        lastn.setFocusable(false);
        lastn.setBackgroundColor(Color.TRANSPARENT);
        //uname = (EditText)findViewById(R.id.ed_uname);
        sp_vehicle = (Spinner)findViewById(R.id.sp_vehicle);
        //ed_vehname = (EditText)findViewById(R.id.ed_vehname);
        ed_locname = (EditText)findViewById(R.id.ed_locname);
        ed_locname.setFocusable(false);
        ed_locname.setBackgroundColor(Color.TRANSPARENT);
        ed_slotend = (EditText)findViewById(R.id.ed_slotend);
        ed_slotend.setFocusable(false);
        ed_slotend.setBackgroundColor(Color.TRANSPARENT);

        firstn.setText(fname);
        lastn.setText(lname);
        //sp_vehicle.setText(vehname);
        ed_locname.setText(locname);
        ed_slotend.setText(slotend);

        //update=(Button)findViewById(R.id.btn_update);
        final String locid=db.getLocationId(locname);

        btn_vehupdate=(Button)findViewById(R.id.btn_vehicleupdate);

        //btn_delete=(Button)findViewById(R.id.btn_delete);

        Cursor c=db.getAvailableVehcilesForNextDay();
        spVehicleList.add("select vehicle");
        sp_vehicle.setAdapter(adapterVehicle);
        if(c.moveToFirst())
        {
            do
            {
                String vehid=c.getString(c.getColumnIndex("vehid"));
                String vehname1=c.getString(c.getColumnIndex("vehname"));
                spVehicleList.add(vehid+":"+vehname1);
                sp_vehicle.setAdapter(adapterVehicle);
            }while(c.moveToNext());//Move the cursor to the next row.
        }


        sp_vehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    String temp = parent.getItemAtPosition(position).toString();
                    vehid = Integer.valueOf(temp.split(":")[0]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.Profile:
                        Intent intent2=new Intent(EditoperatorActivity.this,UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_recents:
                        Intent intent3=new Intent( EditoperatorActivity.this, ManagerHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });
        btn_vehupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int opid = db.getOpId(fname, lname);
                    //ed_vehname = (Spinner) findViewById(R.id.sp_vehicle);
                    //String vehname=ed_vehname.getText().toString();


                    boolean a=db.updateVehname(vehid,opid);
                    if(a==false){
                        Toast.makeText(getApplicationContext(),"Update Failed due to some error!!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Update Successful!!",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(  EditoperatorActivity.this,OperatorActivity.class);
                        startActivity(i);

                    }

                }
            });



        btn_logout = (Button)findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditoperatorActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//            btn_delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int opid = db.getOpId(fname, lname);
//                    //int vehid=db.getVehId(vehname);
//                    boolean b=db.deleteSchedule(opid,vehid,slotend);
//                    if(b==true){
//                        Toast.makeText(getApplicationContext(),"Schedule deleted successfully",Toast.LENGTH_LONG).show();
//                        Intent i=new Intent(  EditoperatorActivity.this,OperatorActivity.class);
//                        startActivity(i);
//
//                    }


//                }
//                });
            




    }
}
