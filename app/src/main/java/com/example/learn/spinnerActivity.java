package com.example.learn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class spinnerActivity extends Activity implements OnItemSelectedListener{
	
	private static final String[] countries = {"default", "SimpleListView", "SimpleCursorAdapter",
		"SimpleAdapter", "myAdapter"};
	Spinner spin0;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_ex);
		findAndModify();
		spin0.setOnItemSelectedListener(this);
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		setTitle(countries[arg2]);
		switch (arg2) {
			case 1:
				Intent intent1 = new Intent(spinnerActivity.this, listViewTest.class);
				startActivity(intent1);
			break;
			case 2:
				Intent intent2 = new Intent(spinnerActivity.this, simCursorAdp.class);
				startActivity(intent2);	
			break;
			case 3:
				Intent intent3 = new Intent(spinnerActivity.this, simAdpTest.class);
				startActivity(intent3);
			break;
			case 4:
				Intent intent4 = new Intent(spinnerActivity.this, myAdpTest.class);
				startActivity(intent4);		
			default:
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void findAndModify(){
		spin0 = (Spinner)findViewById(R.id.spiner1);
		ArrayAdapter<String> spCountries = new ArrayAdapter<String>(this, 
				android.R.layout.simple_dropdown_item_1line, countries);
		spin0.setAdapter(spCountries);
	
	}
	
}