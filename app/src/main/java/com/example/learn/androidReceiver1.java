package com.example.learn;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class androidReceiver1 extends BroadcastReceiver {

	Context context;
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		this.context = arg0;
		stopNotification();
	}

	private void stopNotification() {
		// TODO Auto-generated method stub
		NotificationManager manager = (NotificationManager) context.
				getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		manager.cancel(0);  //Use hardcode id here
	}
	
}