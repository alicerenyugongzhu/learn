package com.example.learn;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class bubbleActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bubble);
		WebView web = (WebView)findViewById(R.id.webview);
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setBuiltInZoomControls(true);
		web.loadUrl("http://www.baidu.com");
		web.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url){
				view.loadUrl(url);
				return false;
			}
			
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
				Toast.makeText(bubbleActivity.this, "Oh no! " + description, Toast.LENGTH_SHORT).show(); 
			}
		});
	}
}