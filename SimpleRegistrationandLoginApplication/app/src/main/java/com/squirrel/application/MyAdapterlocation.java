package com.squirrel.application;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.models.Location;

import java.util.ArrayList;

public class MyAdapterlocation extends BaseAdapter {
    private Context context;
    public static ArrayList<Location> arrayList;



    public MyAdapterlocation(Context context, ArrayList<Location> arrayListl)
    {
        this.context = context;
        this.arrayList = arrayListl;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final RadioButton radioButton;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.mycustomlistviewlocation, null);
        TextView t1_id = (TextView) view.findViewById(R.id.id_txt);
        TextView t2_name = (TextView) view.findViewById(R.id.name_txt);
        TextView t3_duration = (TextView) view.findViewById(R.id.duration_txt);

        Location location = arrayList.get(i);
        t1_id.setText(String.valueOf(location.getLocid()));
        t2_name.setText(location.getLocname());
        t3_duration.setText(String.valueOf(location.getDuration()));


        radioButton = (RadioButton) view.findViewById(R.id.rb);


        radioButton.setChecked(arrayList.get(i).getSelected());


        radioButton.setTag( i);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Integer pos = (Integer) radioButton.getTag();
                //Toast.makeText(context, "radio button  "+pos+" clicked!", Toast.LENGTH_SHORT).show();

                if(arrayList.get(pos).getSelected()){
                    arrayList.get(pos).setSelected(false);
                }else {
                    arrayList.get(pos).setSelected(true);
                }

            }
        });
        return view;
    }


    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    private class ViewHolder {

        private TextView id;
        private TextView name;
        private TextView duration;

        protected  RadioButton radioButton;

    }
}
