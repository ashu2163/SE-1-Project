package com.squirrel.application;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import com.squirrel.app.R;
import com.squirrel.models.User;
import com.squirrel.models.Vehicle;
import android.widget.RadioButton;
import java.util.ArrayList;

public class MyAdapteroperator extends BaseAdapter {

    private Context context;
    public static ArrayList<User> arrayList;



    public MyAdapteroperator(Context context, ArrayList<User> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
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
        // final ViewHolder holder;
        final  RadioButton radioButton;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.mycustomoperatorlistview, null);
        TextView t1_id = (TextView) view.findViewById(R.id.id_txt);
        TextView t2_name = (TextView) view.findViewById(R.id.fname_txt);
        TextView t3_lname = (TextView) view.findViewById(R.id.lname_txt);
        // Button  b1 = (Button) view.findViewById(R.id.edit_btn);
        User user = arrayList.get(i);
        t1_id.setText(String.valueOf(user.getUserid()));
        t2_name.setText(user.getFname());
        t3_lname.setText(user.getLname());



        radioButton = (RadioButton) view.findViewById(R.id.rb);


        radioButton.setChecked(arrayList.get(i).getSelected());


        radioButton.setTag( i);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Integer pos = (Integer) radioButton.getTag();
                Toast.makeText(context, "radio button  "+pos+" clicked!", Toast.LENGTH_SHORT).show();

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

        protected  RadioButton radioButton;

    }

}
