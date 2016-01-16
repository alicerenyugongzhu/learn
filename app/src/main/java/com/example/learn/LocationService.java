package com.example.learn;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class LocationService extends Activity{
	
	LocationManager lcMgr;
	final LocationListener locationListener = new LocationListener(){

		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub
			UpdateWithNewLocation(arg0);
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			UpdateWithNewLocation(null);
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	/**
	 * Add popUpWindow
	 */
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.popup_window, null);
		final PopupWindow pop = new PopupWindow(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
		//pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setOutsideTouchable(true);
		pop.setFocusable(true);
		//TODO need investigate 
		//pop.showAsDropDown(view);
		Button btn = (Button)findViewById(R.id.btnPopUp);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(pop.isShowing()){
					pop.dismiss();
				} else {
					pop.showAsDropDown(arg0);
					pop.showAtLocation(findViewById(R.id.btnPopUp), Gravity.CENTER, 20, 20);
					String myString = "tell Alice no data return";
					/*try {
						URL url = new URL("http://alice0310.blog.sohu.com/307397942.html"); //TODO: need change the link
						try {
							HttpURLConnection urlcnt = (HttpURLConnection)url.openConnection();
							int nRs = urlcnt.getResponseCode();
							if(nRs == HttpURLConnection.HTTP_OK){
							  InputStream is = urlcnt.getInputStream();
							  BufferedInputStream bis = new BufferedInputStream(is);
							  ByteArrayBuffer buffer = new ByteArrayBuffer(100);
							  int current = 0;
							  while((current = bis.read()) != -1){
								buffer.append((byte) current);
							  }
							
							  myString = new String(buffer.toByteArray());
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					//TextView net = (TextView)findViewById(R.id.netInfo);
					//myString = myString.substring(0, 5);
					Log.d("alice", "net page is " + myString);
					//TODO need debug here
					//net.setText(myString);
				}
			}
			
		});
		
		lcMgr = (LocationManager)
				getSystemService(Context.LOCATION_SERVICE);
		/*
		//Set the requirement for the service provider
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		//criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);*/
		List<String> availableProvider = lcMgr.getAllProviders();
		String t = "";
		for (int i = 0; i < availableProvider.size(); i++){
			t += availableProvider.get(i) + "\n";
		}
		Toast.makeText(LocationService.this, t, Toast.LENGTH_SHORT).show();
		//String provider = (String)lcMgr.getBestProvider(criteria, true);
		//String provider = LocationManager.NETWORK_PROVIDER;
		String provider = availableProvider.get(0).toString();
		
		if(lcMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
			Toast.makeText(LocationService.this, "network serivce enabled", Toast.LENGTH_SHORT).show();
			lcMgr.setTestProviderEnabled(LocationManager.NETWORK_PROVIDER, true);
		}
		
		Location lc = lcMgr.getLastKnownLocation(provider);
			
		if(lc == null){
			Toast.makeText(LocationService.this, "network service is not available", Toast.LENGTH_SHORT).show();
			lc = lcMgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if(lc == null ){
				Toast.makeText(LocationService.this, "gps service is not available", Toast.LENGTH_SHORT).show();
			}
		}
		
		UpdateWithNewLocation(lc);
		lcMgr.requestLocationUpdates(provider, 1000, 0, locationListener);
		
	}
	
	@Override
	public void onPause(){
		super.onPause();
		lcMgr.removeUpdates(locationListener);
	}

	private void UpdateWithNewLocation(Location lc) {
		// TODO Auto-generated method stub
		String latest, addrString;
		TextView myLocation = (TextView)findViewById(R.id.locate_text);
		TextView myAddr = (TextView)findViewById(R.id.addr_text);
		
		if(lc != null){
			double latitude = lc.getLatitude();
			double longitude = lc.getLongitude();
			latest = "latitude: " + latitude + "longitude: " + longitude;
			Geocoder gcoder = new Geocoder(this, Locale.getDefault());
			try{
				int maxResult = 2;  //the number you want to return
				List<Address> addresses = gcoder.getFromLocation(latitude, longitude, maxResult);
				StringBuilder sb = new StringBuilder();
				
				if(addresses.size() > 0){
					Address addr = addresses.get(0);				
				    for(int i = 0; i < addr.getMaxAddressLineIndex(); i++){
				    	sb.append(addr.getAddressLine(i)).append("\n");
				    	sb.append(addr.getLocality()).append("\n");
				    	sb.append(addr.getPostalCode()).append("\n");
				    	sb.append(addr.getCountryName()).append("\n");
				    }
				    
				    addrString = sb.toString();
				    myAddr.setText(addrString);
				}
			} catch (Exception e){
				
			}
		} else {
			latest = "NA";
		}
		myLocation.setText(latest);
		
		
	}
}