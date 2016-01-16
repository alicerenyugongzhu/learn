package com.example.learn;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author Administrator
 * I have removed the resource setting
 *
 */

public class playSound extends Activity {
	MediaPlayer mp = null;
	MediaRecorder mr = null;
	Button start;
	Button stop;
	Button startRecord;
	Button stopRecord;
	boolean isPlaying = false;
	boolean isReleased = false;
	
	final String recorderPath = "/sdcard/a.3gpp";
	
    @Override
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.play_music);
    	mp = new MediaPlayer();
    	start = (Button)findViewById(R.id.start);
    	stop = (Button)findViewById(R.id.stop);
    	startRecord = (Button)findViewById(R.id.startRecord);
    	stopRecord = (Button)findViewById(R.id.stopRecord);
    	stopRecord.setVisibility(View.GONE);
    	
    	stop.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(isPlaying){
					if(!isReleased){
						mp.stop();
						mp.release();
						isReleased = true;
					}
					isPlaying = false;
				}
			}
    		
    	});
    	
    	start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try{
					if(!isPlaying){
						isPlaying = true;
					//	mp = MediaPlayer.create(playSound.this, R.raw.want_love);
						mp.setDataSource(recorderPath);
						isReleased = false;
						mp.setLooping(true);
						try{
							mp.prepare();
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						mp.start();
					}
				} catch (IllegalStateException e){
					e.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mp.setOnCompletionListener(new OnCompletionListener(){

					@Override
					public void onCompletion(MediaPlayer arg0) {
						// TODO Auto-generated method stub
						mp.release();
					}
					
				});
			}
    		
    	});
    	
    	startRecord.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				record(recorderPath);
			}

			private void record(String string) {
				// TODO Auto-generated method stub
				try {
					File mediafile = new File(string);
					if(mediafile.exists()){
						mediafile.delete();
					}
					mediafile = null;
					
					startRecord.setVisibility(View.GONE);
					stopRecord.setVisibility(View.VISIBLE);
					
					if(mr == null){
						mr = new MediaRecorder();
						mr.setAudioSource(MediaRecorder.AudioSource.MIC);
						mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
						mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
						mr.setOutputFile(string);
						mr.prepare();
						mr.start();
					}
				} catch (Exception e){
					e.printStackTrace();
				}
				
			}
    		
    	});
    	
    	stopRecord.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mr.stop();
				mr.reset();
				startRecord.setVisibility(View.VISIBLE);
				stopRecord.setVisibility(View.GONE);
			}
    		
    	});
    	
    }
}
