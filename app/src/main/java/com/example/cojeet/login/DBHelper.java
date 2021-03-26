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
        db.execSQL("CREATE TABLE USER_DETAILS(Username TEXT,Email TEXT primary key ,Contact TEXT,Age TEXT,Gender TEXT,Height TEXT,Weight TEXT,CoronaStatus TEXT,VaccinationStatus TEXT,Medicalhistory TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<0){
            db.execSQL("CREATE TABLE USER_DETAILS(Username TEXT,Email TEXT primary key ,Contact TEXT,Age TEXT,Gender TEXT,Height TEXT,Weight TEXT,CoronaStatus TEXT,VaccinationStatus TEXT,Medicalhistory TEXT)");
        }
        if(oldVersion<2){
            db.execSQL("ALTER TABLE USER_DETAILS ADD COLUMN Contact_history TEXT");
            db.execSQL("ALTER TABLE USER_DETAILS ADD COLUMN health_condition TEXT");

        }
        if(oldVersion<4){

            db.execSQL("ALTER TABLE USER_DETAILS ADD COLUMN location TEXT");

        }


    }

    public Cursor getdata(String Email){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from USER_DETAILS where Email = ?",new String[]{Email});
        return cursor;

    }

    public Cursor getvdata(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select location,count(Email) from USER_DETAILS where VaccinationStatus= ? group by location",new String[]{"Vaccinated"});
        return cursor;

    }

    public Cursor gethdata(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select location,count(Email) from USER_DETAILS where health_condition is not null group by location",null);
        return cursor;

    }

    public boolean insertData2(String name,String email,String contact,String age,String gender,String height,String weight,String corona,String vaccine,String medhis,String mhis,String cont,String loc){
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
        contentValues.put("Contact_history",cont);
        contentValues.put("health_condition",mhis);
        contentValues.put("location",loc);
        long result = DB.insert("USER_DETAILS", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public boolean updateuserdata(String name,String email,String contact,String age,String gender,String height,String weight,String corona,String vaccine,String medhis,String mhis,String cont,String loc){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Username", name);
        contentValues.put("Contact", contact);
        contentValues.put("Age", age);
        contentValues.put("Gender", gender);
        contentValues.put("Height", height);
        contentValues.put("Weight", weight);
        contentValues.put("CoronaStatus", corona);
        contentValues.put("VaccinationStatus", vaccine);
        contentValues.put("Medicalhistory", medhis);
        contentValues.put("Contact_history",cont);
        contentValues.put("health_condition",mhis);
        contentValues.put("location",loc);
        Cursor cursor=DB.rawQuery("Select * from USER_DETAILS where Email = ?",new String[]{email});
        if(cursor.getCount()>0){
            long result = DB.update("USER_DETAILS",contentValues,"Email=?",new String[]{email});
            if(result==-1) return false;
            else
                return true;
        }
        else
            return false;

    }




}
