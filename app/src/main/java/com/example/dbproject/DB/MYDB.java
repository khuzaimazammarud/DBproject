package com.example.dbproject.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.dbproject.model.Annoucement;
import com.example.dbproject.model.Event;
import com.example.dbproject.model.Society;
import com.example.dbproject.model.User;
import com.example.dbproject.model.member;
import com.example.dbproject.model.societyMember;
import com.example.dbproject.model.userViewSociety;
import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.List;

public class MYDB extends SQLiteOpenHelper {


    public static final String DBname = "society_db";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ID = "user_id";
    public static final String TABLE_USER = "users";


    public MYDB(Context context) {
        super(context, "society_db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MYDBS) {

        //creating table Society

        MYDBS.execSQL("CREATE TABLE users(userid INTEGER primary key autoincrement,name Text,email TEXT unique" +
                ",password TEXT,societyid integer default null , regnumber TEXT unique" +
                ",CONSTRAINT fk_name foreign key (societyid) references society(societyid) on delete set null)");



        MYDBS.execSQL("create table member(memberid integer primary key autoincrement,membername TEXT,memberemail TEXT unique,memberregno TEXT unique,password TEXT,memberage int default null,userid int,societyid int default null,memberroleid int default null," +
                "CONSTRAINT fk_name foreign key (societyid) references society(societyid) on delete set null, CONSTRAINT user_name foreign key (userid) references users(userid) on delete set null," +
                "CONSTRAINT role_name foreign key (memberroleid) references memberrole(memberroleid) on delete set null)");

        //creating table society

        MYDBS.execSQL("create table society(societyid integer primary key autoincrement,societyname TEXT unique)");

        MYDBS.execSQL("create table annoucement(annoucid integer primary key autoincrement,annouctitle TEXT unique,Describtion TEXT,startdate TEXT)");

        //creating table events
        MYDBS.execSQL("create table event(eventid integer primary key autoincrement,eventname TEXT unique,startdate TEXT,enddate TEXT, societyid integer default null," +
                "CONSTRAINT fk_name foreign key (societyid) references society(societyid) on delete set null)");

        MYDBS.execSQL("create table memberrole(memberroleid integer primary key autoincrement,membername TEXT unique)");

        MYDBS.execSQL("create table head(headid integer primary key autoincrement,headname TEXT unique,userid int,societyid int" +
                ",CONSTRAINT fk_name foreign key (societyid) references society(societyid) on delete set null,CONSTRAINT userid foreign key (userid) references users(userid) on delete set null)");


        MYDBS.execSQL("create table oldevent(eventid integer primary key autoincrement,eventname TEXT, stardate TEXT)");


        //===========trigger=======================//


        MYDBS.execSQL("\n" +
                "create TRIGGER user_emp BEFORE INSERT on users\n" +
                "BEGIN\n" +
                "SELECT\n" +
                "CASE\n" +
                "when NEW.email not like '%_@__%._%' THEN\n" +
                "RAISE(ABORT,'INvalid email')\n" +
                "end;\n" +
                "end;");

        MYDBS.execSQL("create trigger memberupdate after insert on users\n" +
                "BEGIN\n" +
                "insert INTO member(membername,memberemail,memberregno,password,memberage,userid,societyid,memberregno)\n" +
                "VALUES (new.name,new.email,new.regnumber,new.password,NULL,new.userid,NULL,NULL);\n" +
                "END;");

        MYDBS.execSQL("create trigger userupdate after update on users\n" +
                "BEGIN\n" +
                "update  member SET membername=new.name,memberemail=new.email,password=new.password,memberregno=new.regnumber where userid=new.userid;\n" +
                "END;");

        ///=====================view=======================//

        MYDBS.execSQL("create View nonUser as select * from member where societyid is null");

        MYDBS.execSQL("create View societyheadap as select s.societyname,h.headname from society s " +
                "inner join head h on s.societyid=h.societyid");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MYDBS, int i, int i1) {
        MYDBS.execSQL("drop table if exists users");
    }







    //=====================================================//
    //=================check memberview==================//
    //===================================================//

    public boolean checkNonMemberview(String username,String password)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor curson = MYDBMS.rawQuery("select * from nonUser where name=? and password=?", new String[] {username,password});
        if(curson.getCount() > 0)
            return true;
        else
            return false;
    }


