package com.example.android.taskreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GINARO MAINA WAMBUGU on 1/29/2018.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "taskReminder.db";
    public static final String TABLE_NAME = "clientData_table";
    public static final String COL1 = "id";
    public static final String COL2 = "firstname";
    public static final String COL3 = "lastname";
    public static final String COL4 = "email";
    public static final String COL5 = "phone";
    public static final String COL6 = "policy";
    public static final String COL7 = "dateIssued";
    public static final String COL8 = "dateExpired";

    //constructor
    public DbHelper(Context context) {
        super( context, DATABASE_NAME, null, 1 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( " create table " + TABLE_NAME + " (" +
                "id INTERGER PRIMARY KEY AUTOINCREMENT," +
                "firstname TEXT," +
                "lastname TEXT," +
                "email TEXT," +
                "phone TEXT," +
                "policy TEXT," +
                "dateIssued TEXT," +
                "dateExpired TEXT )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public boolean insertData(String fname, String lname, String email, String phone, String policy, String dateIssued, String dateExpired) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( COL2, fname );
        contentValues.put( COL3, lname);
        contentValues.put( COL4, email );
        contentValues.put( COL5, phone );
        contentValues.put( COL6, policy );
        contentValues.put( COL7, dateIssued );
        contentValues.put( COL8, dateExpired );
        Long result = db.insert( TABLE_NAME, null, contentValues );

        if (result == -1)
            return false;
        else
            return true;
    }

}
