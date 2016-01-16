package com.example.learn;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String PACKAGE_NAME = "com.example.learn";
	private static final String VERSION_KEY = "com.example.learn";
	private static final String DATABASE_PATH = "/data/data/com.example.learn";
	private static final String DATABASE_NAME = "test.db";
	
	final int MENU1 = 0x111;
	final int MENU2 = 0x222;
	final int MENU3 = 0x333;

	View.OnClickListener listener0 = null;
	View.OnClickListener listener1 = null;
	View.OnClickListener listener2 = null;
	View.OnClickListener listener3 = null;
	View.OnClickListener listener4 = null;
	View.OnClickListener listener5 = null;
	View.OnClickListener listener6 = null;
	View.OnClickListener listener7 = null;
	View.OnClickListener listener8 = null;
	View.OnClickListener listener9 = null;
	View.OnClickListener listener10 = null;
	View.OnClickListener listener11 = null;
	View.OnClickListener listener12 = null;
	View.OnClickListener listener13 = null;
	View.OnClickListener listener14 = null;
	View.OnClickListener listener15 = null;
	View.OnClickListener listener16 = null;
	View.OnClickListener listener17 = null;
	View.OnClickListener listener18 = null;
	View.OnClickListener listener19 = null;
	View.OnClickListener listener20 = null;
	View.OnClickListener listener21 = null;
	View.OnClickListener listener22 = null;
	View.OnClickListener listener23 = null;
	View.OnClickListener listener24 = null;
	View.OnClickListener listener25 = null;
	
	Button button0;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;
	Button button8;
	Button button9;
	Button button10;
	Button button11;
	Button button12;
	Button button13;
	Button button14;
	Button button15;
	Button button16;
	Button button17;
	Button button18;
	Button button19;
	Button button20;
	Button button21;
	Button button22;
	Button button23;
	Button button24;
	Button button25;
	
	EditText editView;
	
	static final String[] COLORS = {"red", "green", "block"};
	final int REQUEST_0 = 0;
	final int REQUEST_1 = 1;
	final int RECORDING = 1;
	TextView screenInfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Show info about the phone's screen
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenInfo = (TextView)findViewById(R.id.screen_info);
		registerForContextMenu(screenInfo);
		screenInfo.setText("Width = " + dm.widthPixels + "\nheight = " + dm.heightPixels);
		
		//Version checking
		try {
			VersionChecking();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Configuration
		ConfigurationChecking();
		
		//Preference sharing
		InitUserInfo();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COLORS);
		AutoCompleteTextView autoComp = (AutoCompleteTextView)findViewById(R.id.autoCplt);
		autoComp.setAdapter(adapter);
		
		listener0 = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent0 = new Intent(MainActivity.this, linearlayoutActivity.class);
				setTitle("Linear Layout");
				startActivityForResult(intent0, REQUEST_0);
				
			}
		};
		
		listener1 = new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(MainActivity.this, checkboxActivity.class);
				Bundle newBundle = new Bundle();
				editView = (EditText)findViewById(R.id.btn1Edit);
				SharedPreferences saveInfo = getSharedPreferences("userInfo", 0);
				saveInfo.edit().putString("user info", editView.getText().toString()).commit();
				newBundle.putString("input",editView.getText().toString());
				editView.setText("");
				intent1.putExtras(newBundle);
				setTitle("check box");
				startActivity(intent1);
			}
		};
		
		listener2 = new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(MainActivity.this, spinnerActivity.class);
				setTitle("spinner");
				startActivity(intent2);
				
			}
		};
		
		listener3 = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent3 = new Intent(MainActivity.this, tablelayoutActivity.class);
				setTitle("table Layout");
				startActivity(intent3);
			}
		};
		
		listener4 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent4 = new Intent(MainActivity.this, gallery.class);
				setTitle("gallery");
				startActivity(intent4);
			}
		};
		
		listener5 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent5 = new Intent(MainActivity.this, tabAct.class);
				setTitle("Tab");
				startActivity(intent5);
			}
		};
		
		listener6 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent6 = new Intent(MainActivity.this, databaseActivity.class);
				startActivity(intent6);
			}
		};
		
		listener7 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//	Intent intent7 = new Intent(MainActivity.this, weatherActivity.class);
			//	startActivity(intent7);
			}
		};
		
		listener8 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent8 = new Intent(MainActivity.this, LocationService.class);
				startActivity(intent8);
			}
		};
		
        listener9 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent9 = new Intent(MainActivity.this, CameraActivity.class);
				startActivity(intent9);
			}
		};
		
        listener10 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent10 = new Intent(Intent.ACTION_GET_CONTENT);
				intent10.setType("image/*");
				intent10.addCategory(Intent.CATEGORY_OPENABLE);
				try{
					startActivityForResult(Intent.createChooser(intent10, "select a file"), REQUEST_1);
				} catch (android.content.ActivityNotFoundException ex){
					Toast.makeText(MainActivity.this, "not found file manager", Toast.LENGTH_LONG).show();
				}
			}
		};
		
		listener11 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent11 = new Intent(MainActivity.this, fragment_activity.class);
				startActivity(intent11);
			}
		};
		
		listener12 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent12 = new Intent(MainActivity.this, DrawPicture.class);
				startActivity(intent12);
			}
		};
		
        listener13 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent13 = new Intent(MainActivity.this, DrawShader.class);
				startActivity(intent13);
			}
		};
		
		listener14 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent14 = new Intent(MainActivity.this, DrawTweenActivity.class);
				startActivity(intent14);
			}
		};
		
        listener15 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent15 = new Intent(MainActivity.this, DrawGif.class);
				startActivity(intent15);
			}
		};
		
        listener16 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent16 = new Intent(MainActivity.this, playSound.class);
				startActivity(intent16);
			}
		};
		
        listener17 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent17 = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
				startActivityForResult(intent17, RECORDING);
			}
		};
		
        listener18 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent18 = new Intent(MainActivity.this, VedioActivity.class);
				startActivity(intent18);
			}
		};
		
        listener19 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent19 = new Intent(MainActivity.this, fragment2Activity.class);
				startActivity(intent19);
			}
		};
		
		listener20 = new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent20 = new Intent(MainActivity.this, DrawerActivity.class);
					startActivity(intent20);
				}
			};
			
         listener21 = new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent21 = new Intent(MainActivity.this, SlidingDrawActivity.class);
					startActivity(intent21);
				}
			};
			
        listener22 = new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent22 = new Intent(MainActivity.this, bubbleActivity.class);
					startActivity(intent22);
				}
	    };
	    
	    listener23 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent23 = new Intent(MainActivity.this, DrawTree.class);
				startActivity(intent23);
				
			}
		};
		
        listener24 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent24 = new Intent(MainActivity.this, ExpendedTest.class);
				startActivity(intent24);
				
			}
		};
		
		listener25 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent25 = new Intent(MainActivity.this, PinBallActivity.class);
				startActivity(intent25);
				
			}
		};
			
		
		button0 = (Button)findViewById(R.id.button0);
		button0.setOnClickListener(listener0);
		
		button1 = (Button)findViewById(R.id.button1);
		button1.setOnClickListener(listener1);
		
		button2 = (Button)findViewById(R.id.button2);
		button2.setOnClickListener(listener2);
		
		button3 = (Button)findViewById(R.id.button3);
		button3.setOnClickListener(listener3);
		
		button4 = (Button)findViewById(R.id.button4);
		button4.setOnClickListener(listener4);
		
		button5 = (Button)findViewById(R.id.button5);
		button5.setOnClickListener(listener5);
		
		button6 = (Button)findViewById(R.id.button6);
		button6.setOnClickListener(listener6);
		
		button7 = (Button)findViewById(R.id.button7);
		button7.setOnClickListener(listener7);
		
		button8 = (Button)findViewById(R.id.button8);
		button8.setOnClickListener(listener8);
		
		button9 = (Button)findViewById(R.id.button9);
		button9.setOnClickListener(listener9);
		
		button10 = (Button)findViewById(R.id.button10);
		button10.setOnClickListener(listener10);
		
		button11 = (Button)findViewById(R.id.button11);
		button11.setOnClickListener(listener11);
		
		button12 = (Button)findViewById(R.id.button12);
		button12.setOnClickListener(listener12);
		
		button13 = (Button)findViewById(R.id.button13);
		button13.setOnClickListener(listener13);
		
		button14 = (Button)findViewById(R.id.button14);
		button14.setOnClickListener(listener14);
		
		button15 = (Button)findViewById(R.id.button15);
		button15.setOnClickListener(listener15);
		
		button16 = (Button)findViewById(R.id.button16);
		button16.setOnClickListener(listener16);
		
		button17 = (Button)findViewById(R.id.button17);
		button17.setOnClickListener(listener17);
		
		button18 = (Button)findViewById(R.id.button18);
		button18.setOnClickListener(listener18);
		
		button19 = (Button)findViewById(R.id.button19);
		button19.setOnClickListener(listener19);
		
		button20 = (Button)findViewById(R.id.button20);
		button20.setOnClickListener(listener20);
		
		button21 = (Button)findViewById(R.id.button21);
		button21.setOnClickListener(listener21);
		
		button22 = (Button)findViewById(R.id.button22);
		button22.setOnClickListener(listener22);
		
		button23 = (Button)findViewById(R.id.button23);
		button23.setOnClickListener(listener23);
		
		button24 = (Button)findViewById(R.id.button24);
		button24.setOnClickListener(listener24);
		
		button25 = (Button)findViewById(R.id.button25);
		button25.setOnClickListener(listener25);
	}
	
	

	private void ConfigurationChecking() {
		// TODO Auto-generated method stub
		Configuration cfg = getResources().getConfiguration();
		String screen = cfg.orientation == Configuration.ORIENTATION_LANDSCAPE ? "horizontal": "vertical";
		String navi = cfg.navigation == Configuration.NAVIGATION_NONAV ? "no navigation" : 
			cfg.navigation == Configuration.NAVIGATION_WHEEL ? "wheel navigation" :
				cfg.navigation == Configuration.NAVIGATION_DPAD ? "direction navigation" :
					"trail ball navigation";
	}



	private void VersionChecking() throws NameNotFoundException {
		// TODO Auto-generated method stub
		PackageInfo info = getPackageManager().getPackageInfo(PACKAGE_NAME, 0);
		int packageVersion = info.versionCode;
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		int lastVersion = pref.getInt(VERSION_KEY, 0);
		Log.d("alice", "the current version is " + packageVersion);
		Log.d("alice", "the previous version is " + lastVersion);
		if(packageVersion > lastVersion){
			InitDatabase();
		}
	}



	private void InitDatabase() {
		// TODO Auto-generated method stub
		
	}



	private void InitUserInfo() {
		// TODO Auto-generated method stub
		editView = (EditText)findViewById(R.id.btn1Edit);
		SharedPreferences saveInfo = getSharedPreferences("userInfo",0);
		String text = saveInfo.getString("user info", "");
		editView.setText(text);
	}

	static final String BROADCAST_ACTION = "com.example.learn.BROADCAST_1";
	//
    @Override
    public void onStop(){
    	super.onStop();
    	Intent stopNf = new Intent(BROADCAST_ACTION);
    	sendBroadcast(stopNf);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View source, ContextMenu.ContextMenuInfo menuInfo){

		menu.add(0, MENU1 , 0, "RED");
		menu.add(0, MENU2, 0, "GREEN");
		menu.add(0, MENU3, 0, "GRAY");
		menu.setGroupCheckable(0, true, true);
		menu.setHeaderIcon(android.R.drawable.btn_star);
		menu.setHeaderTitle("choose background color");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem it){
		switch(it.getItemId()){
		case MENU1:
			it.setChecked(true);
			screenInfo.setBackgroundColor(Color.RED);
			break;
		case MENU2:
			it.setChecked(true);
			screenInfo.setBackgroundColor(Color.GREEN);
			break;
		case MENU3:
			it.setChecked(true);
			screenInfo.setBackgroundColor(Color.GRAY);
			break;
		}
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestcode, int resultCode, Intent intent){
		if(requestcode == REQUEST_0){
			Bundle value = intent.getExtras();
			TextView text = (TextView) findViewById(R.id.btn0Text);
			text.setText(value.getString("text1"));
		} else if(requestcode == REQUEST_1){
			if(resultCode == RESULT_OK){
				Uri uri = intent.getData();
				String path = FileUtils.getPath(this, uri);
				Toast.makeText(MainActivity.this, "path is " + path, Toast.LENGTH_LONG).show();
			}
		} else if(requestcode == RECORDING){
			if(resultCode == RESULT_OK){
				
				Uri audioUri = intent.getData();
				//playAudio(audioUri);
				Intent intentPlay = new Intent();
				intentPlay.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intentPlay.setAction(android.content.Intent.ACTION_VIEW);
				intentPlay.setDataAndType(audioUri, "audio");
				startActivity(intentPlay);
				
			}
		}
		
		super.onActivityResult(requestcode, resultCode, intent);
	}
	
	public static class FileUtils{

		public static String getPath(Context context, Uri uri) {
			// TODO Auto-generated method stub
			if("content".equalsIgnoreCase(uri.getScheme())){
				String[] projection = {"_data"};
				Cursor cursor = null;
			
				try{
					cursor = context.getContentResolver().query(uri, projection, null, null, null);
					int column_index = cursor.getColumnIndexOrThrow("_data");
					if(cursor.moveToFirst()){
						return cursor.getString(column_index);
					}
				} catch (Exception e){
					
				}
			}
			else if("file".equalsIgnoreCase(uri.getScheme())){
				return uri.getPath();
			}
			
			return null;
		}
		
	}

}

