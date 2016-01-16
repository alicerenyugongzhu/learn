package com.example.learn;

import android.app.Activity;
import android.os.Bundle;

public class DrawPicture extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		draw mdraw = new draw(this);
		//DrawRipple mdraw = new DrawRipple(this);
		setContentView(mdraw);
	}
}