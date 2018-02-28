package com.example.android.taskreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button loginBtn;
    private TextView info;
    private int counter=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        //casting
        username=(EditText) findViewById( R.id.username );
        password=(EditText) findViewById( R.id.password );
        loginBtn=(Button) findViewById( R.id.l_button );
        info= (TextView) findViewById( R.id.info );

        info.setVisibility(View.GONE);

        //onclicklistener for login button
        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(),password.getText().toString() );
            }
        } );

    }

    // function to validate login credentials

    private void validate(String UserName, String Userpassword){
        if ((UserName.equals("Admin"))&&(Userpassword.equals(  "1234"))){
            Intent intent=new Intent( login.this,HomeActivity.class );
            startActivity( intent );
        }else {
            counter--;
            info.setText( "Number of attempts remaining: "+ String.valueOf( counter ) );
            info.setVisibility(View.VISIBLE);
            if (counter==0){
                loginBtn.setEnabled( false );
            }
        }

    }
}
