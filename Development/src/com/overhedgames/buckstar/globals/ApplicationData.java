package com.overhedgames.buckstar.globals;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public final class ApplicationData extends Application {
	public String TAG = ApplicationData.class.getSimpleName();
	
	private static Context context;
	private int screenWidth = 0;
	private int screenHeight = 0;
	
	public ApplicationData() {		
	}
	
	public void onCreate() {
		super.onCreate();
		ApplicationData.context = getApplicationContext(); 
	}
	
	private void init() {
		try {
			WindowManager wm = (WindowManager) this.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			
			if(android.os.Build.VERSION.SDK_INT >= 13) {				
				Point outSize = new Point();
				display.getSize(outSize);
				
				this.screenHeight = outSize.y;
				this.screenWidth = outSize.x;						
			} else {				
				this.screenHeight = display.getHeight();  // deprecated
				this.screenWidth = display.getWidth();  // deprecated				
			}
		}catch(Exception ex) {
			Log.d(this.TAG, "Exception caught in init:" + ex.toString());
		}
	}
	
	public int getScreenWidth() {
		if(this.screenWidth == 0) {
			init();
		}
		return this.screenWidth;
	}
	
	public int getScreenHeight() {
		if(this.screenHeight == 0) {
			init();
		}
		return this.screenHeight;
	}

}
