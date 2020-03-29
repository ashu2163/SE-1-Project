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


public class ConfirmEmailActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText et_cemail;
    Button btn_cemail,back;

    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_email);
        getSupportActionBar().hide();

        databaseHelper = new DatabaseHelper(this);
        et_cemail = (EditText)findViewById(R.id.et_cemail);
        btn_cemail = (Button)findViewById(R.id.btn_cemail);
        back=(Button)findViewById(R.id.back);

        btn_cemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cemail = et_cemail.getText().toString();
                if(cemail.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields Required", Toast.LENGTH_SHORT).show();
                }else{
                    String userid = databaseHelper.CheckCemail(cemail);
                    if(userid.equals("")){
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ConfirmEmailActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "VALID Email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ConfirmEmailActivity.this, ForgotPasswordActivity.class);
                        intent.putExtra("userid",userid);
                        startActivity(intent);
                    }
                }


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmEmailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}