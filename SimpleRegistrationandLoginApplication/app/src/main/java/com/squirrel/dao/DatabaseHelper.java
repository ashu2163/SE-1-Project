package com.squirrel.dao;
import android.util.Log;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squirrel.models.Location;
import com.squirrel.models.PaymentsOptions;
import com.squirrel.models.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
                "(1001300,'Aary','Batson','abco2','abco2','operator','BaryEWatson@uta.edu','4617391409','4444 Maloy Court','Malina','VS',77401),\n" +
                "(1008000,'Gene','Bookman','genembookman','2V/J36=8+b~wA$$','other','GeneMBookman@uta.mavs.edu','9704280507','3291 Coplin Avenue','Phoenix','AZ',85003),\n" +
                "(1009000,'Eleanor','Peoples','abco1','abco1','operator','EleanorGPeoples@uta.edu','6067911291','4768 Horizon Circle','Federal Way','WA',98003),\n" +
                "(1001001,'James','West','jamesswest','{=uux8^qZtrbL\\q','other','JamesSWest@uta.mavs.edu','2704997441','1213 Chicago Avenue','Fresno','CA',93721),\n" +
                "(1001100,'Helen','Baker','helenabaker','bQT%cZe$8P8mTW8','other','HelenABaker@uta.mavs.edu','2037962739','3446 Michigan Avenue','Bridgeville','PA',15017),\n" +
                "(2001100,'Susan','Queen','SuzieQ','Password$34','operator','SuzieQ@mavs.uta.edu','817-777-2345','5678 Straight Dr','Arlington','Tx',76019),\n" +
                "(2002200,'Sam','Johnson','SammyJ','Password12$','manager','sammyJ@mavs.uta.edu','817-777-2000','1234 Anywhere Ln','Arlington','Tx',76019),\n" +
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
                "(54,'Loc_3',1001300,8,17,date('now')),\n" +
                "(51,'Loc_2',1007000,11,13,date('now')),\n" +
                "(53,'Loc_1',2001100,11,13,date('now')),\n" +
                "(57,'Loc_1',1007000,8,10,date('now','+1 day')),\n" +
                "(53,'Loc_2',1009000,8,17,date('now','+1 day')),\n" +
                "(57,'Loc_3',1007000,11,13,date('now','+1 day')),\n" +
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

        db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS paymentoptions(\n" +
                "userid int,\n" +
                "cc varchar(16),\n" +
                "expiry varchar(6),\n" +
                "cvv varchar(3),\n" +
                "cardtype varchar(3),\n" +
                "PRIMARY KEY (cc),\n" +
                "FOREIGN KEY (userid) REFERENCES user(userid)\n" +
                ")" +
                "");

        db.execSQL("" +
                "INSERT INTO paymentoptions(userid,cc,expiry,cvv,cardtype) VALUES\n" +
                "(1001000,'1234123445674567','052024','123','Visa'),\n" +
                "(1003000,'4567456712341234','052025','456','Master')" +
                "");

        db.execSQL("" +
                "CREATE TABLE IF NOT EXISTS payments(\n" +
                "payid int,\n" +
                "userid int,\n" +
                "vehid int,\n" +
                "opid int,\n" +
                "payment_date date,\n" +
                "total_cost decimal(10,2) NOT NULL,\n" +
                "PRIMARY KEY (payid),\n" +
                "FOREIGN KEY (userid) REFERENCES user(userid),\n" +
                "FOREIGN KEY (opid) REFERENCES user(userid),\n" +
                "FOREIGN KEY (vehid) REFERENCES vehicle(vehid)\n" +
                ")" +
                "");

        db.execSQL("" +
                "INSERT INTO payments(payid,userid,opid,vehid,payment_date,total_cost) VALUES\n" +
                "(9901000,1001000,1007000,51,date('now'),1523.26),\n" +
                "(9902000,1001000,2001100,53,date('now'),17),\n" +
                "(9905000,1001000,1007000,51,date('now'),1222.50),\n" +
                "(9907000,1001000,1007000,57,date('now','-1 day'),1750.80)" +
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

    public boolean Insert(User u){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", u.getFname());
        contentValues.put("lname", u.getLname());
        contentValues.put("uname", u.getUname());
        contentValues.put("password", u.getPassword());
        contentValues.put("email", u.getEmail());
        Random rand = new Random();
        int num = rand.nextInt(9000000) + 1000000;
        contentValues.put("userid",num);
        contentValues.put("city", u.getCity());
        contentValues.put("street_address", u.getStreet_address());
        contentValues.put("state", u.getState());
        contentValues.put("zipcode", u.getZipcode());
        contentValues.put("phone", u.getPhone());
        if(u.getRole().contains("Student"))
            u.setRole("other");
        contentValues.put("role", u.getRole().toLowerCase());

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
                "where b.vehname=? and available_date=date ('now')", new String[]{vehname});

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

    public int getVehId(String uname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("select distinct vehicle_schedule.vehid \n" +
                "from user, vehicle_schedule\n" +
                "where user.userid = vehicle_schedule.opid\n" +
                "and vehicle_schedule.scheduled_date= date ('now')\n" +
                "and user.uname = ?", new String[]{uname});
        if(c.getCount()>0){
            if(c.moveToNext()){
                return c.getInt(0);
            }
        }
        return -1;
    }
    public int getVehIdManager(String vehname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("select vehid from vehicle where vehname=?", new String[]{vehname});
        if(c.getCount()>0){
            if(c.moveToNext()){
                return c.getInt(0);
            }
        }
        return -1;
    }
    public float getItemQuantity(int vehId, int itemId){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("select quantity from vehicle_inventory where vehid=? and itemid=?",
                new String[]{String.valueOf(vehId), String.valueOf(itemId)});

        if(c.getCount()>0){
            if(c.moveToNext()){
                return c.getInt(0);
            }
        }
        return -1;
    }

    public boolean findItemInCart(int userId, int itemId)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c= sqLiteDatabase.rawQuery("select * from cart where userid=? and itemid=?",
                new String[]{String.valueOf(userId), String.valueOf(itemId)});

        if(c.getCount()>0){
            return true;
        }
        return false;
    }

    public boolean updateCart(int userId, int itemId, float qua){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("buy_quantity",qua);
        cv.put("userid",userId);
        cv.put("itemid",itemId);
        //long r=sqLiteDatabase.replace("cart",null,cv);
        long r=sqLiteDatabase.update("cart",cv, "userid=? and  itemid=?",new String[]{String.valueOf(userId), String.valueOf(itemId)});

        if(r == -1){
            return false;
        }else{
            return true;
        }

    }
    public int getUserID(String uname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select userid from user where uname= ?",new String[]{uname});

        if(cursor.getCount()>0){
            if(cursor.moveToNext()){
                return cursor.getInt(0);
            }
        }
        return -1;

    }


    public Cursor getUserDetails(String uname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user where uname= ?",new String[]{uname});
        return cursor;
    }

    public Cursor getOperatorVehicle(String uname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select vehicle.vehname,vehicle.vehtype,location.locname,vehicle_schedule.slotbegin,vehicle_schedule.slotend,user.fname,user.lname\n" +
                "from vehicle,location,vehicle_schedule,user where vehicle.vehid=vehicle_schedule.vehid and location.locid=vehicle_schedule.locid and\n" +
                "user.userid=vehicle_schedule.opid and vehicle_schedule.scheduled_date=date('now') and user.uname=?",new String[]{uname});
        return cursor;
    }


    public Cursor getRevenueOperator(String uname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(" select payments.total_cost from user,payments where payments.opid=user.userid and payments.payment_date=date('now') and user.uname=?", new String[]{uname});

        return cursor;
    }


    public Cursor getVehicleList(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select vehicle.vehid,vehicle.vehname,vehicle.vehtype,location.locname,vehicle_schedule.slotbegin,vehicle_schedule.slotend,user.fname,user.lname,vehicle_schedule.scheduled_date from vehicle,location,vehicle_schedule,user \n" +
                "where vehicle.vehid = vehicle_schedule.vehid and location.locid = vehicle_schedule.locid and user.userid=vehicle_schedule.opid",new String[]{});
        return cursor;
    }



    public Cursor getVehicleInventory(String vehname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select item.itemtype, vehicle_inventory.quantity, item.cost from item, vehicle_inventory, vehicle \n" +
                "where item.itemid=vehicle_inventory.itemid and vehicle.vehid=vehicle_inventory.vehid and available_date = date ('now') and vehicle.vehname=?", new String[]{vehname});

        return cursor;
    }


    public Cursor getVehicleInventory_operator(int vehid){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select distinct item.itemtype, vehicle_inventory.quantity, \n" +
                "item.cost from item, vehicle_inventory, vehicle \n" +
                "where item.itemid=vehicle_inventory.itemid and \n" +
                "vehicle.vehid=vehicle_inventory.vehid and available_date = date('now') and vehicle.vehid=?", new String[]{String.valueOf(vehid)});

        return cursor;
    }

    public Cursor getInventoryDetails(int vehid){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * from vehicle_inventory where vehid=?", new String[]{String.valueOf(vehid)});

        return cursor;
    }
    public Cursor getRevenue(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select vehicle.vehname,user.fname,user.lname,payments.payment_date,sum(payments.total_cost) as total from vehicle,user,payments where vehicle.vehid=payments.vehid\n" +
                "and user.userid=payments.opid and payment_date=date('now') group by payments.vehid", new String[]{});

        return cursor;
    }


    public boolean updateQuantity(int vehId, int itemId, float qua){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("quantity",qua);
        cv.put("vehid",vehId);
        cv.put("itemid",itemId);
        long r=sqLiteDatabase.update("vehicle_inventory",cv, "vehid=? and  itemid=?",new String[]{String.valueOf(vehId), String.valueOf(itemId)});

        if(r == -1){
            return false;
        }else{
            return true;
        }

    }

    public boolean updateProfile(int userid,String fname, String lname, String uname, String email, String phone, String street, String city,String state, String zipcode) {
        int zip=Integer.valueOf(zipcode);
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fname", fname);
        cv.put("lname", lname);
        cv.put("uname", uname);
        //cv.put("password", password);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("street_address", street);
        cv.put("city", city);
        cv.put("state", state);
        cv.put("zipcode", zip);

        long r = sqLiteDatabase.update("user", cv, "userid=?", new String[]{String.valueOf(userid)});

        if (r == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateCardDetails(int userid, String cc,String cvv, String cardType, String expdate){


        Cursor c=getCardDetails(userid);
        if(c.getCount()>0) {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("cc", cc);
            cv.put("cvv", cvv);
            cv.put("cardtype", cardType);
            cv.put("expiry", expdate);
            long u = sqLiteDatabase.update("paymentoptions", cv, "userid=?", new String[]{String.valueOf(userid)});

            if (u == -1) {
                return false;
            } else {
                return true;
            }
        }
        else{
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("userid",userid);
            cv.put("cc", cc);
            cv.put("cvv", cvv);
            cv.put("cardtype", cardType);
            cv.put("expiry", expdate);


            long r = sqLiteDatabase.insert("paymentoptions", null, cv);
            if(r == -1){
                return false;
            }else{
                return true;
            }
        }

    }

    public boolean updateCost(int itemId, float cost) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("cost", cost);
        cv.put("itemid", itemId);
        long r = sqLiteDatabase.update("item", cv, "itemid=?", new String[]{String.valueOf(itemId)});

        if (r == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int getOperatorId(String vehname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select distinct a.opid from vehicle_schedule as a join vehicle as b on a.vehid=b.vehid \n" +
                "where b.vehname=? and a.scheduled_date= date('now')  ", new String[]{vehname});

        if(cursor.getCount()>0){
            if(cursor.moveToNext()){
                return cursor.getInt(0);
            }
        }
        return -1;

    }

    public Cursor getCardDetails(final int userid){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from paymentoptions where userid = ?", new String[]{String.valueOf(userid)});

        return cursor;

    }

    public ArrayList<Location> getAllData(){
        ArrayList<Location> arrayListl = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM location",null);

        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            int duration = cursor.getInt(2);
            Location location = new Location(id,name,duration);
            arrayListl.add(location);

        }
        return arrayListl;
    }



    public boolean deleteCartEntry (int userid){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("DELETE FROM cart WHERE userid =?",new String[]{String.valueOf(userid)});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }

    }


    public int getQua_Payment(int vehId,int itemid) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c =sqLiteDatabase.rawQuery("select quantity from vehicle_inventory where vehid=? and  itemid=? and available_date= date('now')",new String[]{String.valueOf(vehId),String.valueOf(itemid)});
        if(c.getCount()>0){
            if(c.moveToNext()){
                return c.getInt(c.getColumnIndex("quantity"));
            }
        }
    return -1;
    }

    public boolean payment_updateInventory(int qua,int count, int vehId, int itemid ){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv=new ContentValues();

    cv.put("quantity", qua - count);
        cv.put("vehid",vehId);
        cv.put("itemid",itemid);
        long r=sqLiteDatabase.update("vehicle_inventory",  cv,
                "vehid=? and  itemid=? and available_date= date('now')",new String[]{String.valueOf(vehId),String.valueOf(itemid)});

        if(r == -1){
            return false;
        }else{
            return true;
        }

    }



    public boolean deleteleLocation(String lid){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //long result = sqLiteDatabase.delete("user", userid+"="+userid, null);
        Cursor cursor = sqLiteDatabase.rawQuery("DELETE FROM location WHERE locid =?",new String[]{lid});
        if(cursor.getCount() > 0){
            return false;
        }else{
            return true;
        }

    }

    public boolean editLocation(String locid,String locname,String duration){

        SQLiteDatabase sqLiteDatabase1 = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("locid",locid);
        contentValues.put("locname",locname);
        contentValues.put("duration",duration);

        // long result =sqLiteDatabase1.update("location",contentValues,"locid = ?",new String[] {locid});
        long result = sqLiteDatabase1.update("location",contentValues,"locid=?",new String[]{locid});

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public int getOpId(String fname, String lname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("select userid from user where fname=? and lname=?",new String[]{fname,lname});
        if(c.getCount()>0){
            if(c.moveToNext()){
                return c.getInt(0);
            }
        }
        return -1;
    }

    public String getLocationId(String locname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("select locid from location where locname=?", new String[]{locname});
        if(c.getCount()>0){
            if(c.moveToNext()){
                return c.getString(0);
            }
        }
        return "";
    }

    public int getSlotBegin(int opid, String locid){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("select slotbegin from vehicle_schedule where opid=? and locid=? and scheduled_date=date('now')",new String[]{String.valueOf(opid), locid});
        if(c.getCount()>0){
            if(c.moveToNext()){
                return c.getInt(0);
            }
        }
        return -1;
    }

    public Cursor getuserData(){
        ArrayList<User> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select distinct b.fname,b.lname,b.uname,c.vehname,d.locname, a.slotend \n" +
                "from vehicle_schedule as a join user as b on a.opid==b.userid \n" +
                "join vehicle as c on c.vehid==a.vehid join location as d on d.locid==a.locid  \n" +
                "where scheduled_date=date('now') "+
                "order by b.fname ASC,a.slotend ",null);

        return cursor;


    }

    public boolean deleteOperator(String userid) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //long result = sqLiteDatabase.delete("user", userid+"="+userid, null);
        Cursor cursor = sqLiteDatabase.rawQuery("DELETE FROM user WHERE userid =?", new String[]{userid});
        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }



    public Cursor cleNames(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select vehname from vehicle",null);
        return cursor;
    }

    public boolean updateVehname(int vehid,int opid){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("vehid",vehid);
        long result = sqLiteDatabase.update("vehicle_schedule",cv,"opid=? and scheduled_date=date('now')",new String[]{String.valueOf(opid)});
        if(result==-1){
            return false;
        }
        return true;
    }

    public boolean deleteSchedule(int opid, int vehid, String slotend){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("delete from vehicle_schedule where vehid=? and opid=? and slotend=?",new String[]{String.valueOf(vehid),String.valueOf(opid),slotend});
        if(cursor.getCount()>0){
            return false;
        }
        return true;
    }

    public User getUser(String emailid){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        User u=null;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user where email= ?",new String[]{emailid});
        if (cursor.moveToFirst()) {
            String fname = cursor.getString(cursor.getColumnIndex("fname"));
            String lname = cursor.getString(cursor.getColumnIndex("lname"));
            String uname = cursor.getString(cursor.getColumnIndex("uname"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String role = cursor.getString(cursor.getColumnIndex("role"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            int userid = cursor.getInt(cursor.getColumnIndex("userid"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String street_address = cursor.getString(cursor.getColumnIndex("street_address"));
            String state = cursor.getString(cursor.getColumnIndex("state"));
            int zipcode = cursor.getInt(cursor.getColumnIndex("zipcode"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            u = new User(userid,fname,lname,uname,password,role,email,phone,street_address,city,state,zipcode);
        } else {
            //user not found
        }
        return u;
    }

    public int getVehIdUser(String vehname){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c=sqLiteDatabase.rawQuery("select vehid from vehicle where vehname=?", new String[]{vehname});
        if(c.getCount()>0){
            if(c.moveToNext()){
                return c.getInt(0);
            }
        }
        return -1;
    }
    //RUTU IT3#####
    public PaymentsOptions getPaymentCardInfo(int userID)
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        PaymentsOptions p=null;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from paymentoptions where userid= ?",new String[]{String.valueOf(userID)});
        if (cursor.moveToFirst()) {
            int userid = cursor.getInt(cursor.getColumnIndex("userid"));
            String cc = cursor.getString(cursor.getColumnIndex("cc"));
            String expiry = cursor.getString(cursor.getColumnIndex("expiry"));
            String  cvv=cursor.getString(cursor.getColumnIndex("cvv"));
            String cardtype=cursor.getString(cursor.getColumnIndex("cardtype"));

            p = new PaymentsOptions(userid,cc,expiry,cvv,cardtype);
        } else {

        }
        return p;
    }

    public boolean verifyPaymentDetails(int userId, String cardno, String cv, String exp, String cardType) {

        boolean r=false;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //PaymentsOptions p=null;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from paymentoptions where userid= ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            int userid = cursor.getInt(cursor.getColumnIndex("userid"));
            String cc = cursor.getString(cursor.getColumnIndex("cc"));
            String expiry = cursor.getString(cursor.getColumnIndex("expiry"));
            String cvv = cursor.getString(cursor.getColumnIndex("cvv"));
            String cardtype = cursor.getString(cursor.getColumnIndex("cardtype"));

            if(cardno.equals(cc) && cv.equals(cvv) && exp.equals(expiry) && cardType.equals(cardtype)){
                r=true;
            }

        }

        return r;
    }

    public boolean insertPayment(int orderid, int userid,int vehid,int opid, Float tc){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

//        sqLiteDatabase.execSQL("" +
//                "INSERT INTO payments(payid,userid,opid,vehid,payment_date,total_cost) VALUES\n" +
//                "(9901000,1001000,1007000,51,date('now'),1523.26),\n" +
//                "(9903000,1003000,1009000,53,date('now'),2028.10),\n" +
//                "(9905000,1001000,1007000,51,date('now'),1222.50),\n" +
//                "(9907000,1001000,1007000,57,date('now','-1 day'),1750.80)" +
//                "");
//        //Float tc1=Float.parseFloat(tc);
//
//       //Date date;
        ContentValues contentValues = new ContentValues();
        contentValues.put("payid", orderid);
        contentValues.put("userid", userid);
        contentValues.put("vehid",vehid);
        contentValues.put("opid",opid);
        contentValues.put("payment_date",getDateTime());
        contentValues.put("total_cost",tc);


        long r = sqLiteDatabase.insert("payments", null, contentValues);
        if(r == -1){
           return false;
        }else{
            return true;
        }
    }
        public Integer getOpId1(int vehid){
           int opid = 0;
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor =sqLiteDatabase.rawQuery("select * from vehicle_schedule where vehid=?",new String[]{String.valueOf(vehid)});
            if (cursor.moveToFirst()) {
                  opid = cursor.getInt(cursor.getColumnIndex("opid"));
                }

            return opid;
            }

    private String getDateTime() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor =sqLiteDatabase.rawQuery("select date('now')", new String[]{});
        String date = null;
        if (cursor.moveToFirst()) {
           date = cursor.getString(cursor.getColumnIndex("date('now')"));
        }

        return date;
    }

    }
    //RUTU IT3 ######
