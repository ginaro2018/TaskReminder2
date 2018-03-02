package com.example.android.taskreminder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.taskreminder.Constants.TABLE_NAME;

public class DbViewActivity extends AppCompatActivity {
    private Cursor cursor;
    private ClientData display_c;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_view);

        cursor = null;
        display_c = new ClientData(this);

        listView = findViewById(R.id.list_db_results);
        try{
            getClients();
        }finally{
            display_c.close();
        }
    }

    private void getClients() {
        //Perform a managed query.
        SQLiteDatabase db = display_c.getReadableDatabase();
        cursor = db.rawQuery(" select * from " + TABLE_NAME, null);
        showClients(cursor);
    }

   private void showClients(Cursor cursor) {
       List<String> builder = new ArrayList<>();
        //Stuff them all into a big string
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String phone = cursor.getString(3);
            String policy = cursor.getString(4);
            String dateI = cursor.getString(5);
            String dateE = cursor.getString(6);

            builder.add("\nFull Names: " + name + "\n"
                    + "ID Number: " + id + "\n"
                    + "Email Address: " + email + "\n"
                    + "Phone Number: " + phone + "\n"
                    + "Type of Policy: " + policy + "\n"
                    + "Date Issued: " + dateI + "\n"
                    + "Date of Expiry: " + dateE + "\n");
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, builder);
        listView.setAdapter(adapter);
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
        DbViewActivity.this.finish();
    }
}
