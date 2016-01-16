package com.example.learn;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
 


public class checkboxActivity extends Activity implements SeekBar.OnSeekBarChangeListener{

	
	View.OnClickListener listener0 = null;
	View.OnClickListener listener1 = null;
	View.OnClickListener listener2 = null;
	View.OnClickListener listener3 = null;
			
	Button button0;
	Button button1; 
	Button button2;
	
	CheckBox box0;
	CheckBox box1;
	
	RadioGroup group0;
	
	ProgressBar bar0;
	ProgressBar bar1;
	
	SeekBar seekbar0;
	TextView barValue;
	
	RatingBar ratingbar0;
	
	ImageButton image0;
	
	int bar0Current = 0;
	int bar1Current = 0;
	int bar1SecCurrent = 0;
			
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkbox_ex);

		
		TextView newTextView = (TextView)findViewById(R.id.extraFromMain);
		newTextView.setText(this.getIntent().getExtras().get("input").toString());
		
		box0 = (CheckBox)findViewById(R.id.selA);
    	box1 = (CheckBox)findViewById(R.id.selB);
		
		listener0 = new View.OnClickListener(){


			@Override
			public void onClick(View v) {
				
				String r= "";
				if(box0.isChecked()){
					r = r + "." + box0.getText();
				}
				if (box1.isChecked()){
					r = r + "." + box1.getText();
				}
				Toast.makeText(checkboxActivity.this, "Äãµã»÷µÄÊÇ:"+r, Toast.LENGTH_LONG).show();
				setTitle("Checked:" + r);
				
			}
						
		};
		
		button0 = (Button)findViewById(R.id.buttonSel);
		button0.setOnClickListener(listener0);
		button0.setEnabled(true);
		
		group0 = (RadioGroup) findViewById(R.id.groupTest);
		group0.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				int selId = arg0.getCheckedRadioButtonId();
				RadioButton rb = (RadioButton) findViewById(selId);
				setTitle("Selected: " +rb.getText());
			}
			
		});
		
		bar0 = (ProgressBar) findViewById(R.id.roundBar);
		bar0.incrementProgressBy(10);
		bar1 = (ProgressBar) findViewById(R.id.lineBar);
		bar1.incrementProgressBy(10);
		bar1.incrementSecondaryProgressBy(5);
		
		
		listener2 = new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(bar0Current < bar0.getMax()){
				   bar0Current = bar0Current + 10;
				   bar0.setProgress(bar0Current);
				}
				
				if(bar1Current < bar1.getMax()){
					bar1Current = bar1Current + 5;
					bar1.setProgress(bar1Current);
				}
				
				if(bar1SecCurrent < bar1.getMax()){
					bar1SecCurrent = bar1SecCurrent + 10;
					bar1.setSecondaryProgress(bar1SecCurrent);
				}
			}
		};
		
		button2 = (Button) findViewById(R.id.btnStart);
		button2.setOnClickListener(listener2);
		
		seekbar0 = (SeekBar) findViewById(R.id.seekbar0);
		seekbar0.setOnSeekBarChangeListener(this);
		
		barValue = (TextView) findViewById(R.id.textSeekBar);
	
		TextView textRate = (TextView) findViewById(R.id.textRate);
		textRate.setText("rating bar");
		
	    final RatingBar ratingBar_Small = (RatingBar)findViewById(R.id.ratingbar_Small);
	    final RatingBar ratingBar_Indicator = (RatingBar)findViewById(R.id.ratingbar_Indicator);
	    final RatingBar ratingBar_default = (RatingBar)findViewById(R.id.ratingbar_default);
/**
 *    rating bar only default mode works. So don't use others
 */
	    ratingBar_default.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
	    	
       @Override
	   public void onRatingChanged(RatingBar ratingBar, float rating,
	     boolean fromUser) {
	    ratingBar_Small.setRating(rating);
	    ratingBar_Indicator.setRating(rating);
	    Toast.makeText(checkboxActivity.this, "rating:"+String.valueOf(rating),
	      Toast.LENGTH_LONG).show();
	   }});
	    				
		listener3 = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialog dialog = new AlertDialog.Builder(checkboxActivity.this)
				    .setTitle("Alert")
				    .setMessage("You click the ImageButton")
				    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							image0.setImageDrawable(
									getResources().getDrawable(android.R.drawable.picture_frame));
						}
					}).create();
				    dialog.show();
			}
		};
		image0 = (ImageButton) findViewById(R.id.imagebtn);
		image0.setOnClickListener(listener3);		
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		barValue.setText("SeekBar value is " + progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

		barValue.setText("on draggen");
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		barValue.setText("draggen done");
	}

}