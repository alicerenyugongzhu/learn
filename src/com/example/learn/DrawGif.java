package com.example.learn;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 
 * @author Alice Ai
 * GIF play doesn't work. Need double confirm if I got more time
 * Play several jpg also ok. So if I need it. I can use that way
 *
 */

public class DrawGif extends Activity{
	private long movieStart;
	private Movie movie;
    @Override
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);
    	MyGifView myGifView = new MyGifView(this);
    	//setContentView(R.layout.gif);
    	setContentView(myGifView);
    }
    
    public class MyGifView extends View {

    	public MyGifView(Context context) {
    		super(context);
    		movie = Movie.decodeStream(getResources().openRawResource(R.drawable.youchuan));
    		Log.d("alice","I am in the mygifView");
    	}
    	
    	@Override
    	protected void onDraw(Canvas canvas){
    		long currTime = android.os.SystemClock.uptimeMillis();
    		if(movieStart == 0){
    			movieStart = currTime;
    		}
    		if(movie != null){
    			int duration = movie.duration();
    			//Log.d("alice", "duration is " + duration);
    			if(duration == 0) duration = 2000;
    			int relTime = (int)((currTime - movieStart)%duration);
    			Log.d("alice","real time is " + relTime);
    			//Log.d("alice", "duration is " + duration + " currTime is " + currTime);
    			movie.setTime(relTime);
    			movie.draw(canvas, 0, 0);
    			invalidate();
    		}
    		super.onDraw(canvas);
    	}
        
    }
}
