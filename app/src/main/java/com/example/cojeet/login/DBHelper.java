package com.example.cojeet.login;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;


import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int version=4;

    public DBHelper( Context context) {
        super(context, "Users.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE USER_DETAILS(Username TEXT ,Email TEXT primary key ,Contact TEXT ,Age TEXT ,Gender TEXT,Height TEXT ,Weight TEXT ,CoronaStatus TEXT ,VaccinationStatus TEXT ,Medicalhistory TEXT,Contact_history TEXT,health_condition TEXT,Latitude TEXT,Longitude TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<0){
            db.execSQL("CREATE TABLE USER_DETAILS(Username TEXT,Email TEXT primary key ,Contact TEXT,Age TEXT,Gender TEXT,Height TEXT,Weight TEXT,CoronaStatus TEXT,VaccinationStatus TEXT,Medicalhistory TEXT,Contact_history TEXT,health_condition TEXT,Latitude TEXT,Longitude TEXT)");
        }
    }

    public boolean insertData2(String name,String email,String contact,String age,String gender,String height,String weight,String corona,String vaccine,String medhis,String mhis,String cont,String latitude,String longitude){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Username", name);
        contentValues.put("Email", email);
        contentValues.put("Contact", contact);
        contentValues.put("Age", age);
        contentValues.put("Gender", gender);
        contentValues.put("Height", height);
        contentValues.put("Weight", weight);
        contentValues.put("CoronaStatus", corona);
        contentValues.put("VaccinationStatus", vaccine);
        contentValues.put("Medicalhistory", medhis);
        contentValues.put("HEALTH_condition",mhis);
        contentValues.put("Contact_history",cont);
        contentValues.put("Latitude",latitude);
        contentValues.put("Longitude",longitude);
        long result = DB.insert("USER_DETAILS", null, contentValues);
        return result != -1;
    }
}

