package com.example.learn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

public class ShaderActivity extends View implements Runnable {

	Bitmap myBitmap = null;
	int width;
	int height;
	
	Paint myPaint = null;
	
	Shader mBitmapShader = null;
	Shader mLinearGradientShader = null;
	Shader mComposeShader = null;
	Shader mRadialGradientShader = null;
	Shader mSweepGradientShader = null;
	
	ShapeDrawable myShapeDrawable = null;
	
	public ShaderActivity(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		myBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.kula)).getBitmap();
		width = myBitmap.getWidth();
		height = myBitmap.getHeight();
		
		mBitmapShader = new BitmapShader(myBitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
		mLinearGradientShader = new LinearGradient(0, 0, 100, 100, new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.WHITE},
				null, Shader.TileMode.REPEAT);
		mComposeShader = new ComposeShader(mBitmapShader, mLinearGradientShader, PorterDuff.Mode.DARKEN);
		mRadialGradientShader = new RadialGradient(50, 200, 50, new int[]{Color.RED, Color.YELLOW, Color.CYAN, Color.GRAY},
				null, Shader.TileMode.REPEAT);
		mSweepGradientShader = new SweepGradient(30, 30, new int[]{Color.BLUE, Color.RED, Color.GREEN, Color.GRAY}, null);
		
		myPaint = new Paint();
		new Thread(this).start();
	}

	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		myShapeDrawable = new ShapeDrawable(new OvalShape());
		myShapeDrawable.getPaint().setShader(mBitmapShader);
		myShapeDrawable.setBounds(0, 0, width, height);
		myShapeDrawable.draw(canvas);
		
		myPaint.setShader(mLinearGradientShader);
		canvas.drawRect(width, 0, 320, 156, myPaint);
		myPaint.setShader(mComposeShader);
		canvas.drawRect(0, 300, width, 300+height, myPaint);
		myPaint.setShader(mRadialGradientShader);
		canvas.drawCircle(50, 200, 50, myPaint);
		myPaint.setShader(mSweepGradientShader);  
		canvas.drawRect(150, 160, 300, 300, myPaint); 
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.currentThread().isInterrupted()){
			try{
				Thread.sleep(100);
			} catch (InterruptedException e){
				Thread.currentThread().interrupt();
			}
			postInvalidate();
		}
	}
	
}