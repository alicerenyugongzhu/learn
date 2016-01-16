package com.example.learn;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VedioActivity extends Activity{
	
	VideoView mvv;
	Button load, start, stop;
	String videoPath = "/basketball/[黑子的篮球][第二季]第1集_hd.mp4";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vedio_play);
		
		load = (Button)findViewById(R.id.loadVideo);
		start = (Button)findViewById(R.id.startVideo);
		stop = (Button)findViewById(R.id.stopVideo);
		mvv = (VideoView)findViewById(R.id.video);
		videoPath = Environment.getExternalStorageDirectory().toString() + videoPath;
		Log.d("alice", videoPath);
		
		load.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mvv.setVideoPath(videoPath);
				mvv.setMediaController(new MediaController(VedioActivity.this));
				//Video can't control the voice channel. Need to rewrite mediaPlayer
				mvv.requestFocus();
			}
			
		});
		
		start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mvv.start();
			}
			
		});
		
		stop.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mvv.pause();
			}
			
		});
	}
}