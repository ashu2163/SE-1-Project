package com.squirrel.dao;
import android.content.Intent;
import android.database.SQLException;
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

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
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
                "(1001000,'Margaret','Jones','abcs','abcs','other','MargaretTJones@uta.mavs.edu','9725878858','2959 Worthington Drive','Dallas','TX',75244),\n" +
                "(1002000,'Donna','Batson','donnagbatson','ubS$_,vxL/hJ<7j','other','DonnaGBatson@uta.mavs.edu','3016851343','1556  Wilson Avenue','Carrollton','TX',75244),\n" +
                "(1003000,'Sarah','Maxie','sarahwmaxie','wgS2jm\"+Ah=2fd{','other','SarahWMaxie@uta.mavs.edu','9048456024','1880 Ryan Road','Deadwood','SD',57732),\n" +
                "(1004000,'Edward','Tarleton','abcm','abcm','manager','EdwardHTarleton@uta.edu','7084245246','2851 Beeghley Street','Temple','TX',76501),\n" +
                "(1005000,'Heather','Peterson','heatherapeterson','$y6.jZXe\"?','other','HeatherAPeterson@uta.mavs.edu','7084540101','3660 Joy Lane','Calabasas','CA',91302),\n" +
                "(1006000,'Thomas','Murphy','thomaslmurphy','uS@*,Vz{9>','other','ThomasLMurphy@uta.mavs.edu','5088263769','1579 Woodbridge Lane','Detroit','MI',48202),\n" +
                "(1007000,'Gary','Watson','abco','abco','operator','GaryEWatson@uta.edu','5617391409','3444 Maloy Court','Salina','KS',67401),\n" +
                "(1008000,'Gene','Bookman','genembookman','2V/J36=8+b~wA$$','other','GeneMBookman@uta.mavs.edu','9704280507','3291 Coplin Avenue','Phoenix','AZ',85003),\n" +
                "(1009000,'Eleanor','Peoples','eleanorgpeoples','&zr=#9%hUW9S[KC','operator','EleanorGPeoples@uta.edu','6067911291','4768 Horizon Circle','Federal Way','WA',98003),\n" +
                "(1001001,'James','West','jamesswest','{=uux8^qZtrbL\\q','other','JamesSWest@uta.mavs.edu','2704997441','1213 Chicago Avenue','Fresno','CA',93721),\n" +
                "(1001100,'Helen','Baker','helenabaker','bQT%cZe$8P8mTW8','other','HelenABaker@uta.mavs.edu','2037962739','3446 Michigan Avenue','Bridgeville','PA',15017),\n" +
                "(1001200,'George','Sisk','georgecsisk','vf*p!22JqxPCT5','other','GeorgeCSisk@uta.mavs.edu','2034240092','2460 Stratford Park','Bloomington','IN',47408)" +
                "");

        db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS location(\n" +
                "locid varchar(10) PRIMARY KEY,\n" +
                "locname varchar(20) NOT NULL,\n" +
                "duration int(1) NOT NULL\n" +
                ")" +
                "");

        db.execSQL("" +
                "INSERT INTO location(locid,locname,duration) VALUES\n" +
                "('Loc_1','Cooper & UTA Blvd',2),\n" +
                "('Loc_2','W Nedderman & Greek Row',1),\n" +
                "('Loc_3','S Davis & W Mitchell',2),\n" +
                "('Loc_4','Cooper & W Mitchell',3),\n" +
                "('Loc_5','S Oak & UTA Blvd',2),\n" +
                "('Loc_6','Spaniolo & W 1st',4),\n" +
                "('Loc_7','Spaniolo & W Mitchell',2),\n" +
                "('Loc_8','S Center & W Mitchell',1)" +
                "");

        db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS item(\n" +
                "itemid int PRIMARY KEY,\n" +
                "itemtype varchar(20) NOT NULL,\n" +
                "cost decimal(6,2) NOT NULL\n" +
                ")" +
                "");

        db.execSQL("" +
                "INSERT INTO item(itemid,itemtype,cost) VALUES\n" +
                "(81,'Drinks',1.50),\n" +
                "(82,'Sandwiches',5.75),\n" +
                "(83,'Snacks',1.25)" +
                "");

        db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS vehicle(\n" +
                "vehid int PRIMARY KEY,\n" +
                "vehname varchar(20) NOT NULL,\n" +
                "vehtype varchar(10) NOT NULL\n" +
                ")" +
                "");

        db.execSQL("" +
                "INSERT INTO vehicle(vehid,vehname, vehtype) VALUES\n" +
                "(51,'foodtruck1', 'truck'),\n" +
                "(52,'foodtruck2','truck'),\n" +
                "(53,'stationcart1','cart'),\n" +
                "(54,'stationcart2','cart'),\n" +
                "(55,'stationcart3','cart'),\n" +
                "(56,'stationcart4','cart'),\n" +
                "(57,'stationcart5','cart')" +
                "");

        db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS vehicle_inventory(\n" +
                "vehid int,\n" +
                "itemid int,\n" +
                "quantity int,\n" +
                "available_date date,\n" +
                "PRIMARY KEY (vehid,itemid,available_date),\n" +
                "FOREIGN KEY (vehid) REFERENCES vehicle(vehid),\n" +
                "FOREIGN KEY (itemid) REFERENCES item(itemid)\n" +
                ")" +
                "");

        db.execSQL("" +
                "INSERT INTO vehicle_inventory(vehid,itemid,quantity,available_date) VALUES\n" +
                "(51,81,50,date('now')),\n" +
                "(51,81,50,date('now','+1 day')),\n" +
                "(51,82,35,date('now')),\n" +
                "(51,82,35,date('now','+1 day')),\n" +
                "(51,83,40,date('now')),\n" +
                "(51,83,40,date('now','+1 day')),\n" +
                "\n" +
                "(52,81,50,date('now')),\n" +
                "(52,82,35,date('now')),\n" +
                "(52,83,40,date('now')),\n" +
                "\n" +
                "(53,81,30,date('now')),\n" +
                "(53,81,30,date('now','+1 day')),\n" +
                "(53,82,5,date('now')),\n" +
                "(53,82,5,date('now','+1 day')),\n" +
                "(53,83,30,date('now')),\n" +
                "(53,83,30,date('now','+1 day')),\n" +
                "\n" +
                "(54,81,30,date('now')),\n" +
                "(54,81,30,date('now','+1 day')),\n" +
                "(54,82,5,date('now')),\n" +
                "(54,82,5,date('now','+1 day')),\n" +
                "(54,83,30,date('now')),\n" +
                "(54,83,30,date('now','+1 day')),\n" +
                "\n" +
                "(55,81,30,date('now')),\n" +
                "(55,81,30,date('now','+1 day')),\n" +
                "(55,82,5,date('now')),\n" +
                "(55,82,5,date('now','+1 day')),\n" +
                "(55,83,30,date('now')),\n" +
                "(55,83,30,date('now','+1 day')),\n" +
                "\n" +
                "(56,81,30,date('now')),\n" +
                "(56,82,5,date('now')),\n" +
                "(56,83,30,date('now')),\n" +
                "\n" +
                "(57,81,30,date('now')),\n" +
                "(57,81,30,date('now','+1 day')),\n" +
                "(57,82,5,date('now')),\n" +
                "(57,82,5,date('now','+1 day')),\n" +
                "(57,83,30,date('now')),\n" +
                "(57,83,30,date('now','+1 day'))" +
                "");

        db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS vehicle_schedule(\n" +
                "vehid int,\n" +
                "locid varchar(10),\n" +
                "opid int,\n" +
                "slotbegin int,\n" +
                "slotend int,\n" +
                "scheduled_date date,\n" +
                "PRIMARY KEY (vehid,slotbegin,slotend,scheduled_date),\n" +
                "FOREIGN KEY (vehid) REFERENCES vehicle(vehid),\n" +
                "FOREIGN KEY (locid) REFERENCES location(locid),\n" +
                "FOREIGN KEY (opid) REFERENCES user(userid)\n" +
                ")" +
                "");

        db.execSQL("" +
                "INSERT INTO vehicle_schedule(vehid,locid,opid,slotbegin,slotend,scheduled_date) VALUES\n" +
                "(51,'Loc_1',1007000,8,10,date('now')),\n" +
                "(51,'Loc_2',1009000,10,11,date('now')),\n" +
                "(51,'Loc_3',1007000,11,13,date('now')),\n" +
                "(53,'Loc_4',1009000,8,11,date('now')),\n" +
                "(53,'Loc_4',1009000,11,14,date('now')),\n" +
                "(53,'Loc_4',1009000,14,17,date('now')),\n" +
                "(57,'Loc_7',1007000,15,17,date('now')),\n" +
                "(51,'Loc_1',1007000,8,10,date('now','+1 day')),\n" +
                "(51,'Loc_2',1009000,10,11,date('now','+1 day')),\n" +
                "(51,'Loc_3',1007000,11,13,date('now','+1 day')),\n" +
                "(53,'Loc_4',1009000,8,11,date('now','+1 day')),\n" +
                "(53,'Loc_4',1009000,11,14,date('now','+1 day')),\n" +
                "(53,'Loc_4',1009000,14,17,date('now','+1 day')),\n" +
                "(57,'Loc_7',1007000,15,17,date('now','+1 day'))" +
                "");

        db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS cart(\n" +
                "userid int,\n" +
                "itemid int,\n" +
                "buy_quantity int,\n" +
                "PRIMARY KEY (userid,itemid),\n" +
                "FOREIGN KEY (userid) REFERENCES user(userid),\n" +
                "FOREIGN KEY (itemid) REFERENCES item(itemid)\n" +
                ")" +
                "");

        db.execSQL("" +
                "INSERT INTO cart(userid,itemid,buy_quantity) VALUES\n" +
                "(1003000,81,1),\n" +
                "(1001001,82,10),\n" +
                "(1003000,83,2)" +
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

    public boolean Insert(String fname,String lname,String uname, String password,String email, String address,
                          String role,String city, String state, int zipcode, String phone){
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
        contentValues.put("city", city);
        contentValues.put("street_address", address);
        contentValues.put("state", state);
        contentValues.put("zipcode", zipcode);
        contentValues.put("phone", phone);
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


    public Cursor getVehicleSchedule(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM vehicle_schedule where scheduled_date=date('now','+1 day')",new String[]{});
        return cursor;
    }

    public Cursor getAvailableVehcilesForNextDay(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT distinct(vi.vehid),v.vehname FROM vehicle_inventory vi LEFT JOIN vehicle v on vi.vehid=v.vehid WHERE available_date=date('now','+1 day')",new String[]{});
        return cursor;
    }

    public Cursor getLocations(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM location",new String[]{});
        return cursor;
    }

    public Cursor getOperators(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE role='operator'",new String[]{});
        return cursor;
    }

    public Cursor getMinMaxSlots(String vehid){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select min(slotbegin) as minslotbegin,max(slotend) as maxslotend from vehicle_schedule where vehid=? and scheduled_date=date('now','+1 day')",new String[]{vehid});
        return cursor;
    }

    public boolean insertVehicleSchedule(int vehid,String locid,int opid, int slotbegin,int slotend){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vehid", vehid);
        contentValues.put("locid", locid);
        contentValues.put("opid", opid);
        contentValues.put("slotbegin", slotbegin);
        contentValues.put("slotend", slotend);
        contentValues.put("scheduled_date", LocalDate.now(ZoneId.of("UTC")).plusDays(1).toString());

        long result = sqLiteDatabase.insert("vehicle_schedule", null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getVehicleName(String vehtype){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select distinct v.vehname,vs.slotbegin,vs.slotend,l.locname " +
                "from vehicle as v \n" +
                "left join vehicle_schedule as vs on v.vehid=vs.vehid \n" +
                "left join location as l on vs.locid=l.locid\n" +
                "where v.vehtype=? and vs.scheduled_date=date('now')" +
                "\n",new String[]{vehtype});
        return cursor;
    }

    public Cursor getSelectedVehicleInventory(String vehname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select distinct c.itemtype,a.quantity from vehicle_inventory as a join vehicle as b on a.vehid=b.vehid\n" +
                "join item as c on c.itemid= a.itemid\n" +
                "where b.vehname=?", new String[]{vehname});

        return cursor;
    }

    public int getUserId(String username){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select userid from user where uname=?", new String[]{username});

        if(cursor.getCount() > 0) {
            if (cursor.moveToNext()) {
                return cursor.getInt(0);
            }
        }
        return -1;
    }

    private static Context mContext;
    public boolean insertToCart(int userId, int itemId , int quantity){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        long result=0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("userid", userId);
        contentValues.put("itemid", itemId);
        contentValues.put("buy_quantity",quantity);
        Cursor cursor=sqLiteDatabase.rawQuery("select userid,itemid from cart where userid=?", new String[]{String.valueOf(userId)});
        if(cursor.getCount()>0){
            while (cursor.moveToNext()) {
                if(cursor.getInt(1)==itemId){
                    return false;
                }
            }
        }
        if(sqLiteDatabase.insert("cart", null, contentValues)!=-1){
            return true;
        }
        else{
            return false;
        }

    }


    public Cursor getCartDetails(int userId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select * from cart where userid=?", new String[]{String.valueOf(userId)});
        return cursor;
    }

    public float getCost(int itemId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("select cost from item where itemid=?", new String[]{String.valueOf(itemId)});
        if(cursor.getCount()>0){
            if(cursor.moveToNext()){
                return cursor.getFloat(0);
            }
        }
        return -1;
    }
}