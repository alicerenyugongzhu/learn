package com.example.learn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class tablelayoutActivity extends Activity{
	
	DatePicker datePicker1;
	TimePicker timePicker1;
	EditText ed1;
	EditText ed2;
	
	Button write;
	Button read;
	
	final String FILE = "tbd";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tablelayout_ex);
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int monthOfYear = cal.get(Calendar.MONTH);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		
		datePicker1 = (DatePicker)findViewById(R.id.datePicker1);
		datePicker1.init(year, monthOfYear, dayOfMonth, null);
		
		timePicker1 = (TimePicker)findViewById(R.id.timePicker1);
		timePicker1.setIs24HourView(true);
		
		ed1 = (EditText) findViewById(R.id.uName);
		ed2 = (EditText) findViewById(R.id.password);
		
		write = (Button) findViewById(R.id.write);
		write.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				write(ed1.getText().toString());
				write(ed2.getText().toString());
				ed1.setText("");
				ed2.setText("");
			}
			
		});
		
		read = (Button) findViewById(R.id.read);
		read.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ed1.setText(Readback(1));
				ed2.setText(Readback(2));
			}


			
		})	;	
		
	}
	
	private String Readback(int i) {
		// TODO Auto-generated method stub
		try{
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File sdFile = Environment.getExternalStorageDirectory();
				FileInputStream file = new FileInputStream(sdFile.getCanonicalPath()+FILE);
				BufferedReader reader = new BufferedReader(new InputStreamReader(file));
				String str = null;
			
				while (i-- > 0){
					str = reader.readLine();
				}
				file.close();
			    return str.toString();
			} else {
				FileInputStream file = openFileInput(FILE);
				InputStreamReader sreader = new InputStreamReader(file);
				BufferedReader reader = new BufferedReader(sreader);
				String str = "";
			
				while (i-- > 0){
					str = reader.readLine();
				}
				file.close();
			    return str.toString();
			}
		} catch (IOException e){
			Toast.makeText(tablelayoutActivity.this, "write file failed", Toast.LENGTH_SHORT).
			show();
		}
		return null;
	}
	
	private void write(String string) {
		// TODO Auto-generated method stub
		try{
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				File sdDir = Environment.getExternalStorageDirectory();
				File targetFile = new File(sdDir.getCanonicalFile()+FILE);
				RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
				raf.seek(targetFile.length());
				raf.write(string.getBytes());
				raf.close();
			} else {
				FileOutputStream file = openFileOutput(FILE, MODE_APPEND);
				PrintStream ps = new PrintStream(file);
				ps.println(string);
				ps.close();
			}
		}catch (Exception e){
			Toast.makeText(tablelayoutActivity.this, "write file failed", Toast.LENGTH_SHORT).
			show();
		}
	}
}