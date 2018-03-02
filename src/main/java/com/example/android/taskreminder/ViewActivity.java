package com.example.android.taskreminder;

import android.database.Cursor;
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

import static com.example.android.taskreminder.Constants.ID_NO;
import static com.example.android.taskreminder.Constants.TABLE_NAME;

public class ViewActivity extends AppCompatActivity {
    private ClientData clients;
    private EditText search;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        submit = findViewById(R.id.button_submit);
        clients = new ClientData(this);
        search = findViewById(R.id.search_text);

        runQuery();

    }

    private void runQuery() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClients();
            }
        });
    }


    //Running a Query
    private void getClients(){
        //Get the ID number entered by the user
        String search_input = search.getText().toString();

        /*Perform a managed query. The Activity will handle closing and
        re-querying the cursor when needed.*/
        SQLiteDatabase db = clients.getReadableDatabase();

        //Use of raw query to collect data
        Cursor cursor = db.rawQuery(" select * from " + TABLE_NAME + " where " + (ID_NO.equals(search_input)), null);
        showClient(cursor);
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

            builder.append(id).append(" : ");
            builder.append(name).append(" : ");
            builder.append(email).append(" : ");
            builder.append(phone).append(" : ");
            builder.append(policy).append(" : ");
            builder.append(dateI).append(" : ");
            builder.append(dateE).append("\n");
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