    //=========================================================///
    //=================user login and signup===========================//
    ///======================================================================///

    public boolean checkuserpass(String username,String password)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor curson = MYDBMS.rawQuery("select * from users where name=? and password=?", new String[] {username,password});
        if(curson.getCount() > 0)
            return true;
        else
            return false;
    }



    ///check already exist user

    public boolean checkuser(String username)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor curson = MYDBMS.rawQuery("select * from users where name=?", new String[] {username});
        if(curson.getCount() > 0)
            return true;
        else
            return false;
    }





    //=========================================================//
    //============USER DATABSE=================================//
    //=========================================================//


    public void addUser(String name,String email,String password,String regnumber){
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("email",email);
        values.put("password",password);
        values.put("regnumber",regnumber);

        MYDBMS.insert(TABLE_USER,null,values);
        MYDBMS.close();
    }


    //====get data users again====//

    public ArrayList<User> readUser() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUser = db.rawQuery("SELECT * FROM users", null);

        // on below line we are creating a new array list.
        ArrayList<User> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new User(cursorUser.getInt(0),cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getString(3),
                        cursorUser.getInt(4)));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }


    //===delete user========//

    public void deleteUser(String userName) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("users", "name=?", new String[]{userName});
        db.close();
    }


    //=====update user========//


    public void updateUser(String username_original,String userName, String userEmail, String userPassword,
                             int userSocietyId,String regno) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("name",userName);
        values.put("email",userEmail);
        values.put("password",userPassword);
        values.put("societyid",userSocietyId);
        values.put("regnumber",regno);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update("users", values, "name=?", new String[]{username_original});
        db.close();

    }



    //==fetuser alag tariqe see bache======//

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select * from " +TABLE_USER , null);
        return cursor;
    }


    ///========================================================//
    //==================SOCIETY DB ============================//
    //=========================================================//


    //=================Add society===========================//

    public void addSociety(String societyname){
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("societyname",societyname);
        MYDBMS.insert("society",null,values);
        MYDBMS.close();
    }


    //===read society DATA======//

    public ArrayList<Society> readSociety() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUser = db.rawQuery("SELECT * FROM society", null);

        // on below line we are creating a new array list.
        ArrayList<Society> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new Society(cursorUser.getString(1),cursorUser.getInt(0)));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }

    //=======update society=========//

    public void updateSociety(String societyName, String societynameEdt) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("societyname",societynameEdt);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update("society", values, "societyname=?", new String[]{societyName});
        db.close();
    }


    //====delete society====/

    public void deleteSociety(String societyName) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("society", "societyname=?", new String[]{societyName});
        db.close();
    }


    //==============check society=====================//

    public boolean checkSociety(String societyname)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor curson = MYDBMS.rawQuery("select * from society where societyname=?", new String[] {societyname});
        if(curson.getCount() > 0)
            return true;
        else
            return false;
    }



    //==================================================//
    //==============Event Table========================//
    /////////////////////////////////////////////////////


    //==================Add event=======================//

    public void addEvent(String eventName,String startDate,String endDate,int societyid){

        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("eventname",eventName);
        values.put("startdate",startDate);
        values.put("enddate",endDate);
        values.put("societyid",societyid);

        MYDBMS.insert("event",null,values);
        MYDBMS.close();
    }

    //=====getAllEvents====//

    public ArrayList<Event> readEvent() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUser = db.rawQuery("SELECT * FROM event", null);

        // on below line we are creating a new array list.
        ArrayList<Event> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new Event(cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getString(3),
                        cursorUser.getInt(0),
                        cursorUser.getInt(4)));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }


    //===========update event=======================//

    public void updateEvent(String originalEventname,String eventName, String startDate, String endDate,
                           int eventSocietyId) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("eventname",eventName);
        values.put("startdate",startDate);
        values.put("enddate",endDate);
        values.put("societyid",eventSocietyId);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update("event", values, "eventname=?", new String[]{originalEventname});
        db.close();
    }


    //=============update events=====================//


    public void deleteEvent(String eventName) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("event", "eventname=?", new String[]{eventName});
        db.close();
    }



    public boolean checkEvent(String eventname)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor curson = MYDBMS.rawQuery("select * from event where eventname=?", new String[] {eventname});
        if(curson.getCount() > 0)
            return true;
        else
            return false;
    }


    //===============================//
    //========View for User========//
    //=============================//

    public boolean checkUserView(String username,String password)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor curson = MYDBMS.rawQuery("select * from users where name=? and password=? and (societyid isnull or societyid=0)", new String[] {username,password});
        if(curson.getCount() > 0)
            return true;
        else
            return false;
    }


    //===================================//
    //====View for specific Society======//
    //===================================//

    public ArrayList<userViewSociety> readUserEvents(String username,String password) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUser = db.rawQuery("select s.societyname,e.eventname,e.startdate,e.enddate from member u\n" +
                "inner join society s on s.societyid = u.societyid\n" +
                "inner join event e on e.societyid = s.societyid\n" +
                "where u.membername=? and password =?",new String[] {username,password});

        // on below line we are creating a new array list.
        ArrayList<userViewSociety> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new userViewSociety(cursorUser.getString(0),
                        cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getString(3)));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }

    //======================================================//
    //===============Read All events========================//
    //=====================================================//


    public ArrayList<userViewSociety> readAllEvents() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.

        Cursor cursorUser = db.rawQuery("select s.societyname, e.eventname,e.startdate,e.enddate from society s\n" +
                "INNER join event e on e.societyid=s.societyid",null);
        // on below line we are creating a new array list.
        ArrayList<userViewSociety> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new userViewSociety(cursorUser.getString(0),
                        cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getString(3)));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }


    //===================================================//
    //=============add head=================================//
    //======================================================//


    public void addhead(String name,int userid,int societyid){
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("headname",name);
        values.put("userid",userid);
        values.put("societyid",societyid);

        MYDBMS.insert("head",null,values);
        MYDBMS.close();
    }


    //========================================================//
    //=========================check head login=====================//
    //================================================================//


    public boolean checkhead(String username)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor curson = MYDBMS.rawQuery("select * from head where headname=?", new String[] {username});
        if(curson.getCount() > 0)
            return true;
        else
            return false;
    }


    //================================================//
    //=================add annoucement================//
    //================================================//

    public void addannoucement(String title,String desc,String startdate){
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("annouctitle",title);
        values.put("Describtion",desc);
        values.put("startdate",startdate);

        MYDBMS.insert("annoucement",null,values);
        MYDBMS.close();
    }


    //==================================================//
    //==================View annoucement===================//
    //=====================================================//

    public ArrayList<Annoucement> readAllAnnoucement() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.

        Cursor cursorUser = db.rawQuery("select * from annoucement",null);
        // on below line we are creating a new array list.
        ArrayList<Annoucement> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new Annoucement(cursorUser.getInt(0),
                        cursorUser.getString(1),
                        cursorUser.getString(3),
                        cursorUser.getString(2)));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }

    //==============================================//
    //==============add Member======================//
    //==============================================//

    public void addMember(String name,String email,int userid,String regno,String password){
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("membername",name);
        values.put("memberemail",email);
        values.put("userid",userid);
        values.put("memberregno",regno);
        values.put("password",password);

        MYDBMS.insert("member",null,values);
        MYDBMS.close();
    }

    //==============================================//
    //==========fetching user for id======================//
    //===================================================//


    public int fetchid(String username,String password)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor cursorUser = MYDBMS.rawQuery("select userid from users where name=? and password=?", new String[] {username,password});

        int userid=0;

        if (cursorUser.moveToFirst()) {
            do {
                userid = cursorUser.getInt(0);
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return userid;
    }

    //==============================================//
    //============CHECK non member view==========//
    //=============================================//

    public boolean checkNonMemberView(String username,String password)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor curson = MYDBMS.rawQuery("select * from member where membername=? and password=? and (societyid isnull or societyid=0)", new String[] {username,password});
        if(curson.getCount() > 0)
            return true;
        else
            return false;
    }

    //================================//
    //===fetching one member details======//
    //==============================//

    public ArrayList<member> readOnemember(String username,String password) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.

        Cursor cursorUser = db.rawQuery("select * from member where membername = ? and password = ?",new String[] {username,password});
        // on below line we are creating a new array list.
        ArrayList<member> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new member(cursorUser.getInt(6),
                        cursorUser.getInt(0),
                        cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getString(4),
                        cursorUser.getString(3)));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }


    //=============================//
    //====join society========//
    //======================//


    public void joinSociety(String membername,String memberemail,String memberpass,int mamaberage,String memberregno,int societyid,int userid) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("membername",membername);
        values.put("memberemail",memberemail);
        values.put("memberregno",memberregno);
        values.put("password",memberpass);
        values.put("memberage",mamaberage);
        values.put("userid",userid);
        values.put("societyid",societyid);


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update("member", values, "memberregno=?", new String[]{memberregno});
        db.close();
    }

    //=======================================//
    //===========fetch head id ==============//
    //========================================//


    public int fetchidHead(String username)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor cursorUser = MYDBMS.rawQuery("select societyid from head where headname=?", new String[] {username});

        int userid=0;

        if (cursorUser.moveToFirst()) {
            do {
                userid = cursorUser.getInt(0);
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return userid;
    }

    //=======================================//
    //===============fateching event with societyname head ======//
    //=======================================================//

    public ArrayList<userViewSociety> readMemberEvents(int societyid) {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorUser = db.rawQuery("select s.societyid,s.societyname,e.eventname,e.startdate,e.enddate from society s\n" +
                "inner join event e on s.societyid=e.societyid where s.societyid = ?",new String[] {String.valueOf(societyid)});

        // on below line we are creating a new array list.
        ArrayList<userViewSociety> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new userViewSociety(cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getString(3),
                        cursorUser.getString(4),
                        cursorUser.getInt(0)));

            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }


    public ArrayList<User> fetchidusersociety(String username,String password)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor cursorUser = MYDBMS.rawQuery("select * from users where name=? and password=?", new String[] {username,password});

        ArrayList<User> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new User(cursorUser.getInt(0),
                        cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getString(3),
                        cursorUser.getInt(4),
                        cursorUser.getString(5)));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }


    //===========fetching memnber of society================//
    //======================================================//

    public ArrayList<societyMember> fetchMember(int societyid)
    {
        SQLiteDatabase MYDBMS = this.getWritableDatabase();
        Cursor cursorUser = MYDBMS.rawQuery("select m.membername,m.memberage,m.memberemail,m.memberregno from member m\n" +
                "inner join society s on s.societyid=m.societyid where m.societyid=?;", new String[] {String.valueOf(societyid)});

        ArrayList<societyMember> cursorUserArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                cursorUserArrayList.add(new societyMember(cursorUser.getString(0),
                        cursorUser.getString(1),
                        cursorUser.getString(2),
                        cursorUser.getString(3)));

            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return cursorUserArrayList;
    }

    public void updateannoucement(String title,String message,String startdate,int annoucementid) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("annouctitle",title);
        values.put("Describtion",message);
        values.put("startdate",startdate);
        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update("annoucement", values, "annoucid=?", new String[]{String.valueOf(annoucementid)});
        db.close();

    }

}
