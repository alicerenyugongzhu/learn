package com.example.learn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;

public class TreeActivity extends View{

	int depth;
	private double length = 0.75;
	private double angle = Math.PI/5;
	Handler handler = new Handler();
	class GameThread implements Runnable{

		TreeActivity tree;
		public GameThread(TreeActivity tree){
			this.tree = tree;
		}
		@Override
		public void run() {
			tree.depth++;
			if(tree.depth > 12){
				tree.depth = 1;
			}
			tree.invalidate();
			handler.postDelayed(this, 1000);
		}
		
	}
	
	public TreeActivity(Context context) {
		super(context);
		depth = 1;
		handler.post(new GameThread(this));
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawRect(new Rect(0, 0, 320, 480), paint);
		paint.setColor(Color.YELLOW);
		PointF pt = new PointF(160, 240);
		drawFX(canvas, paint, depth, pt, 64, -Math.PI/2);
		canvas.drawText("depth: " + depth, 160, 280, paint);
		
	}

	private void drawFX(Canvas canvas, Paint paint, int depth, PointF pt,
			double i, double d) {
		float x1 = (float) (pt.x + i*Math.cos(d));
		float y1 = (float) (pt.y + i*Math.sin(d));
		canvas.drawLine(pt.x,  pt.y,  x1,  y1,  paint);
		if(depth > 1){
			drawFX(canvas, paint, depth-1, new PointF(x1, y1), i * length, d + angle);
			drawFX(canvas, paint, depth-1, new PointF(x1, y1), i * length, d - angle);
		} else {
			return;
		}
		
	}
}