package com.example.learn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

public class TweenActivity extends View implements OnGestureListener, OnDoubleTapListener{

	private Animation alpha = null;
	private Animation scale = null;
	private Animation translate = null;
	private Animation rotate = null;
	
	private GestureDetectorCompat mDetector;
	
	Bitmap myBitmap = null;
	int step = 0;
	float x;
	float y;
	
	public TweenActivity(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		myBitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.kula)).getBitmap();
		x = 0;
		y = 0;
		//It seems the GestureDetector doesn't suite to be here.
        //mDetector = new GestureDetectorCompat(this, null);
	}

	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawBitmap(myBitmap, x, y, null);

		
	}
	
	public void alphaAnimation(){

		alpha = new AlphaAnimation(0.1f, 1.0f);
		alpha.setDuration(3000);
		this.startAnimation(alpha);
		
	}
	
	public void scaleAnimation(){
		scale = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f, Animation.RELATIVE_TO_SELF,0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scale.setDuration(500);
		this.startAnimation(scale);
	}
	
	public void translateAnimation(){
		translate = new TranslateAnimation(10,100,10,100);
		translate.setDuration(1000);
		this.startAnimation(translate);
	}
	
	public void rotateAnimation(){
		rotate = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(1000);
		this.startAnimation(rotate);
	}
/*	
    @Override
	public boolean onTouchEvent(MotionEvent event){

			if(event.getAction() == MotionEvent.ACTION_DOWN){
				switch (step) {
				case 0:
					Log.d("alice", " I am in the alpha");
					alphaAnimation();
					break;
				case 1:
					Log.d("alice", "I am in the scale");
					scaleAnimation();
					break;
				case 2:
					Log.d("alice", "I am in the translate");
					translateAnimation();
					break;
				case 3:
					Log.d("alice", "I am in the rotate");
					rotateAnimation();
				default:
					break;
				}
				step=step+1;
				if(step>3) step = 0;
			}
	    return true;
	}
*/    
    @Override
    public boolean onTrackballEvent(MotionEvent event){
    	x = event.getX();
    	y = event.getY();
    	Log.d("alice", "x is " + x + " y is " + y);
    	invalidate();
		return false;
    	
    }


	@Override
	public boolean onDown(MotionEvent e){
		Log.d("alice", " I am in the alpha");
		alphaAnimation();
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		Log.d("alice", "I am in the scale");
		scaleAnimation();
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		Log.d("alice", "I am in the translate");
		translateAnimation();
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		Log.d("alice", "I am in the translate");
		translateAnimation();
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		//Toast.makeText(TweenActivity.this, "I am in the onShowPress", Toast.LENGTH_SHORT).show();
		Log.d("alice", "I am in the onShowPress");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		Log.d("alice", "I am in the onSingleTapUp");
		return false;
	}

	@Override
	public boolean onDoubleTap(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
