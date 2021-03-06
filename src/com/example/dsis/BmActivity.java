package com.example.dsis;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BmActivity extends Activity {
	private WebView mWebView;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bm);
		
		mWebView = (WebView) findViewById(R.id.webView1);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.loadUrl("http://sjhan2343.cafe24.com/wordpress/?cat=5");
		mWebView.setWebViewClient(new WebViewClientClass());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bm, menu);
		return true;
	}
	
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if((keyCode == KeyEvent.KEYCODE_BACK)&& mWebView.canGoBack()){
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private class WebViewClientClass extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view,String url) {
			view.loadUrl(url);
			return true;
		}
		
	}


}
