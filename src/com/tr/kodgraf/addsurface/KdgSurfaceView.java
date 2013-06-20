/*
Kodgraf Game Studio - ADDSurface 
Created Date: 15 Haz 2013 - 13:47:42
Author: berkanuslu (c) 2013
Androd Developer Days 2013 - www.androiddeveloperdays.com
*/
package com.tr.kodgraf.addsurface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class KdgSurfaceView extends SurfaceView implements Runnable {

	Thread myThread;
	SurfaceHolder myHolder;
	boolean isRunning = true;
	Bitmap kdgLogo;
	Bitmap sheep;
	float x, y, startX, startY, stopX, stopY = 0;
	
	
	public KdgSurfaceView(Context context) {
		super(context);
		
		//get SurfaceHolder object from SurfaceView getHolder method
		myHolder = getHolder();
		//get bitmap images from resources
		kdgLogo = BitmapFactory.decodeResource(getResources(), R.drawable.kodgraf_logo);
		sheep = BitmapFactory.decodeResource(getResources(), R.drawable.sheep_0);
	}

	@Override
	public void run() {
		while(isRunning) {
			if(!myHolder.getSurface().isValid()) {
				continue;
			}
			
			//get canvas from SurfaceView's SurfaceHolder
			Canvas canvas = myHolder.lockCanvas();
			
			//draw background color
			canvas.drawRGB(0, 0, 150);
			
			if( x != 0 && y != 0) {
				//draw bitmap in touched x and y coordinate from center of bitmap
				canvas.drawBitmap(kdgLogo, x - (kdgLogo.getWidth()/2), y - (kdgLogo.getHeight()/2), null);
			}
			
			if( startX != 0 && startY != 0) {
				canvas.drawBitmap(sheep, startX - (sheep.getWidth()/2), startY - (sheep.getHeight()/2), null);
			}
			
			if( stopX != 0 && stopY != 0) {
				canvas.drawBitmap(sheep, stopX - (sheep.getWidth()/2), stopY - (sheep.getHeight()/2), null);
			}
			
			if( startX != 0 && startY != 0 && stopX != 0 && stopY != 0) {
				Paint paint = new Paint();
				paint.setColor(Color.WHITE);
				canvas.drawLine(startX, startY, stopX, stopY, paint);
			}
			
			//Post canvas changes
			myHolder.unlockCanvasAndPost(canvas);
		}
	}
	
	public void pause() {
		isRunning = false;
		while(true) {
			try {
				myThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			break;
		}
		myThread = null;
	}
	
	public void resume() {
		isRunning = true;
		//create and start new thread 
		myThread = new Thread(this);
		myThread.start();
	}

	
}
