package com.example.learn;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listViewTest extends Activity{

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		ListView listViewSim = new ListView(this);
		//listViewSim.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData()));
		listViewSim.setAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_expandable_list_item_1, getData()));
		setContentView(listViewSim);
	}
	
	private List<String> getData(){
		List<String> data = new ArrayList<String>();
		data.add("Alice");
		data.add("Alice1");
		data.add("Alice2");
		data.add("Alice3");
		
		return data;
	}
}