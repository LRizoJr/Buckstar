package com.example.testapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity   {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		RelativeLayout layout = (RelativeLayout) this.findViewById(R.id.testLayout);
	
		layout.setOnTouchListener(new View.OnTouchListener() {			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				TestImageView i = (TestImageView) arg0.findViewById(R.id.imageView1);
				TextView t = (TextView) arg0.findViewById(R.id.lblCurrentView);
				t.setText("Layout: " + i.getCurrentImageName());
				t.bringToFront();
				return false;
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
}
