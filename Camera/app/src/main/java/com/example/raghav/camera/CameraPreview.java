package com.example.raghav.camera;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by 500038780 on 6/17/2015.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder sHolder;
    private Camera myCam;

    public CameraPreview (Context context, Camera cam) {
        super (context);

        myCam = cam;
        sHolder = getHolder();
        sHolder.addCallback(this);

        sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated (SurfaceHolder holder) {
        try {
            myCam.setPreviewDisplay(holder);
            myCam.startPreview();
        }
        catch (IOException e) {
            //Error handling logic
        }
    }

    public void surfaceDestroyed (SurfaceHolder holder) {
        //nothing
    }

    public void surfaceChanged (SurfaceHolder holder, int format, int width, int height) {
        if (sHolder.getSurface() == null) {
            //Surface for Preview does not exist
            return;
        }

        try {
            myCam.stopPreview();
        }
        catch (Exception e) {
            //Error
        }

        try {
            myCam.setPreviewDisplay(sHolder);
            myCam.startPreview();
        }
        catch (IOException e) {
            //error
        }
    }
}
