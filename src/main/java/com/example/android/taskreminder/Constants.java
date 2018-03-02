package com.example.android.taskreminder;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
    public static final String TABLE_NAME = "cilent";

    //Columns in the Events database
    public static final String ID_NO= "idNo";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String POLICY = "policy";
    public static final String DATE_ISSUED = "dateIssued";
    public static final String DATE_EXPIRED = "dateExpired";
}
