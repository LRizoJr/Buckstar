package com.overhedgames.buckstar;

import com.overhedgames.buckstar.parameters.Parameters_Facility;

import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.os.Bundle;

public class BuckStarActivity extends Activity {
	private static final String TAG = BuckStarActivity.class.getSimpleName();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        Log.d(this.TAG, "test");
        this.setContentView(new GameplayView(this));
        init();
        Log.d(this.TAG, "View added");
    }
    
    @Override
    protected void onDestroy() {
    	Log.d(BuckStarActivity.TAG, "Destroying...");
    	super.onDestroy();
    }
    
    @Override
    protected void onStop() {
    	Log.d(BuckStarActivity.TAG, "Stopping...");
    	super.onStop();
    }
    
    private void init() {
    
    	Parameters_Facility.Context = this;
    }
}