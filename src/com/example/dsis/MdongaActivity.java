package com.example.dsis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


public class MdongaActivity extends Activity {

	Intent itt;
	int num = 0;

	WebView mWebView;
	WebSettings Wset;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	 
		setContentView(R.layout.mdonga);
		
		itt = getIntent();
		num = itt.getIntExtra("code", 0);
		
		mWebView = (WebView)findViewById(R.id.mb_Wv); // 웹뷰와 xml 간 연동
		mWebView.setWebViewClient(new WebViewClient());
		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // 자바스크립트 허용

		//xml에 선언한 프로그래스바를 생성한다. id는 xml을 참고
		final ProgressBar mProgCircle = (ProgressBar)findViewById(R.id.bar);
				
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

		else if (num == 1) // 공지
			mWebView.loadUrl("http://m.donga.ac.kr/WebApp/BOARD/MBASIC/List.asp?BIDX=15"); // 웹뷰
																							// 주소
		else if (num == 2) // 학사
			mWebView.loadUrl("http://m.donga.ac.kr/WebApp/BOARD/MBASIC/List.asp?BIDX=102"); // 웹뷰
																							// 주소
		else if (num == 3) // 학식
			mWebView.loadUrl("http://m.donga.ac.kr/SUB003/SUB_003001.asp?PID=003001"); // 웹뷰
																						// 주소
		else if (num == 4) // 자게
			mWebView.loadUrl("http://m.donga.ac.kr/WebApp/BOARD/MBASIC/List.asp?BIDX=20"); // 웹뷰
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mdonga, menu);
		return true;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {// 뒤로가기 눌렸을때 이전페이지
		// 나오도록
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.loadUrl("javascript:history.go(-1);");// 한칸뒤로
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
	

