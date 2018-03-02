package com.example.android.taskreminder;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.taskreminder.Constants.ID_NO;
import static com.example.android.taskreminder.Constants.TABLE_NAME;

public class ViewActivity extends AppCompatActivity {
    private ClientData clients;
    private EditText search;
    private Button submit, delete, edit;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        submit = findViewById(R.id.button_submit);
        delete = findViewById(R.id.button_delete);
        edit = findViewById(R.id.button_edit);
        cursor = null;
        clients = new ClientData(this);
        search = findViewById(R.id.search_text);

        goToEdit();
        runQuerySearch();
        runQueryDelete();
    }

    private void runQuerySearch() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClients();
            }
        });
    }

    private void runQueryDelete(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteClients();
            }
        });
    }

    private void goToEdit(){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit_c =new Intent( ViewActivity.this, EditActivity.class );
                startActivity(edit_c);
            }
        });
    }

    //Running a search Query
    private void getClients(){
        //Get the ID number entered by the user
        String search_input = search.getText().toString();

        /*Perform a managed query. The Activity will handle closing and
        re-querying the cursor when needed.*/
        SQLiteDatabase db = clients.getReadableDatabase();

        //Use of raw query to collect data
        try{
            String[] params = new String[]{search_input};
            cursor = db.rawQuery(" select * from " + TABLE_NAME + " where " + ID_NO + " = ?" , params);
            showClient(cursor);
        }catch(SQLException e){
            String message = e.getMessage();
            Toast.makeText( ViewActivity.this, message, Toast.LENGTH_LONG ).show();
        }finally{
            cursor.close();
            clients.close();
        }
    }

    //Running a delete Query
    private void deleteClients() {
        //Get the ID number entered by the user
        String search_input = search.getText().toString();

        /*Perform a managed query. The Activity will handle closing and
        re-querying the cursor when needed.*/
        SQLiteDatabase db = clients.getReadableDatabase();

        //Use of raw query to collect data
        try {
            String[] params = new String[]{search_input};
            cursor = db.rawQuery(" delete from " + TABLE_NAME + " where " + ID_NO + " = ?", params);
            showClient(cursor);
            Toast.makeText( ViewActivity.this, "Client Details Deleted!", Toast.LENGTH_LONG ).show();
        } catch(SQLException e){
            String message = e.getMessage();
            Toast.makeText( ViewActivity.this, message, Toast.LENGTH_LONG ).show();
        }finally {
            cursor.close();
            clients.close();
        }
    }

    //Displaying the query results
    private void showClient(Cursor cursor){
        StringBuilder builder = new StringBuilder();
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);
            String policy = cursor.getString(4);
            String dateI = cursor.getString(5);
            String dateE = cursor.getString(6);

            builder.append("ID Number: " + id + "\n");
            builder.append("Full Names: " + name + "\n");
            builder.append("Email Address: " + email + "\n");
            builder.append("Phone Number: " + phone + "\n");
            builder.append("Type of Policy: " + policy + "\n");
            builder.append("Date Issued: " + dateI + "\n");
            builder.append("Date of Expiry: " + dateE + "\n");
        }
        //Display on the screen
        TextView text = findViewById(R.id.result_display);
        text.setText(builder);
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
        ViewActivity.this.finish();
    }
}
