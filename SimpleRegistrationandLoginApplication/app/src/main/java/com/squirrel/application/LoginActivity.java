package com.squirrel.application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;


public class LoginActivity extends AppCompatActivity {
    Button btn_lregister, btn_llogin, btn_fpassword;
    EditText et_lusername, et_lpassword;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        databaseHelper = new DatabaseHelper(this);

        et_lusername = (EditText)findViewById(R.id.et_lusername);
        et_lpassword = (EditText)findViewById(R.id.et_lpassword);

        btn_llogin = (Button)findViewById(R.id.btn_llogin);
        btn_lregister = (Button)findViewById(R.id.btn_lregister);
        btn_fpassword = (Button)findViewById(R.id.btn_fpassword);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btn_lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btn_llogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_lusername.getText().toString();
                String password = et_lpassword.getText().toString();

                Boolean checklogin = databaseHelper.CheckLogin(username, password);
                if(checklogin == true){
                    Cursor role=databaseHelper.CheckRole(username, password);
                    //String r=role.getString(0);
                    if(role.moveToNext()) {
                        if (role.getString(0).equals("other")) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("username",username );
                            editor.putString("password", password);
                            editor.putString("role",role.getString(0) );
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, UserHomeActivity.class);
                            startActivity(intent);
                        } else if (role.getString(0).equals("manager")) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("username",username );
                            editor.putString("password", password);
                            editor.putString("role",role.getString(0) );
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, ManagerHomeActivity.class);
                            startActivity(intent);
                        } else if (role.getString(0).equals("operator")) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("username",username );
                            editor.putString("password", password);
                            editor.putString("role",role.getString(0) );
                            editor.commit();
                            //Toast.makeText(getApplicationContext(), "LoginActivity" , Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, OperatorHomeActivity.class);
                            startActivity(intent);
                        }
                    }
                    //System.out.print(role.toString());
                    Toast.makeText(getApplicationContext(), "LoginActivity Successfull"+role.getString(0) , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_fpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ConfirmEmailActivity.class);
                startActivity(intent);
            }
        });
    }

}