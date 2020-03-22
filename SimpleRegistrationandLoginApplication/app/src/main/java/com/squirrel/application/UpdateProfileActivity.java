package com.squirrel.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import java.util.ArrayList;

public class UpdateProfileActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    EditText username, password,fname,lname,email,phone,street_add,city,state,zipcode,cardno,cvv,expdate,cardtype;
    TextView role1;
    Button btn_back, btn_update;
    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        role1=(TextView)findViewById(R.id.role);
        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        email=(EditText)findViewById(R.id.emailadd);
        phone=(EditText)findViewById(R.id.phone);
        street_add=(EditText)findViewById(R.id.street_add);
        city=(EditText)findViewById(R.id.city);
        state=(EditText)findViewById(R.id.state);
        zipcode=(EditText)findViewById(R.id.zipcode);
        cardno=(EditText)findViewById(R.id.cardno);
        cvv=(EditText)findViewById(R.id.cvv);
        expdate=(EditText)findViewById(R.id.exp_date);
        cardtype=(EditText)findViewById(R.id.cardType);


        btn_update=(Button)findViewById(R.id.update_profile);
        btn_back=(Button)findViewById(R.id.button);



        SharedPreferences sharedpreferences = getSharedPreferences(UpdateProfileActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String uname= sharedpreferences.getString("username","User");
        final String role= sharedpreferences.getString("role","User");
       final  String pass=sharedpreferences.getString("password","User");
       db=new DatabaseHelper(this);
       final int userid=db.getUserID(uname);
        Cursor userD=db.getUserDetails(uname);
        Cursor cardD=db.getCardDetails(userid);
        Toast.makeText(getApplicationContext(), "UserId"+userid , Toast.LENGTH_SHORT).show();
        if(role.equals("other")){
            cardtype.setVisibility(View.VISIBLE);
            cardno.setVisibility(View.VISIBLE);
            cvv.setVisibility(View.VISIBLE);
            expdate.setVisibility(View.VISIBLE);
        }
        username.setText(uname);
        password.setText(pass);
        if (userD.moveToFirst()) {
            do {
                String first = userD.getString(userD.getColumnIndex("fname"));
                //Toast.makeText(getApplicationContext(), "fname" + first, Toast.LENGTH_SHORT).show();

                 fname.setText(userD.getString(userD.getColumnIndex("fname")));
                lname.setText(userD.getString(userD.getColumnIndex("lname")));
                street_add.setText(userD.getString(userD.getColumnIndex("street_address")));
                city.setText(userD.getString(userD.getColumnIndex("city")));
                state.setText(userD.getString(userD.getColumnIndex("state")));
                zipcode.setText(userD.getString(userD.getColumnIndex("zipcode")));
                email.setText(userD.getString(userD.getColumnIndex("email")));
                phone.setText(userD.getString(userD.getColumnIndex("phone")));


            } while (userD.moveToNext());
        }


        if (cardD.moveToFirst()) {
            do {
                //String first = userD.getString(userD.getColumnIndex("fname"));
                //Toast.makeText(getApplicationContext(), "fname" + first, Toast.LENGTH_SHORT).show();

                cardno.setText(cardD.getString(cardD.getColumnIndex("cc")));
                cvv.setText(cardD.getString(cardD.getColumnIndex("cvv")));
                expdate.setText(cardD.getString(cardD.getColumnIndex("expiry")));
                cardtype.setText(cardD.getString(cardD.getColumnIndex("cardtype")));



            } while (cardD.moveToNext());
        }
        role1.setText(role);

        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(role.equals("operator")){
                Intent intent = new Intent(UpdateProfileActivity.this, OperatorHomeActivity.class);
                startActivity(intent);
                }
                else if(role.equals("manager")){
                    Intent intent = new Intent(UpdateProfileActivity.this, ManagerHomeActivity.class);
                    startActivity(intent);

                }
                else{
                    Intent intent = new Intent(UpdateProfileActivity.this, UserHomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(role.equals("other")){
                        db.updateCardDetails(userid,cardno.getText().toString(),cvv.getText().toString(),cardtype.getText().toString(),expdate.getText().toString());


                 }
                if (db.updateProfile(userid,fname.getText().toString(),lname.getText().toString(),username.getText().toString(),password.getText().toString(),email.getText().toString(),phone.getText().toString(),street_add.getText().toString(),city.getText().toString(),state.getText().toString(),zipcode.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                    if(role.equals("operator")){
                        Intent intent = new Intent(UpdateProfileActivity.this, OperatorHomeActivity.class);
                        startActivity(intent);
                    }
                    else if(role.equals("manager")){
                        Intent intent = new Intent(UpdateProfileActivity.this, ManagerHomeActivity.class);
                        startActivity(intent);

                    }
                    else{
                        Intent intent = new Intent(UpdateProfileActivity.this, UserHomeActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });




    }
}
