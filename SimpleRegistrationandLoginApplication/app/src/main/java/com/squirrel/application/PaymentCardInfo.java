package com.squirrel.application;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squirrel.app.R;
import com.squirrel.dao.DatabaseHelper;
import com.squirrel.models.PaymentsOptions;

import java.util.Random;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class PaymentCardInfo extends AppCompatActivity {


    DatabaseHelper db;
    PaymentsOptions p;
    BottomNavigationView bottomNavigationView;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    Button btn_place_order;
    Button btn_modify;
    Button btn_logout;
    EditText cvv,cardType,cardno,exp_date;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_info);

        cvv=(EditText)findViewById(R.id.cvv);
        cardno=(EditText)findViewById(R.id.cardno);
        cardType=(EditText)findViewById(R.id.cardType);
        exp_date=(EditText)findViewById(R.id.exp_date);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_place_order = (Button) findViewById(R.id.placeorder);
        btn_modify = (Button) findViewById(R.id.btn_modify);
        error=(TextView)findViewById(R.id.error);

        SharedPreferences pref = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        final String username=pref.getString("username",null);

        db = new DatabaseHelper(this);
        final int userId = db.getUserId(username);

        Intent intent = getIntent();
        final Float tcost = intent.getFloatExtra("tcost",0.0f);


        p=db.getPaymentCardInfo(userId);
        if(p==null){
           error.setVisibility(VISIBLE);
           error.setText("No Card Information available. Please click on ADD/MODIFY BUTTON to add card details");
        }
        else {
            cardno.setText(p.getCc());
            cardType.setText(p.getCardtype());
            //cvv.setText(p.getCvv());
            exp_date.setText(p.getExpiry());
            cardno.setVisibility(VISIBLE);
            cardType.setVisibility(VISIBLE);
            exp_date.setVisibility(VISIBLE);
            cvv.setVisibility(VISIBLE);
            error.setVisibility(INVISIBLE);
        }
        //Toast.makeText(getApplicationContext(), "usename:"+username+"user id"+userId, Toast.LENGTH_SHORT).show();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Intent intent1 = new Intent(PaymentCardInfo.this, CartDetailsActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_profile:
                        Intent intent2 = new Intent(PaymentCardInfo.this, UpdateProfileActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_home:
                        Intent intent3 = new Intent(PaymentCardInfo.this, UserHomeActivity.class);
                        startActivity(intent3);
                        break;
                }

                return false;
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentCardInfo.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        btn_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( cvv.getText().toString().trim().equals("") ||cardno.getText().toString().trim().equals("") ||cardType.getText().toString().trim().equals("")||exp_date.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if(!db.verifyPaymentDetails(userId,cardno.getText().toString(),cvv.getText().toString(),exp_date.getText().toString(),cardType.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Please input correct card details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Random r = new Random();
                    int randomNumber = r.nextInt(10000000);
                    String orderid=Integer.toString(randomNumber);
                    Intent intent = new Intent(PaymentCardInfo.this, Conformation.class);
                    intent.putExtra("order_id",orderid);
                    intent.putExtra("tcost",tcost);
                    startActivity(intent);
                }
            }
        });



        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentCardInfo.this, UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        //Toast.makeText(getApplicationContext(), "Conformation Number will be given after place order on third iteration", Toast.LENGTH_SHORT).show();
    }
}