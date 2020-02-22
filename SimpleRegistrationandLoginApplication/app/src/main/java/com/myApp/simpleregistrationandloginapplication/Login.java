//package com.myApp.simpleregistrationandloginapplication;
package com.razormist.simpleregistrationandloginapplication;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.Cursor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;


public class Login extends AppCompatActivity {
    Button btn_lregister, btn_llogin;
    EditText et_lusername, et_lpassword;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        et_lusername = (EditText)findViewById(R.id.et_lusername);
        et_lpassword = (EditText)findViewById(R.id.et_lpassword);

        btn_llogin = (Button)findViewById(R.id.btn_llogin);
        btn_lregister = (Button)findViewById(R.id.btn_lregister);

        btn_lregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
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
                    Cursor role=databaseHelper.CheckRole(username,password);
                    //String r=role.getString(0);
                    if(role.moveToNext()) {
                        if (role.getString(0).equals("Student/Staff")) {
                            Intent intent = new Intent(Login.this, UserHomeActivity.class);
                            startActivity(intent);
                        } else if (role.getString(0).equals("Admin")) {
                            Intent intent = new Intent(Login.this, ManagerHomeActivity.class);
                            startActivity(intent);
                        } else if (role.getString(0).equals("Operator")) {
                            Intent intent = new Intent(Login.this, OperatorHomeActivity.class);
                            startActivity(intent);
                        }
                    }
                    //System.out.print(role.toString());
                    Toast.makeText(getApplicationContext(), "Login Successfull"+role.getString(0) , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}