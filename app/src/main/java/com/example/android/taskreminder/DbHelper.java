package com.example.android.taskreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by GINARO MAINA WAMBUGU on 1/29/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "taskReminder.db";
    public static final String TABLE_NAME = "clientData_table";
    public static final String COL1 = "id";
    public static final String COL2 = "name";
    public static final String COL3 = "email";
    public static final String COL4 = "phone";
    public static final String COL5 = "policy";
    public static final String COL6 = "dateIssued";
    public static final String COL7 = "dateExpired";

    //constructor
    public DbHelper(Context context) {
        super( context, DATABASE_NAME, null, 1 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( " create table " + TABLE_NAME + " (id INTERGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,phone TEXT,policy TEXT,dateIssued TEXT ,dateExpired TEXT )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public boolean insertData(String name, String email, String phone, String policy, String dateIssued, String dateExpired) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( COL2, name );
        contentValues.put( COL3, email );
        contentValues.put( COL4, phone );
        contentValues.put( COL5, policy );
        contentValues.put( COL6, dateIssued );
        contentValues.put( COL7, dateExpired );
        Long result = db.insert( TABLE_NAME, null, contentValues );

        if (result == -1)
            return false;
        else
            return true;
    }

}
