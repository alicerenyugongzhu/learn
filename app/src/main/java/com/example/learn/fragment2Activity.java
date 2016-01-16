package com.example.learn;
/**
 * The fragment doesn't work. Need think other way.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class fragment2Activity extends FragmentActivity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment2);
	}
}

