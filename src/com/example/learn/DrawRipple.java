package com.example.learn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

public class DrawRipple extends View implements Runnable{

	int backwidth;
	int backheight;
    short[] buf1;
    short[] buf2;
    int[] bitmap1;
    int[] bitmap2;
 	
	public DrawRipple(Context context) {
		super(context);
		Bitmap image = BitmapFactory.decodeResource(this.getResources(), R.drawable.image1);
		backwidth = image.getWidth();
		backheight = image.getHeight();
		
		buf1 = new short[backwidth * backheight];
		buf2 = new short[backwidth * backheight];
		
		bitmap1 = new int[backwidth * backheight];
		bitmap2 = new int[backwidth * backheight];
		
		image.getPixels(bitmap1, 0, backwidth, 0, 0, backwidth, backheight);
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			try{
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			RippleSpread();
			render();
			postInvalidate();
		}

	}
	
	private void RippleSpread() {
		for(int i = backwidth; i<backwidth * backheight - backwidth; i++){
			buf2[i] = (short) (((buf1[i-1] + buf1[i+1] + buf1[i-backwidth] + buf1[i+backwidth])>>1)-buf2[i]);
			buf2[i] -= buf2[i] >> 5;
		}
		
		short[] tmp = buf1;
		buf1 = buf2;
		buf2 = tmp;
	}

	private void render() {
		int xoff, yoff;
		int k = backwidth;
		for(int i = 1; i < backheight-1; i++){
			for(int j=0; j<backwidth; j++){
				xoff = buf1[k-1] - buf1[k+1];
				Log.d("alice", "k = " + k + ", bandwidth = " + backwidth);
				yoff = buf1[k-backwidth] - buf1[k+backwidth];
				if((i+yoff) < 0){
					k++;
					continue;
				}
				if((i+yoff) > backheight){
					k++;
					continue;
				}
				if((j+xoff) < 0){
					k++;
					continue;
				}
				if((j+xoff) > backwidth){
					k++;
					continue;
				}
				int  pos1, pos2;
				pos1 = backwidth * (i+yoff) + (j+xoff);
				pos2 = backwidth * i + j;
				bitmap2[pos2++] = bitmap1[pos1++];
				k++;
			}
		}
		
	}
 
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		canvas.drawBitmap(bitmap2, 0, backwidth, 0 , 0 , backwidth, backheight, false, null);
	}
	
}