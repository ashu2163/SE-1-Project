package com.myApp.simpleregistrationandloginapplication;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Random;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Squirrel.db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT,lname TEXT, username TEXT, password TEXT,email TEXT, address TEXT, role TEXT)");
        Log.d("","HELLO CREATING TABLES");
        db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS user(\n" +
                "userid int PRIMARY KEY,\n" +
                "fname varchar(20),\n" +
                "lname varchar(20),\n" +
                "uname varchar(20) NOT NULL,\n" +
                "password varchar(40) NOT NULL,\n" +
                "role varchar(10) NOT NULL DEFAULT 'other',\n" +
                "email varchar(40),\n" +
                "phone varchar(15),\n" +
                "street_address varchar(100),\n" +
                "city varchar(15),\n" +
                "state varchar(15),\n" +
                "zipcode int\n" +
                ")" +
                "");
        Log.d("","HELLO INSERTING RECORDS");

        db.execSQL("" +
                "INSERT INTO user(userid,fname,lname,uname,password,role,email,phone,street_address,city,state,zipcode) VALUES\n" +
                "(1001000,'Margaret','Jones','abc','abc','other','MargaretTJones@uta.mavs.edu','9725878858','2959 Worthington Drive','Dallas','TX',75244),\n" +
                "(1002000,'Donna','Batson','donnagbatson','ubS$_,vxL/hJ<7j','other','DonnaGBatson@uta.mavs.edu','3016851343','1556  Wilson Avenue','Carrollton','TX',75244),\n" +
                "(1003000,'Sarah','Maxie','sarahwmaxie','wgS2jm\"+Ah=2fd{','other','SarahWMaxie@uta.mavs.edu','9048456024','1880 Ryan Road','Deadwood','SD',57732),\n" +
                "(1004000,'Edward','Tarleton','edwardhtarleton','9TywUg<v_Y','manager','EdwardHTarleton@uta.edu','7084245246','2851 Beeghley Street','Temple','TX',76501),\n" +
                "(1005000,'Heather','Peterson','heatherapeterson','$y6.jZXe\"?','other','HeatherAPeterson@uta.mavs.edu','7084540101','3660 Joy Lane','Calabasas','CA',91302),\n" +
                "(1006000,'Thomas','Murphy','thomaslmurphy','uS@*,Vz{9>','other','ThomasLMurphy@uta.mavs.edu','5088263769','1579 Woodbridge Lane','Detroit','MI',48202),\n" +
                "(1007000,'Gary','Watson','garyewatson','*_5aa%GGUGt_R}:','operator','GaryEWatson@uta.edu','5617391409','3444 Maloy Court','Salina','KS',67401),\n" +
                "(1008000,'Gene','Bookman','genembookman','2V/J36=8+b~wA$$','other','GeneMBookman@uta.mavs.edu','9704280507','3291 Coplin Avenue','Phoenix','AZ',85003),\n" +
                "(1009000,'Eleanor','Peoples','eleanorgpeoples','&zr=#9%hUW9S[KC','operator','EleanorGPeoples@uta.edu','6067911291','4768 Horizon Circle','Federal Way','WA',98003),\n" +
                "(1001001,'James','West','jamesswest','{=uux8^qZtrbL\\q','other','JamesSWest@uta.mavs.edu','2704997441','1213 Chicago Avenue','Fresno','CA',93721),\n" +
                "(1001100,'Helen','Baker','helenabaker','bQT%cZe$8P8mTW8','other','HelenABaker@uta.mavs.edu','2037962739','3446 Michigan Avenue','Bridgeville','PA',15017),\n" +
                "(1001200,'George','Sisk','georgecsisk','vf*p!22JqxPCT5','other','GeorgeCSisk@uta.mavs.edu','2034240092','2460 Stratford Park','Bloomington','IN',47408)" +
                "");
        Log.d("","DONE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);

        /*if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE user ADD COLUMN role TEXT");
        }*/
    }

    public boolean Insert(String fname,String lname,String uname, String password,String email, String address, String role){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        contentValues.put("uname", uname);
        contentValues.put("password", password);
        contentValues.put("email", email);
        Random rand = new Random();
        int num = rand.nextInt(9000000) + 1000000;
        contentValues.put("userid",num);
        //contentValues.put("address", address);
        if(role.equals("Student/Staff"))
            role="other";
        contentValues.put("role", role.toLowerCase());

        long result = sqLiteDatabase.insert("user", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean changePassword(String userid,String npassword){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", npassword);
        long result = sqLiteDatabase.update("user", contentValues,"userid=?",new String[]{userid});
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean CheckUsername(String uname){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE uname=?", new String[]{uname});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }
    }

    public String CheckCemail(String cemail){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=?", new String[]{cemail});
        if(cursor.getCount() > 0){
            if(cursor.moveToNext()) {
                if (cursor.getString(6).equals(cemail)) {
                    return cursor.getString(0);
                }
            }
        } else {
            return "";
        }
        return "";
    }

    public Boolean CheckLogin(String uname, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE uname=? AND password=?", new String[]{uname, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }

        /*Cursor cursor = sqLiteDatabase.rawQuery("SELECT role FROM user WHERE username=? AND password=?", new String[]{username, password});
        if(cursor.getCount() > 0){
            return cursor.getString(0);
        }else{
            return false;
        }*/
    }

    public Cursor CheckRole(String uname, String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT role FROM user WHERE uname=? AND password=?", new String[]{uname, password});
        if (cursor.getCount() > 0) {
                return cursor;
//            } else {
//                return "";
//            }
            }
            return cursor;


    }

}