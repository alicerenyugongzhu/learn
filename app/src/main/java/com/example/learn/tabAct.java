package com.example.learn;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class tabAct extends TabActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.tab_main);
		
		TabHost tabhost = getTabHost();
		//tabhost.setup();
		LayoutInflater.from(this).inflate(R.layout.tab_main,tabhost.getTabContentView(), true);
		tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("tab1").setContent(R.id.textTab1));
		tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("tab2").setContent(R.id.textTab2));
		tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("tab3").setContent(R.id.textTab3));
	}
}