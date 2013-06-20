/*
Kodgraf Game Studio - ADDSurface 
Created Date: 15 Haz 2013 - 13:45:00
Author: berkanuslu (c) 2013
Androd Developer Days 2013 - www.androiddeveloperdays.com
*/
package com.tr.kodgraf.addsurface;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	KdgSurfaceView mySurface;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//set application no title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//set fullscreen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//set application orientation
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		mySurface = new KdgSurfaceView(this);
		
		//onTouchListener receive touch event
		mySurface.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				//MotionEvent class describes touch information
				mySurface.x = event.getX(); //touch x coordinate
				mySurface.y = event.getY(); //touch y coordinate
				
				//getAction() method describes touch action such as ACTION_DOWN, ACTION_UP, ACTION_MOVE etc.
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
						mySurface.startX = event.getX(); //get action down x coordinate
						mySurface.startY = event.getY(); //get action down y coordinate
					break;
				case MotionEvent.ACTION_UP:
						mySurface.stopX = event.getX(); //get action up x coordinate
						mySurface.stopY = event.getY(); //get action up y coordinate
						break;
				default:
					break;
				}
				
				return true;  //true = get constantly touch event
			}
		});
		
		setContentView(mySurface); //set Activity content from SurfaceView
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		mySurface.pause(); //pause SurfaceView and stop Thread when Activity is paused.
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		mySurface.resume(); //resume (or start for first use) SurfaceView and start Thread when Activity is resumed.
		super.onResume();
	}

}
