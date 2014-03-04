package com.overhedgames.buckstar.globals;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public final class ApplicationData {
	public String TAG = ApplicationData.class.getSimpleName();
	
	private Context context;
	
	private int screenWidth;
	private int screenHeight;
	
	public ApplicationData(Context context) {
		init();
	}
	
	private void init() {
		try {
			WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			
			if(android.os.Build.VERSION.SDK_INT >= 13) {				
				Point outSize = new Point();
				display.getSize(outSize);
				
				this.screenHeight = outSize.x;
				this.screenWidth = outSize.y;						
			} else {				
				this.screenHeight = display.getHeight();  // deprecated
				this.screenWidth = display.getWidth();  // deprecated				
			}
		}catch(Exception ex) {
			Log.d(this.TAG, "Exception caught in init:" + ex.toString());
		}
	}
	
	public int getScreenWidth() { 
		return this.screenWidth;
	}
	
	public int getScreenHeight() { 
		return this.screenHeight;
	}

}
