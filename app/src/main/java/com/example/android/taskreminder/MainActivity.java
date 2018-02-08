package com.example.android.taskreminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    DbHelper myDb;
    EditText name, email, phone, policyType, dateissued, dateExpired;
    Button btnAddClient, delButton;
     EditText date;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        myDb = new DbHelper( this );


        // initialize the editTexts and buttons(casting)
        name = (EditText) findViewById( R.id.editText1 );
        email = (EditText) findViewById( R.id.editText2 );
        phone = (EditText) findViewById( R.id.editText3 );
        policyType = (EditText) findViewById( R.id.editText4 );
        dateissued = (EditText) findViewById( R.id.datepicker1 );
        dateExpired = (EditText) findViewById( R.id.datepicker2 );
        //buttons
        btnAddClient = (Button) findViewById( R.id.button_submit );
        //  delButton = (Button) findViewById( R.id.buttonSrc );

        //calling the AddClient method inside onCreate method
        AddClient();
        myDate();
        myDate2();
    }

    /*
//defining  the date method
    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
    }  */

    // method to add clients
    public void AddClient() {
        btnAddClient.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData( name.getText().toString(), email.getText().toString(), phone.getText().toString(),
                                policyType.getText().toString(), dateissued.getText().toString(), dateExpired.getText().toString() );
                        if (isInserted = true)
                            Toast.makeText( MainActivity.this, "Data Inserted", Toast.LENGTH_LONG ).show();
                        else
                            Toast.makeText( MainActivity.this, "Data NOT Inserted", Toast.LENGTH_LONG ).show();
                    }
                }
        );

    }

    // the date1 method
    public void myDate() {
        date.setOnClickListener(
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
                                        date.setText( dayOfMonth + "/"
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
    
    

}
