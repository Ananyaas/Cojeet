package com.example.cojeet.login;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "Users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USER_DETAILS(Username TEXT not null,Email TEXT primary key ,Contact TEXT not null,Age TEXT not null,Gender TEXT not null,Height TEXT not null,Weight TEXT not null,CoronaStatus TEXT not null,VaccinationStatus TEXT not null,Medicalhistory TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists USER_DETAILS");
    }

    public boolean insertData2(String name,String email,String contact,String age,String gender,String height,String weight,String corona,String vaccine,String medhis){
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
        long result = DB.insert("USER_DETAILS", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
}
