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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;

import java.util.ArrayList;
/**
 * A simple {@link Fragment} subclass.
 */
public class OperatorVehicleFragment extends Fragment {
    private ListView listView;
    private ArrayList<String> stringArrayList;
    DatabaseHelper db;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreference;
    TextView Total_Revenue;

    View view;
    ArrayList<String> list;
    private ArrayAdapter<String> stringArrayAdapter;
    public OperatorVehicleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_operator_vehicle, container, false);
        sharedpreference = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences sharedpreferences = getContext().getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        final String uname= sharedpreferences.getString("username","User");
        Float total_revenue = 0.0f;
        listView = (ListView) view.findViewById(R.id.listView);
        list=new ArrayList<String>();
        Total_Revenue=(TextView) view.findViewById(R.id.textView5);

        db=new DatabaseHelper(getActivity());

        Cursor c=db.getOperatorVehicle(uname);

        ArrayAdapter<String> adplist=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,list);
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
//                Toast.makeText(getContext(),vehname, Toast.LENGTH_LONG).show();
            }while(c.moveToNext());
//
        }
        c=db.getRevenueOperator(uname);
        if(c.moveToFirst()){
            do{
                String revenue = c.getString(c.getColumnIndex("total_cost"));
                total_revenue = total_revenue+ Float.parseFloat(revenue);
            }while(c.moveToNext());
            Total_Revenue.setText(total_revenue.toString());
        }

        return view;
    }
//

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        //DealAdapter adapter = new DealAdapter(getActivity(), R.layout.fragment_food_cart, stringArrayList);
//        }

        //listView.setAdapter(adapter);



    }
}
