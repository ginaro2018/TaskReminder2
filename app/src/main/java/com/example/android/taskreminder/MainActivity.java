package com.example.android.taskreminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DbHelper myDb;
    EditText name, email, phone, policyType, dateissued, dateExpired;
    Button btnAddClient, delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        myDb = new DbHelper( this );


        // initialize the editTexts and buttons
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


}
