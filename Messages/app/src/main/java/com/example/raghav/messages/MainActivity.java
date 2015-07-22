package com.example.raghav.messages;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private ListView message_list;
    public static String sample [] = {
            "Hello",
            "World",
            "How", "Are"
    };
    private Uri inboxUri;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message_list = (ListView) findViewById(R.id.message_list);
        inboxUri = Uri.parse("content://sms/inbox");
        String request_cols [] = {"_id", "address", "body"};
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query (inboxUri, request_cols, null, null, null);

        adapter = new SimpleCursorAdapter(this, R.layout.list_row, cursor, new String[]{"body", "address"}, new int [] {R.id.sender,R.id.body});
        message_list.setAdapter (adapter);


        /*message_list.setAdapter(new ArrayAdapter< String >(this, android.R.layout.simple_list_item_1, sample));
        message_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), sample [position], Toast.LENGTH_SHORT).show ();
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
