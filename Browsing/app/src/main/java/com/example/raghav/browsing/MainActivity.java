package com.example.raghav.browsing;

import android.database.Cursor;
import android.database.DataSetObserver;
import android.provider.Browser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView tv_title = (TextView) findViewById(R.id.title);
        //TextView tv_uri = (TextView) findViewById(R.id.uri);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);

        String[] proj = new String[] { Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL };
        String sel = Browser.BookmarkColumns.BOOKMARK + " = 0"; // 0 = history, 1 = bookmark
        Cursor mCur = getContentResolver().query(Browser.BOOKMARKS_URI, proj, sel, null, null);
        mCur.moveToFirst();

        @SuppressWarnings("unused")
        String title = "";
        @SuppressWarnings("unused")
        String url = "";

        if (mCur.moveToFirst() && mCur.getCount() > 0) {
            boolean cont = true;
            while (mCur.isAfterLast() == false && cont) {
                title = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.TITLE));
                url = mCur.getString(mCur.getColumnIndex(Browser.BookmarkColumns.URL));

                TextView tvTitle = new TextView(this);
                TextView tvUri = new TextView(this);
                tvTitle.setText (title);
                tvUri.setText (url);

                mainLayout.addView (tvTitle);
                mainLayout.addView (tvUri);
                // Do something with title and url
                mCur.moveToNext();
            }
        }
        //tv_title.setText (title);
        //tv_uri.setText (url);

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
