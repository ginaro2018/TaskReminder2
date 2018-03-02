package com.example.android.taskreminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.android.taskreminder.Constants.DATE_EXPIRED;
import static com.example.android.taskreminder.Constants.DATE_ISSUED;
import static com.example.android.taskreminder.Constants.EMAIL;
import static com.example.android.taskreminder.Constants.ID_NO;
import static com.example.android.taskreminder.Constants.NAME;
import static com.example.android.taskreminder.Constants.PHONE;
import static com.example.android.taskreminder.Constants.POLICY;
import static com.example.android.taskreminder.Constants.TABLE_NAME;

public class ClientData extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "insurance.db";
    private static final int DATABASE_VERSION = 2;

    //Create a helper object for the Insurance database
    public ClientData(Context con){
        super(con, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE_NAME + " (" +
        ID_NO + " TEXT PRIMARY KEY, " +
        NAME + " TEXT NOT NULL, " +
        EMAIL + " TEXT NOT NULL, " +
        PHONE + " TEXT NOT NULL, " +
        POLICY + " TEXT NOT NULL, " +
        DATE_ISSUED + " TEXT NOT NULL," +
        DATE_EXPIRED + " TEXT NOT NULL);";

        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
