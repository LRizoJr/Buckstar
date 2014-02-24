package com.example.helloandroid;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class HelloAndroid extends Activity {
	AnimationDrawable walkingAnimation; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);               
        
        ImageView personView = new ImageView(this);       
        
        personView.setScaleType(ScaleType.CENTER_INSIDE);
        LayoutParams p = new LayoutParams
        
        personView.setLayoutParams(p);
        //personView.setImageResource(R.drawable.person1);
        //ImageView personView = (ImageView) this.findViewById(R.drawable.person1);
        personView.setBackgroundResource(R.drawable.person_walking);
        walkingAnimation = (AnimationDrawable) personView.getBackground();
        this.setContentView(personView, personView.getLayoutParams());
    }
    
    public boolean onTouchEvent(MotionEvent event) {
    	  if (event.getAction() == MotionEvent.ACTION_DOWN) {
    	    walkingAnimation.start();
    	    return true;
    	  }
    	  return super.onTouchEvent(event);
    	}
}