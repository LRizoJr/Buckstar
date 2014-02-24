package com.example.helloandroid;

import android.util.Log;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class HelloAndroid extends Activity {	
	private static final String TAG = HelloAndroid.class.getSimpleName();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             
        // turn title OFF
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // go full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our View as the view
        this.setContentView(new HelloAndroidView(this));
        Log.d(this.TAG, "View added");
    }
    
    @Override
    protected void onDestroy() {
    	Log.d(this.TAG, "Destroying...");
    	super.onDestroy();
    }
    
    @Override
    protected void onStop() {
    	Log.d(this.TAG, "Stopping...");
    	super.onStop();
    }
}