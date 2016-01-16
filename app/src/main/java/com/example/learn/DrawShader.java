package com.example.learn;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class DrawShader extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	
		requestWindowFeature(Window.FEATURE_NO_TITLE); //No title
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //turn the screen orientation
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		ShaderActivity mShader = new ShaderActivity(this);
		setContentView(mShader);

	}
}