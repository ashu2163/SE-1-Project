package com.squirrel.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;

import java.util.ArrayList;

public class OperatorInventoryFragment extends Fragment {
    TextView ava_drinks, ava_snacks, ava_sandwich, cost_drinks, cost_sandwich, cost_snacks;
    String MY_PREFS_NAME = "MyPrefs";
    Button bt_update;


    private ListView listView;
    private ArrayList<String> stringArrayList;
    DatabaseHelper db;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreference;

    View view;
    ArrayList<String> list;
    private ArrayAdapter<String> stringArrayAdapter;
    public OperatorInventoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_operator_inventory, container, false);

        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences sharedpreferences = getContext().getSharedPreferences(OperatorVehicleFragment.MyPREFERENCES, Context.MODE_PRIVATE);
        final String uname = prefs.getString("username", "User");

//        Toast.makeText(getContext(), uname, Toast.LENGTH_LONG).show();


        ava_drinks = (EditText) view.findViewById(R.id.ava_drinks);
        ava_snacks = (EditText) view.findViewById(R.id.ava_snacks);
        ava_sandwich = (EditText) view.findViewById(R.id.ava_sandwich);

        cost_drinks = (TextView) view.findViewById(R.id.cost_drinks);
        cost_snacks = (TextView) view.findViewById(R.id.cost_snacks);
        cost_sandwich = (TextView) view.findViewById(R.id.cost_sandwich);
        bt_update=(Button) view.findViewById(R.id.btn_update);

        db = new DatabaseHelper(getActivity());
        final int vehId = db.getVehId(uname);

        Cursor c = db.getVehicleInventory_operator(vehId);

        if (c.moveToFirst()) {
            do {
                String itemType = c.getString(c.getColumnIndex("itemtype"));
                String quantity = c.getString(c.getColumnIndex("quantity"));
                String cost = c.getString(c.getColumnIndex("cost"));
                if (itemType.equals("Drinks")) {
                    ava_drinks.setText(quantity);
                    cost_drinks.setText(cost);
                } else if (itemType.equals("Sandwiches")) {
                    ava_sandwich.setText(quantity);
                    cost_sandwich.setText(cost);
                } else if (itemType.equals("Snacks")) {
                    ava_snacks.setText(quantity);
                    cost_snacks.setText(cost);
                }

            } while (c.moveToNext());
        }

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float drinks = Float.parseFloat(ava_drinks.getText().toString());
                float snacks = Float.parseFloat(ava_snacks.getText().toString());
                float sandwiches = Float.parseFloat(ava_sandwich.getText().toString());
                Cursor c = db.getInventoryDetails(vehId);
                if(c.moveToFirst()){
                    do{
                        int itemid=c.getInt(c.getColumnIndex("itemid"));
                        if(itemid==81){
                            db.updateQuantity(vehId,itemid,drinks);
                        }
                        else if(itemid==82){
                            db.updateQuantity(vehId,itemid,sandwiches);
                        }
                        else if(itemid==83){
                            db.updateQuantity(vehId,itemid,snacks);
                        }
                    }while(c.moveToNext());
                }


                Toast.makeText(getContext(), "Quantity Updated Successfully", Toast.LENGTH_LONG).show();
//                Intent intent= new Intent(getContext(), OperatorInventoryFragment.class);
//                startActivity(intent);

            }
        });


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