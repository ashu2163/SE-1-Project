package com.squirrel.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.myApp.simpleregistrationandloginapplication.R;
import com.squirrel.constants.SquirrelConstants;
import com.squirrel.dao.DatabaseHelper;

import java.util.ArrayList;


public class AssignActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    GridView gv_assigned;
    Button btn_assign;
    Spinner sp_vehicle,sp_location,sp_operator,sp_slotbegin;
    TextView tv_slotend;
    int vehid,userid,duration,slotbegin,slotend=0;
    String locationid="";
    SharedPreferences sharedpreferences;
    DatabaseHelper databaseHelper;
    ArrayList<String> spSlotBeginList;
    ArrayAdapter<String> adapterSlotBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);
        getSupportActionBar().hide();
        databaseHelper = new DatabaseHelper(this);
        gv_assigned = (GridView)findViewById(R.id.gv_assigned);
        sp_vehicle = (Spinner)findViewById(R.id.sp_vehicle);
        sp_location = (Spinner)findViewById(R.id.sp_location);
        sp_operator = (Spinner)findViewById(R.id.sp_operator);
        sp_slotbegin = (Spinner)findViewById(R.id.sp_slotbegin);
        tv_slotend = (TextView)findViewById(R.id.tv_slotend);
        btn_assign = (Button)findViewById(R.id.btn_assign);
        btn_assign.setEnabled(false);
        sp_location.setEnabled(false);
        sp_operator.setEnabled(false);
        sp_slotbegin.setEnabled(false);
        tv_slotend.setEnabled(false);
        ArrayList<String> gridViewList=new ArrayList<String>();
        ArrayAdapter<String> adapterGrid=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,gridViewList);

        ArrayList<String> spVehicleList=new ArrayList<String>();
        ArrayAdapter<String> adapterVehicle=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,spVehicleList);

        ArrayList<String> spLocationList=new ArrayList<String>();
        ArrayAdapter<String> adapterLocation=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,spLocationList);

        ArrayList<String> spOperatorList=new ArrayList<String>();
        ArrayAdapter<String> adapterOperator=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,spOperatorList);

        spSlotBeginList=new ArrayList<String>();
        adapterSlotBegin=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,spSlotBeginList);

        databaseHelper = new DatabaseHelper(this);
        Cursor c=databaseHelper.getVehicleSchedule();
        gridViewList.add("vehid");
        gridViewList.add("locid");
        gridViewList.add("opid");
        gridViewList.add("slotbegin");
        gridViewList.add("slotend");
        gv_assigned.setAdapter(adapterGrid);
        if(c.moveToFirst())
        {
            do
            {
                String vehid=c.getString(c.getColumnIndex("vehid"));
                String locid=c.getString(c.getColumnIndex("locid"));
                String opid=c.getString(c.getColumnIndex("opid"));
                String slotbegin=c.getString(c.getColumnIndex("slotbegin"));
                String slotend=c.getString(c.getColumnIndex("slotend"));
                //add in to array list
                gridViewList.add(vehid);
                gridViewList.add(locid);
                gridViewList.add(opid);
                gridViewList.add(slotbegin);
                gridViewList.add(slotend);
                gv_assigned.setAdapter(adapterGrid);
            }while(c.moveToNext());//Move the cursor to the next row.
        }

        c=databaseHelper.getAvailableVehcilesForNextDay();
        spVehicleList.add("select vehicle");
        sp_vehicle.setAdapter(adapterVehicle);
        if(c.moveToFirst())
        {
            do
            {
                String vehid=c.getString(c.getColumnIndex("vehid"));
                String vehname=c.getString(c.getColumnIndex("vehname"));
                spVehicleList.add(vehid+":"+vehname);
                sp_vehicle.setAdapter(adapterVehicle);
            }while(c.moveToNext());//Move the cursor to the next row.
        }

        c=databaseHelper.getLocations();
        spLocationList.add("select location");
        sp_location.setAdapter(adapterLocation);
        if(c.moveToFirst())
        {
            do
            {
                String locid=c.getString(c.getColumnIndex("locid"));
                String locname=c.getString(c.getColumnIndex("locname"));
                String duration=c.getString(c.getColumnIndex("duration"));
                spLocationList.add(locid+":"+locname+":"+duration+" hours");
                sp_location.setAdapter(adapterLocation);
            }while(c.moveToNext());//Move the cursor to the next row.
        }

        c=databaseHelper.getOperators();
        spOperatorList.add("select operator");
        sp_operator.setAdapter(adapterOperator);
        if(c.moveToFirst())
        {
            do
            {
                String userid=c.getString(c.getColumnIndex("userid"));
                String fname=c.getString(c.getColumnIndex("fname"));
                String lname=c.getString(c.getColumnIndex("lname"));
                spOperatorList.add(userid+":"+fname+" "+lname);
                sp_operator.setAdapter(adapterOperator);
            }while(c.moveToNext());//Move the cursor to the next row.
        }
        spSlotBeginList.add("select start time");
        sp_slotbegin.setAdapter(adapterSlotBegin);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sp_vehicle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    sp_location.setEnabled(true);
                    String temp=parent.getItemAtPosition(position).toString();
                    vehid=Integer.valueOf(temp.split(":")[0]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    sp_operator.setEnabled(true);
                    String temp=parent.getItemAtPosition(position).toString();
                    locationid=temp.split(":")[0];
                    duration=Integer.valueOf((temp.split(":")[2]).replaceAll(" hours",""));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_operator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    sp_slotbegin.setEnabled(true);
                    String temp=parent.getItemAtPosition(position).toString();
                    userid=Integer.valueOf(temp.split(":")[0]);
                    spSlotBeginList.clear();
                    spSlotBeginList.add("select start time");
                    sp_slotbegin.setAdapter(adapterSlotBegin);
                    //for(int i=SquirrelConstants.shiftBegin;i<=SquirrelConstants.shiftEnd-duration;i=i+duration){
                    for(int i=SquirrelConstants.shiftBegin;i<=SquirrelConstants.shiftEnd-duration;i=i+1){
                        spSlotBeginList.add(String.valueOf(i));
                    }
                    sp_slotbegin.setAdapter(adapterSlotBegin);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_slotbegin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    slotbegin=Integer.valueOf(parent.getItemAtPosition(position).toString());
                    slotend=slotbegin+duration;
                    tv_slotend.setText("end time:"+slotend);
                    btn_assign.setEnabled(true);
                    btn_assign.setTextColor(getResources().getColor(R.color.colorCream));
                    btn_assign.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(vehid==0||userid==0||locationid.equals("")||slotbegin==0||slotend==0){
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor c=databaseHelper.getMinMaxSlots(String.valueOf(vehid));
                    if(c.moveToFirst()){
                        int minslotbegin=c.getInt(c.getColumnIndex("minslotbegin"));
                        int maxslotend=c.getInt(c.getColumnIndex("maxslotend"));
                        if(minslotbegin<slotbegin && maxslotend>slotbegin)    {
                            Toast.makeText(getApplicationContext(), "Invalid slot", Toast.LENGTH_SHORT).show();
                            startActivity(getIntent());
                        } else {
                            Boolean insertVehicleSchedule = databaseHelper.insertVehicleSchedule(vehid,locationid,userid,slotbegin,slotend);
                            if(insertVehicleSchedule == true){
                                Toast.makeText(getApplicationContext(), "Successfully assigned", Toast.LENGTH_SHORT).show();
                                startActivity(getIntent());
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Slot already taken", Toast.LENGTH_SHORT).show();
                                startActivity(getIntent());
                            }
                        }
                    }
                }
                startActivity(getIntent());
            }
        });
    }



}