package com.example.testapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class TestImageView extends ImageView {

	private int currentLayout = 0;
	private int[] testLayouts = new int[] { R.drawable.scene1920x1080_01, R.drawable.scene1920x1080_03, R.drawable.scene1920x1200_03 };
	private String[] testLayoutNames = new String[] { "scene1920x1080_01", "scene1920x1080_03", "scene1920x1200_03" };
	
	public TestImageView(Context context, AttributeSet attrs)  {
		super(context, attrs);
		this.init();		 
		// TODO Auto-generated constructor stub */
	}
	
	public TestImageView(Context context, AttributeSet attrs, int defStyle)  {
		super(context, attrs, defStyle);
		this.init();
		// TODO Auto-generated constructor stub */
	}	
	
	private boolean init() {
		this.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						if(arg1.getAction() == MotionEvent.ACTION_DOWN) {			
							ImageView v = (ImageView) arg0;
							currentLayout = (currentLayout + 1) % testLayouts.length;
							v.setImageResource(testLayouts[currentLayout]);							
						}				
						return false;
					}
				}); 
				
				
	    this.setImageResource(testLayouts[currentLayout]);	  
		return true;	
	}
	
	public String getCurrentImageName() {
		return this.testLayoutNames[currentLayout];
	}
}
