/*package com.example.learn;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class weatherActivity extends Activity{
	
	public static final String SERIVER_URL=
	//		"http://www.webservicex.net/WeatherForecast.asmx/GetWeatherByPlaceName";
			"http://www.webservicex.net/GetWeatherByZipCode";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		HttpPost hp = new HttpPost(SERIVER_URL);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//params.add(new BasicNameValuePair("PlaceName", "NewYork"));
		params.add(new BasicNameValuePair("ZipCode", "200120"));
		Log.d("alice", "I am in the get weather");
		
		try{
			hp.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = new DefaultHttpClient().execute(hp);
			Log.d("alice", "I am in the get weather 1");
			if(response.getStatusLine().getStatusCode() != 404){
				String result = EntityUtils.toString(response.getEntity());
				Log.d("alice", "I am in the get weather. And the weather is " + result);
				Toast.makeText(weatherActivity.this, "the weather for SRDC is "
				+ result, Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e){
			Log.d("alice", "I am in the get weather Error");
			e.printStackTrace();
		}
	}
}
*/