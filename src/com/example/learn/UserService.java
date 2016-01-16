package com.example.learn;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class UserService extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate(){
		linearlayoutActivity aService = linearlayoutActivity.getApp();
		aService.btEvent("from UserService: start service");
		Toast.makeText(UserService.this, "In the service", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onDestroy(){
		linearlayoutActivity aService = linearlayoutActivity.getApp();
		aService.btEvent("from UserService: stop service");
		Toast.makeText(UserService.this, "Out of service", Toast.LENGTH_LONG).show();
		super.onDestroy();
	}
}