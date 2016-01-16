package com.example.learn;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class linearlayoutActivity extends Activity{
	
	private static linearlayoutActivity appRef = null;
	Button newButton;
	public ProgressDialog pd;
	/**
	 * Thread and Handler usage
	 */
	final Handler handler = new Handler(){
		public void handleMessage(Message msg){
			pd.dismiss();
			Toast.makeText(linearlayoutActivity.this, "Done", Toast.LENGTH_SHORT).show();
		}
	};
	
	Thread newThread = new Thread(){
		public void run(){
			SystemClock.sleep(4000);
			handler.sendEmptyMessage(0);
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.linearlayout_ex);
		clearNotification();
		
	    newButton = (Button)findViewById(R.id.main_newRoute);
	    appRef = this;
	}
	
	@Override
	public void onStart(){
		clearNotification();
		super.onStart();
	}
	
	@Override
	public void onStop(){
		showNotification();
		super.onStop();
	}
	
	private void showNotification() {
		// TODO Auto-generated method stub
		NotificationManager nmgr = (NotificationManager)this.
				getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		Notification nt = new Notification(android.R.drawable.alert_light_frame, "Notification", 
				System.currentTimeMillis());
		nt.flags |= Notification.FLAG_ONGOING_EVENT;
		nt.flags |= Notification.FLAG_NO_CLEAR;
		nt.flags |= Notification.FLAG_SHOW_LIGHTS;
		
		nt.defaults = Notification.DEFAULT_ALL;
		nt.ledARGB = Color.BLUE;
		nt.ledOnMS = 4000;
		
		CharSequence title = "Notification title";
		CharSequence text = "Notifictaion text";
		
		Intent notificationIntent = new Intent(linearlayoutActivity.this, 
				linearlayoutActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		nt.setLatestEventInfo(this, title, text, pIntent);
		nmgr.notify(0, nt);
	}

	private void clearNotification() {
		// TODO Auto-generated method stub
		NotificationManager nmgr = (NotificationManager)this.
				getSystemService(NOTIFICATION_SERVICE);
		nmgr.cancel(0);
		
	}

	public void btnNewRouteClicked(View v){

			// TODO Auto-generated method stub
			Intent backIntent = new Intent();
			//Bundle data = new Bundle();
			backIntent.putExtra("text1", "text1");
			backIntent.putExtra("text2", "text2");
			setResult(20, backIntent);
			finish();
		
	}
	
	public void btnExistingRouteClicked(View v){
		pd = ProgressDialog.show(linearlayoutActivity.this, "Please wait...", "Doing Job...", true);
		newThread.start();
	}

	public void startSerivceClick(View v){
		setTitle("Waiting alarm = 5");
		Intent intent = new Intent(linearlayoutActivity.this, alarmReceiver.class);
		PendingIntent pIntent = PendingIntent.getBroadcast(
				linearlayoutActivity.this, 0, intent, 0);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.SECOND, 5);
		
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
	}
	
	public void stopSerivceClick(View v){
		Intent intent = new Intent(linearlayoutActivity.this, alarmReceiver.class);
		PendingIntent pIntent = PendingIntent.getBroadcast(
				linearlayoutActivity.this, 0, intent, 0);
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(pIntent);
		stopService(new Intent(this, UserService.class));
		//finish();
	}
	

	
	public static linearlayoutActivity getApp(){
		return appRef;	
	}
	
	public void btEvent(String data){
		setTitle(data);
	}
}