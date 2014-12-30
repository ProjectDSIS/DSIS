package com.example.dsis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class LibInfoActivity extends Activity {

	Intent itt;
	int num = 0;
	
	WebView mWebView;
	WebSettings Wset;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.libinfo);
		
		itt = getIntent();
		num = itt.getIntExtra("code", 0);
		
		//xml에 선언한 프로그래스바를 생성한다. id는 xml을 참고
		final ProgressBar mProgCircle = (ProgressBar)findViewById(R.id.bar);
		
		mWebView = (WebView)findViewById(R.id.wv_libinfo); // 웹뷰와 xml 간 연동
		mWebView.setWebViewClient(new WebViewClient());
		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // 자바스크립트 허용		
		
		Wset.setSupportZoom(true);
		Wset.setBuiltInZoomControls(true);
		
		mWebView.setInitialScale(0);//default,no zoom
		
		//loadUrl 완료까지 프로그래스바를 보이게 설정
		mProgCircle.setVisibility(View.VISIBLE);
		
		mWebView.setWebViewClient(new WebViewClient() {
			  @Override
			  public void onPageFinished(WebView view,String url)
			  {   				  
			     if(mProgCircle.getVisibility() ==  View.VISIBLE){
			       mProgCircle.setVisibility(View.INVISIBLE);
			       
			     }		
			  } 
			});		

		if (num == 0)
			mWebView.loadUrl("http://m.donga.ac.kr");

		else if (num == 1) // 4층
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=1"); // 웹뷰
																							// 주소
		else if (num == 2) // 5층 A
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=2"); // 웹뷰
																							// 주소
		else if (num == 3) // 5층 B
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=3"); // 웹뷰
																						// 주소
		else if (num == 4) // 5층 C
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=4"); // 웹뷰
		
		else if (num == 5) // 5층 D
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=5"); // 웹뷰
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lib_info, menu);
		return true;
	}

}
