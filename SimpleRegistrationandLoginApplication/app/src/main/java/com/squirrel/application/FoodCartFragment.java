package com.squirrel.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodCartFragment extends Fragment {
    private ListView listView;
    private ArrayList<String> stringArrayList;
    DatabaseHelper db;
    public static final String MyPREFERENCES = "MyPrefs";
    View view;
    ArrayList<String> list;
    private ArrayAdapter<String> stringArrayAdapter;
    public FoodCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_cart, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        db=new DatabaseHelper(getActivity());
        Cursor vname = db.getVehicleName("cart");

        //listView.setAdapter(new ArrayAdapter<String>());
        list=new ArrayList<String>();
        ArrayAdapter<String> adplist=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adplist);
        if(vname.moveToFirst()){
            do{
                String vehname=vname.getString(vname.getColumnIndex("vehname"));
                String slotbegin=vname.getString(vname.getColumnIndex("slotbegin"));
                String slotend=vname.getString(vname.getColumnIndex("slotend"));
                String locname=vname.getString(vname.getColumnIndex("locname"));
                String con="Name: "+vehname+"  Time: "+slotbegin+" to "+slotend+"\nLocation: "+locname;

                list.add(con);

                listView.setAdapter(adplist);
            }while(vname.moveToNext());
        }

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String selected=String.valueOf(parent.getItemAtPosition(position));
                        String[] name=selected.split(" ");
                        //Toast.makeText(getContext(),selected,Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor sharedPref=getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).edit();
                        sharedPref.putString("vehname",name[1]);
                        sharedPref.apply();
                        Intent intent= new Intent(getContext(), SelectedVehicleInventory.class);
                        startActivity(intent);
                    }
                }
        );
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        //DealAdapter adapter = new DealAdapter(getActivity(), R.layout.fragment_food_cart, stringArrayList);
//        }

        //listView.setAdapter(adapter);



    }

    }
