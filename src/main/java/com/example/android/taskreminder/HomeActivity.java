package com.example.android.taskreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button add_client, view_client, delete_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        add_client = findViewById(R.id.add_client);
        view_client = findViewById(R.id.view_client);
        delete_client = findViewById(R.id.delete_client);

        addClient();
        viewClient();
        deleteClient();
    }

    public void addClient(){
        add_client.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent add_c = new Intent( HomeActivity.this, MainActivity.class );
                startActivity(add_c);
            }
        });
    }

    public void viewClient(){
        view_client.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent view_c = new Intent( HomeActivity.this, ViewActivity.class );
                startActivity(view_c);
            }
        });
    }

    public void deleteClient(){
        delete_client.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent delete_c =new Intent( HomeActivity.this, DeleteActivity.class );
                startActivity(delete_c);
            }
        });
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
        HomeActivity.this.finish();
    }

}