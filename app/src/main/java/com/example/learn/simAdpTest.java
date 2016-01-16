package com.example.learn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class simAdpTest extends ListActivity{
	
	OnItemSelectedListener listSel;
	ListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	/*	listSel = new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "I am in ItemSelected: " + arg2, Toast.LENGTH_LONG).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		};*/

		//listView = new ListView(this);
		SimpleAdapter adp = new SimpleAdapter(this, getData(),
				R.layout.sim_adp_layout, new String[]{"name", "info", "listImage", "btnList"},
				new int[]{R.id.name, R.id.info, R.id.listImage, R.id.btnList});
		setListAdapter(adp);
		//listView.setOnItemSelectedListener(listSel);
		
		
		
	}
	
	private List<Map<String, Object>> getData(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "Alice");
		map.put("info", "first");
		map.put("listImage", android.R.drawable.alert_dark_frame);
		map.put("btnList", "confirm");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name", "Cherry");
		map.put("info", "second");
		map.put("listImage", android.R.drawable.alert_light_frame);
		map.put("btnList", "confirm");
		list.add(map);
		
		return list;
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		Toast.makeText(getApplicationContext(), "You click positon" + position, Toast.LENGTH_SHORT).show();
	}
	

	
	
}