package com.example.android.taskreminder;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.android.taskreminder.Constants.DATE_EXPIRED;
import static com.example.android.taskreminder.Constants.DATE_ISSUED;
import static com.example.android.taskreminder.Constants.EMAIL;
import static com.example.android.taskreminder.Constants.ID_NO;
import static com.example.android.taskreminder.Constants.NAME;
import static com.example.android.taskreminder.Constants.PHONE;
import static com.example.android.taskreminder.Constants.POLICY;
import static com.example.android.taskreminder.Constants.TABLE_NAME;


public class MainActivity extends AppCompatActivity {
    //DbHelper myDb;
    private ClientData a_client;
    private EditText idNo, name, email, phone, policyType, dateissued, dateExpired;
    private Button btnAddClient;
    private DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_main);
        //myDb = new DbHelper( this );
        a_client = new ClientData(this);

        // initialize the editTexts and buttons(casting)
        idNo = (EditText) findViewById(R.id.idNo);
        name = (EditText) findViewById( R.id.editText1 );
        email = (EditText) findViewById( R.id.editText2 );
        phone = (EditText) findViewById( R.id.editText3 );
        policyType = (EditText) findViewById( R.id.editText4 );
        dateissued = (EditText) findViewById( R.id.datepicker1 );
        dateExpired = (EditText) findViewById( R.id.datepicker2 );
        //buttons
        btnAddClient = (Button) findViewById( R.id.button_submit );

        //calling the AddClient method inside onCreate method
        AddClient();

        myDate();
        myDate2();
    }


    //defining  the date method
    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }

    // method to add clients
    private void AddClient() {
        btnAddClient.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertData();
                    }
                }
        );
    }

    //method to insert data to Client database
    public boolean insertData(){
        SQLiteDatabase db = a_client.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_NO, idNo.getText().toString() );
        values.put(NAME, name.getText().toString() );
        values.put(EMAIL, email.getText().toString());
        values.put(PHONE, phone.getText().toString());
        values.put(POLICY, policyType.getText().toString());
        values.put(DATE_ISSUED, dateissued.getText().toString());
        values.put(DATE_EXPIRED, dateExpired.getText().toString());

        Long result = db.insertOrThrow(TABLE_NAME, null, values);
        if (result == -1) {
            Toast.makeText( MainActivity.this, "Data NOT Inserted", Toast.LENGTH_LONG ).show();
            return false;
        } else{
            Toast.makeText( MainActivity.this, "Data Inserted", Toast.LENGTH_LONG ).show();
            return true;
        }
    }

    //the date1 method
    public void myDate() {
        dateissued.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calender class's instance and get current date , month and year from calender
                        final Calendar c = Calendar.getInstance();
                        int mYear = c.get( Calendar.YEAR ); // current year
                        int mMonth = c.get( Calendar.MONTH ); // current month
                        int mDay = c.get( Calendar.DAY_OF_MONTH ); // current day
                        // date picker dialog
                        datePickerDialog = new DatePickerDialog( MainActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        // set day of month , month and year value in the edit text
                                        dateissued.setText( dayOfMonth + "/"
                                                + (monthOfYear + 1) + "/" + year );

                                    }
                                }, mYear, mMonth, mDay );
                        datePickerDialog.show();
                    }
                } );
    }

    //calling myDate function in datePicker2
    public void myDate2() {
        dateExpired.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calender class's instance and get current date , month and year from calender
                        final Calendar c = Calendar.getInstance();
                        int mYear = c.get( Calendar.YEAR ); // current year
                        int mMonth = c.get( Calendar.MONTH ); // current month
                        int mDay = c.get( Calendar.DAY_OF_MONTH ); // current day
                        // date picker dialog
                        datePickerDialog = new DatePickerDialog( MainActivity.this,
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        // set day of month , month and year value in the edit text
                                        dateExpired.setText( dayOfMonth + "/"
                                                + (monthOfYear + 1) + "/" + year );

                                    }
                                }, mYear, mMonth, mDay );
                        datePickerDialog.show();
                    }
                } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            //Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
        MainActivity.this.finish();
    }

}
