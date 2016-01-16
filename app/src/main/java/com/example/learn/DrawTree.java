package com.example.learn;

import android.app.Activity;

public class DrawTree extends Activity{
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TreeActivity drawTree = new TreeActivity(this);
		setContentView(drawTree);
	}
}