package com.example.learn;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class PinBallActivity extends Activity {
	
	//table width x height
	private int tableWidth;
	private int tableHeight;
	//racket width x height
	private final int racketWidth = 70;
	private final int racketHeight = 20;
	//Ball Size
	private final int ballSize = 12;
	
	//Sign of the end
	private boolean gameOver = false;
	
	//Ball running speed calculation
	Random rand = new Random();
	private double xyRate = rand.nextDouble()-0.5;
	private int ballSpeedY = 10;
	private int ballSpeedX = (int) (ballSpeedY * xyRate * 2);
	
	//Ball position
	private int ballPosX = rand.nextInt(200) + 20;
	private int ballPosY = rand.nextInt(10) + 20;
	//racket position on Y 
	private int racketPosY;
	private int racketPosX = rand.nextInt(200);
	//temp postion
	private float tempX = 0;
	private float tempY = 0;
	
	@Override 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		final GameView gameView = new GameView(this);
		setContentView(gameView);
		
		WindowManager wm = getWindowManager();
		Display dp = wm.getDefaultDisplay();
		tableWidth = dp.getWidth();
		tableHeight = dp.getHeight();
		
		racketPosY = tableWidth -80;
		
		final Handler handler = new Handler(){
			public void handleMessage(Message msg){
				if(msg.what == 0x1000){
					gameView.invalidate();
				}
			}
		};
		
		gameView.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				int action = arg1.getAction();
				float currX;
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					racketPosX = (int)arg1.getX();
					handler.sendEmptyMessage(0x1000);
					break;
				case MotionEvent.ACTION_MOVE:
					//Alice: it seems here doesn't work
					currX = arg1.getX();
					Log.d("alice", "I am in the action move");
					Log.d("alice","current X is " + currX);
					racketPosX = racketPosX + (int)(currX - tempX);
					handler.sendEmptyMessage(0x1000);
					break;
				}
				return false;
			}
			
		});
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				if(ballPosX <= 0 || ballPosX >= (tableWidth - ballSize)){
					ballSpeedX = -ballSpeedX;
				}
				if(ballPosY >= (racketPosY - ballSize) && 
						(ballPosX < racketPosX || ballPosX > racketPosX + racketWidth)){
					timer.cancel();
					gameOver = true;
				} else if ((ballPosX >= racketPosX && ballPosX <= (racketPosX + racketWidth) && ballPosY >= (racketPosY - ballSize))
						|| ballPosY <= 0){
					ballSpeedY = -ballSpeedY;
				}
				ballPosX = ballPosX + ballSpeedX;
				ballPosY = ballPosY + ballSpeedY;
				handler.sendEmptyMessage(0x1000);
			}
			
		}, 0, 100);
			
		
	}
	
	class GameView extends View {

		public GameView(Context context) {
			super(context);
			setFocusable(true);
		}
		
		public void onDraw(Canvas canvas){
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			if(gameOver){
				paint.setColor(Color.RED);
				paint.setTextSize(40);
				canvas.drawText("Game Over", 50, 200, paint);
			} else {
				paint.setColor(Color.rgb(240, 240, 80));
				canvas.drawCircle(ballPosX, ballPosY, ballSize, paint);
				paint.setColor(Color.rgb(80, 80, 200));
				canvas.drawRect(racketPosX, racketPosY, racketWidth + racketPosX, 
						racketHeight + racketPosY, paint);
			}
		}
	}
}