package com.example.learn;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DataTime extends Fragment{
	private String time;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		if(null == time){
			time = new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(new Date());
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,
			Bundle b){
		View view = inflater.inflate(R.layout.right_fragment, container, false);
		TextView text = (TextView)view.findViewById(R.id.right_show_message);
		text.setText(time);
		return view;
	}
}