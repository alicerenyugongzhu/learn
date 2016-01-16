package com.example.learn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public class draw extends View implements Runnable{

	private Paint mPaint = null;
	Bitmap myBitmap = null;
	
	float angle = 0.0f;
	float scale = 0.0f;
	Matrix myMatrix;
	
	public draw(Context context) {
		super(context);
		mPaint = new Paint();
		myMatrix = new Matrix();
		myBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.kula)).getBitmap();
		
		new Thread(this).start();
	}

	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		canvas.drawColor(Color.BLACK);
		
	    draw.drawImage(canvas, myBitmap, 105, 0);
	    //draw.drawImage(canvas, myBitmap, 0, myBitmap.getHeight(), myBitmap.getWidth(), myBitmap.getHeight()/2, 0, 0);
	    
	    angle = angle + 5;
	    scale = (float) (scale + 0.5);
	    myMatrix.reset();
	    myMatrix.setRotate(angle);
	    myMatrix.postScale(scale, scale);
	    
	    Bitmap myBitmap2 = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), myMatrix, true);
	    draw.drawImage(canvas, myBitmap2, 0, 350);
	    myBitmap2 = null;
	    		
		mPaint.setAntiAlias(true); /*disable ¾â³Ý*/
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setAlpha(200);
		{
			Rect rect1 = new Rect();
			rect1.left = 5;
			rect1.top = 5;
			rect1.bottom = 25;
			rect1.right = 25;
			mPaint.setColor(Color.BLUE);
			canvas.drawRect(rect1, mPaint);
			
			mPaint.setColor(Color.RED);
			canvas.drawRect(50,  5, 90, 25, mPaint); /* left, top, right, bottom*/
			
			mPaint.setColor(Color.YELLOW);
			mPaint.setStyle(Paint.Style.FILL);
			canvas.drawCircle(40, 70, 30, mPaint); /*left, top, size*/
			
			RectF rectf1 = new RectF();
			rectf1.left=70;
			rectf1.top = 30;
			rectf1.bottom = 120;
			rectf1.right = 80;
			mPaint.setColor(Color.LTGRAY);
			canvas.drawOval(rectf1, mPaint);
			
			Path path1 = new Path();
			path1.moveTo(50+5, 80-50);
			path1.lineTo(50+45, 80-50);
			path1.lineTo(50+30, 120-50);
			path1.lineTo(50+20, 120-50);
			path1.close();
			mPaint.setColor(Color.GRAY);
			canvas.drawPath(path1, mPaint);
			
			mPaint.setColor(Color.RED);
			mPaint.setStrokeWidth(10);
			canvas.drawLine(5, 110, 315, 150, mPaint);
			
		}
		
		//Font print
		mPaint.setTypeface(Typeface.SANS_SERIF);
		mPaint.setUnderlineText(true);
		mPaint.setTextSize(20);
		canvas.drawText("I am Alice", 100, 320, mPaint);
		
	}
	
	private static void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(bitmap, x, y, null);
	}

	private static void drawImage(Canvas canvas, Bitmap bitmap, int x, int y,
			int w, int h, int bx, int by) {
		Rect src = new Rect();
		Rect des = new Rect();
		
		src.left = bx;
		src.top = by;
		src.right = bx+w;
		src.bottom=by+h;
		
		des.left = x;
		des.top = y;
		des.right = x + w;
		des.bottom = y + h;
		
		canvas.drawBitmap(bitmap, src, des, null);
		src=null;
		des=null;
		
	}

	@Override
    public void run(){
    	while(!Thread.currentThread().isInterrupted()){
    		try{
    			Thread.sleep(2000);
    		} catch (InterruptedException e){
    			Thread.currentThread().interrupt();
    		}
    		postInvalidate();
    	}
    }
    
}