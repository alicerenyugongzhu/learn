package com.example.learn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

public class gallery extends Activity implements ViewFactory, OnTouchListener, OnItemSelectedListener{
	
	ImageSwitcher image0;
	
	Gallery gallery0;
	
	GridView grid0;
	
	private float upx;
	private float downx;
	
	private int picIndex = 0;
	
	private int[] picArray = {R.drawable.image1, R.drawable.kula};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);  //No title Bar
		setContentView(R.layout.image_show);
		
		image0 = (ImageSwitcher) findViewById(R.id.imageswitcher);
		image0.setFactory(this);
		image0.setOnTouchListener(this);
		image0.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));   
        image0.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
		//image0.setFocusable(true);
		
		gallery0 = (Gallery)findViewById(R.id.gallery);
		gallery0.setAdapter(new ImageAdapter(gallery.this));
		gallery0.setOnItemSelectedListener(this);
		
		grid0 = (GridView)findViewById(R.id.gridview);
		//grid0.setAdapter(new ImageAdapter(gallery.this));
		
		//Gen a new adapter
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
		for(int i = 0; i < 9; i++){
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("imageItem", android.R.drawable.ic_menu_rotate);
			item.put("textItem", "icon" + i);
			list.add(item);
		}
		SimpleAdapter adp = new SimpleAdapter(this, list, R.layout.grid_item, 
				new String[] {"imageItem", "textItem"}, new int[] {R.id.image_item, R.id.text_item});
		grid0.setAdapter(adp);
		grid0.setOnItemSelectedListener(this);
	}
	
	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		Log.d("alice", "in makeView");
		ImageView image1 = new ImageView(this);
		//image1.setImageResource(picArray[picIndex]);
		image1.setBackgroundColor(0xFF000000); 
        image1.setScaleType(ImageView.ScaleType.FIT_CENTER); 
        image1.setImageResource(picArray[picIndex]);
		return image1;    
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		System.out.println("Alice: I am in the onTouch listener");
		Toast.makeText(gallery.this, "I am in the onTouch callback", Toast.LENGTH_LONG).show();
		Log.d("alice", "in touch listener");
		
		if(arg1.getAction() == MotionEvent.ACTION_DOWN){
			downx = arg1.getX();
			return true;
		}
		else if (arg1.getAction() == MotionEvent.ACTION_UP){
			upx =arg1.getX();
			
			if(upx-downx > 100){
				picIndex = (picIndex == 0) ? picArray.length - 1: picIndex-1;
				
				image0.setInAnimation(AnimationUtils.loadAnimation(this, 
						android.R.anim.slide_in_left));
				image0.setOutAnimation(AnimationUtils.loadAnimation(this, 
						android.R.anim.slide_out_right));
				image0.setImageResource(picArray[picIndex]);
			}
			else if(downx-upx > 100){
				picIndex = (picArray.length - 1 == picIndex) ? 0 : picIndex + 1;
				image0.setInAnimation(AnimationUtils.loadAnimation(this, 
						android.R.anim.slide_in_left));
				image0.setOutAnimation(AnimationUtils.loadAnimation(this, 
						android.R.anim.slide_out_right));
				image0.setImageResource(picArray[picIndex]);
			}
			return true;
		}
			
		return false;
	}
	
    @Override   
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,   
            long arg3) {   
        image0.setImageResource(new ImageAdapter().image[position]);   
    }

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		Log.d("alice", "i'm in nothing selected");
	}   


}