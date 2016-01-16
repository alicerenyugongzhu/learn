package com.example.learn;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class DrawTweenActivity extends Activity{
	TweenActivity draw = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		draw = new TweenActivity(this);
		setContentView(draw);
	}
}