package com.squirrel.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import java.util.ArrayList;

public class UpdateProfileActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    EditText username,fname,lname,email,phone,street_add,city,state,zipcode,cardno,cvv,expdate,cardtype;
    TextView role1;
    Button btn_back, btn_update,btn_edit;
    RadioGroup rg;
    RadioButton rb;
    DatabaseHelper db;
    BottomNavigationView bottomNavigationView, bottomView_others;
    //public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        username = (EditText)findViewById(R.id.username);
       // password = (EditText)findViewById(R.id.password);
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
        rg = (RadioGroup) findViewById(R.id.et_rgroup);


        btn_update=(Button)findViewById(R.id.update_profile);
        btn_back=(Button)findViewById(R.id.button);
        btn_edit=(Button)findViewById(R.id.edit);

        sharedpreference = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences sharedpreferences = getSharedPreferences(UpdateProfileActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        final String uname= sharedpreferences.getString("username","User");
        final String role= sharedpreferences.getString("role","User");
       final  String pass=sharedpreferences.getString("password","User");


        bottomView_others = (BottomNavigationView) findViewById(R.id.bottom_navigation1);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        if(role.equals("other")){
            bottomView_others.setVisibility(View.VISIBLE);
            cardtype.setVisibility(View.VISIBLE);
            cardno.setVisibility(View.VISIBLE);
            cvv.setVisibility(View.VISIBLE);
            expdate.setVisibility(View.VISIBLE);
            role1.setText("Student/Staff");
        }
        else{
            bottomNavigationView.setVisibility(View.VISIBLE);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        // Intent intent1=new Intent( UserHomeActivity.this, CartDetailsActivity.class);
                        //startActivity(intent1);
                        //break;
                    case R.id.Profile:
                        SharedPreferences.Editor editor = sharedpreference.edit();

                        editor.putString("username",uname );
                        editor.putString("password", pass);
                        editor.putString("role",role );
                        editor.commit();
                        Intent intent2=new Intent(UpdateProfileActivity.this,UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_recents:
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
                        break;
                }

                return false;
            }
        });

        bottomView_others.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Intent intent1=new Intent( UpdateProfileActivity.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        SharedPreferences.Editor editor = sharedpreference.edit();

                        editor.putString("username",uname );
                        editor.putString("password", pass);
                        editor.putString("role",role );
                        editor.commit();
                        Intent intent2=new Intent(UpdateProfileActivity.this,UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_home:
                        Intent intent3=new Intent( UpdateProfileActivity.this, UserHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });

        db=new DatabaseHelper(this);
        final int userid=db.getUserID(uname);
        Cursor userD=db.getUserDetails(uname);
        Cursor cardD=db.getCardDetails(userid);
//        Toast.makeText(getApplicationContext(), "UserId"+userid , Toast.LENGTH_SHORT).show();
        role1.setText(role);
        if(role.equals("other")){
            cardtype.setVisibility(View.VISIBLE);
            cardno.setVisibility(View.VISIBLE);
            cvv.setVisibility(View.VISIBLE);
            expdate.setVisibility(View.VISIBLE);
            role1.setText("Student/Staff");
        }
        username.setText(uname);

        //username.setFocusable(false);

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

                fname.setEnabled(false);
                lname.setEnabled(false);
                street_add.setEnabled(false);
                city.setEnabled(false);
                state.setEnabled(false);
                zipcode.setEnabled(false);
                email.setEnabled(false);
                phone.setEnabled(false);
                username.setEnabled(false);
                cardno.setEnabled(false);
                cardtype.setEnabled(false);
                cvv.setEnabled(false);
                expdate.setEnabled(false);

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


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_edit.setVisibility(View.INVISIBLE);

                fname.setEnabled(true);
                lname.setEnabled(true);
                street_add.setEnabled(true);
                city.setEnabled(true);
                state.setEnabled(true);
                zipcode.setEnabled(true);
                email.setEnabled(true);
                phone.setEnabled(true);
               // username.setEnabled(true);
                cardno.setEnabled(true);
                //cardtype.setEnabled(true);
                cvv.setEnabled(true);
                expdate.setEnabled(true);

                btn_update.setVisibility(View.VISIBLE);
                if(role.equals("other")){
                    rg.setVisibility(View.VISIBLE);
                    cardtype.setVisibility(View.INVISIBLE);
                }


            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(role.equals("other")){
                    int selectedId = rg.getCheckedRadioButtonId();
                    //Toast.makeText(getApplicationContext(), ""+selectedId, Toast.LENGTH_SHORT).show();
                    // System.out.println(selectedId);
                    rb=(RadioButton)findViewById(selectedId);
                    String cardType=rb.getText().toString();

                    db.updateCardDetails(userid,cardno.getText().toString(),cvv.getText().toString(),cardType,expdate.getText().toString());

                }

                if (db.updateProfile(userid,fname.getText().toString(),lname.getText().toString(),username.getText().toString(),email.getText().toString(),phone.getText().toString(),street_add.getText().toString(),city.getText().toString(),state.getText().toString(),zipcode.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                    if(role.equals("operator")){
                        Intent intent = new Intent(UpdateProfileActivity.this, UpdateProfileActivity.class);
                        startActivity(intent);
                    }
                    else if(role.equals("manager")){
                        Intent intent = new Intent(UpdateProfileActivity.this, UpdateProfileActivity.class);
                        startActivity(intent);

                    }
                    else{
                        Intent intent = new Intent(UpdateProfileActivity.this, UpdateProfileActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });




    }
}
