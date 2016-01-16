package com.example.learn;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraActivity extends Activity{
	private Preview mPreview;
	int count;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        count = 0;
		
		mPreview = new Preview(this);
		setContentView(mPreview);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		Log.d("alice", "I am in the touch event");
		if(count == 0)
		    mPreview.TakePicture();
		count = 1;
		return true;
	}

}

class Preview extends SurfaceView implements SurfaceHolder.Callback{

	SurfaceHolder mSHolder;
	Camera mCamera;
	Bitmap mBitmap;
	
	public Preview(Context context) {
		super(context);
		mSHolder = getHolder();
		mSHolder.addCallback(this);
		mSHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		int width = 320;
		int height = 480;
		Camera.Parameters param = mCamera.getParameters();
		param.setPictureFormat(PixelFormat.JPEG);
		param.setPreviewSize(width, height);
		param.setPictureSize(width, height);
		mCamera.setParameters(param);
		mCamera.startPreview();
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		mCamera = Camera.open();
		try {
			mCamera.setPreviewDisplay(arg0);
		} catch (IOException exception){
			mCamera.release();
			mCamera = null;
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
	}
	
	public void TakePicture(){
		if(mCamera != null){
			//mCamera.stopPreview();
			Log.d("alice", "start tot take picture");
			mCamera.takePicture(null, null, jpeg);
			Log.d("alice", "finish take picture");
		}
	}
	
	private PictureCallback jpeg = new PictureCallback(){

		@Override
		public void onPictureTaken(byte[] arg0, Camera arg1) {
			int quality = 80;
			
			mBitmap = BitmapFactory.decodeByteArray(arg0, 0, arg0.length);
	        Log.d("alice", "I am in the jpeg callback");
			File myPictureFile = new File("*/sdcard/test.jpg");
			try {
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(myPictureFile));
				mBitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
				stream.flush();
				stream.close();
				
				Canvas canvas = mSHolder.lockCanvas();
				canvas.drawBitmap(mBitmap, 0, 0, null);
				mSHolder.unlockCanvasAndPost(canvas);
			} catch (Exception e){
				e.getMessage();
			}
			
			//arg1.startPreview();
		}
		
	};
}


