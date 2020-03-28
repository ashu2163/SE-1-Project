package com.squirrel.application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.RadioGroup;
import android.widget.RadioButton;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import com.squirrel.models.User;


public class RegistrationActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText et_username, et_password, et_cpassword,et_fname,et_lname,et_email,et_address,et_city,et_state,et_zipcode,et_phone;
    Button btn_register, btn_login;

    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        databaseHelper = new DatabaseHelper(this);
        et_fname = (EditText)findViewById(R.id.et_fname);
        et_lname = (EditText)findViewById(R.id.et_lname);
        et_username = (EditText)findViewById(R.id.et_lusername);
        et_password = (EditText)findViewById(R.id.et_lpassword);
        et_cpassword = (EditText)findViewById(R.id.et_cpassword);
        et_email = (EditText)findViewById(R.id.et_email);
        et_address = (EditText)findViewById(R.id.et_address);
        et_city = (EditText)findViewById(R.id.et_city);
        et_state = (EditText)findViewById(R.id.et_state);
        et_zipcode = (EditText)findViewById(R.id.et_zipcode);
        et_phone = (EditText)findViewById(R.id.et_phone);
        rg = (RadioGroup) findViewById(R.id.et_rgroup);


        btn_register = (Button)findViewById(R.id.btn_register);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rg.getCheckedRadioButtonId();
                rb=(RadioButton)findViewById(selectedId);
                String role=rb.getText().toString();

                String fname = et_fname.getText().toString();
                String lname = et_lname.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String confirm_password = et_cpassword.getText().toString();
                String email = et_email.getText().toString();
                String address = et_address.getText().toString();
                String city = et_city.getText().toString();
                String state = et_state.getText().toString();
                int zipcode = et_zipcode.getInputType();
                String phone = et_phone.getText().toString();

                if(username.equals("") || password.equals("") || confirm_password.equals("")){
                    //Toast.makeText(getApplicationContext(), role, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(getApplicationContext(), role, Toast.LENGTH_SHORT).show();
                    if(password.equals(confirm_password)){
                        User temp = databaseHelper.getUser(email);
                        Boolean userexists=false;
                        if(temp==null){
                            userexists = false;
                        } else {
                            userexists = temp.verifyUsername(username);
                        }
                        if(userexists == false){
                            User u = new User(0,fname,lname,username,password,role,email,phone,address,city,state,zipcode);
                            Boolean insert = databaseHelper.Insert(u);

                            if(insert == true){

                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                                et_fname.setText("");
                                et_lname.setText("");
                                et_username.setText("");
                                et_password.setText("");
                                et_cpassword.setText("");
                                et_email.setText("");
                                et_address.setText("");
                                et_city.setText("");
                                et_state.setText("");
                                et_zipcode.setText("");
                                et_phone.setText("");
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}