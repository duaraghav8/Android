package com.example.raghav.camera;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {
    private Camera myCam;
    private CameraPreview myPreview;
    public static int MEDIA_TYPE_IMAGE = 1;
    public static final String FILE_PATH = "com.example.raghav.camera.MESSAGE";

    private Camera.PictureCallback takePicCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File imgFile = new File(path, "temp_image.jpg");

            try {
                OutputStream os = new FileOutputStream(imgFile);
                os.write (data);
                os.close ();
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show ();
            }
            catch (IOException ioe) {
                Toast.makeText(getApplicationContext(), "Error Creating Media File", Toast.LENGTH_SHORT).show ();
            }

            Intent intent = new Intent(getApplicationContext (), MainActivity2.class);
            intent.putExtra(FILE_PATH, imgFile.getPath().toString());
            startActivity (intent);
        }
    };

    public static Camera getCameraInstance () {
        Camera temp = null;
        try {
            temp = Camera.open();
        }
        catch (Exception e) {
            //error
        }

        return (temp);
    }

    public void click (View v) {
        myCam.takePicture(null, null, takePicCallback);
    }

    @Override
    protected void onPause () {
        if (myCam != null) {
            myCam.release();
            myCam = null;
        }
        super.onPause ();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide ();
        //getActionBar ().hide ();

        myCam = getCameraInstance ();
        myPreview = new CameraPreview(this, myCam);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);

        preview.addView (myPreview);
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
