package com.squirrel.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;


public class ForgotPasswordActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText et_ncpassword,et_npassword;
    Button btn_changepass, back;

    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        getSupportActionBar().hide();

        databaseHelper = new DatabaseHelper(this);
        et_ncpassword = (EditText)findViewById(R.id.et_ncpassword);
        et_npassword = (EditText)findViewById(R.id.et_npassword);
        btn_changepass = (Button)findViewById(R.id.btn_changepass);
        back=(Button)findViewById(R.id.button2);

        btn_changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ncpassword = et_ncpassword.getText().toString();
                String npassword = et_npassword.getText().toString();
                if(npassword.equals("") || ncpassword.equals("")){
                    //Toast.makeText(getApplicationContext(), role, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }else {
                    if(npassword.equals(ncpassword)){
                        Intent thisIntent = getIntent();
                        String userid = thisIntent.getStringExtra("userid");
                        Boolean insert = databaseHelper.changePassword(userid,npassword);

                        if(insert == true){
                            Toast.makeText(getApplicationContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to change password", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}