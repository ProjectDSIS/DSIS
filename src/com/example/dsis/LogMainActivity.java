package com.example.dsis;

import android.app.Activity;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.*;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LogMainActivity extends Activity {

	WebView mWebView;
	WebSettings Wset;
	Intent itt;
	String chs = "https://student.donga.ac.kr/Main.aspx";
	int flag = 0;
	boolean check = true;
	int deviceWidth;
	int deviceHeight;
	int checkZoom = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logmain);
		
		//디바이스 해상도 리턴
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		deviceWidth = displayMetrics.widthPixels;
		deviceHeight =displayMetrics.heightPixels;
		
		mWebView = (WebView)findViewById(R.id.wv_login);	// 웹뷰와 xml 간 연동
		mWebView.setWebViewClient(new WebViewClient());
		
		Wset = mWebView.getSettings();		
		Wset.setJavaScriptEnabled(true);	 // 자바스크립트 허용
		
		itt = new Intent();
		
		mWebView.setHorizontalScrollBarEnabled(false); //가로 스크롤  
		mWebView.setVerticalScrollBarEnabled(false);
		
		mMainHandler = new SendMassgeHandler();
		mCountThread = new CountThread();
				
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				handler.proceed(); // SSL 에러가 발생해도 계속 진행!
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {	// 페이지 로드 완료 후 
				if(checkZoom == 0)
				{
					mWebView.zoomIn();
					checkZoom = 1;
				}
			}			
		});
		
		mWebView.loadUrl("https://student.donga.ac.kr/Login.aspx");
		mCountThread.start();
		
		Log.e("메인","피니쉬");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_main, menu);
		return true;
	}
	
	private SendMassgeHandler mMainHandler = null;
	private CountThread mCountThread = null;
	
	// Handler 클래스
    class SendMassgeHandler extends Handler {
         
        @Override
        public void handleMessage(Message msg) {
                   	
            if(msg.what == 0)
            {               	
            	mWebView.setOnTouchListener(new View.OnTouchListener() {	// 터치 리스너로 무빙 터치 발생 시 스크롤 없게
					
				    public boolean onTouch(View v, MotionEvent event) {
				      return (event.getAction() == MotionEvent.ACTION_MOVE);
				    }
				  });
            	
            	if(deviceHeight == 2560 && deviceWidth == 1440)
             		mWebView.scrollTo(380,1265); // 웹사이트 로그인 화면 절대 좌표 설정 2560*1440
            	
            	else if(deviceHeight == 2392 && deviceWidth == 1440)
             		mWebView.scrollTo(380,1250); // 웹사이트 로그인 화면 절대 좌표 설정 2392*1440
            	
            	else if(deviceHeight == 1920 && deviceWidth == 1080)
            		mWebView.scrollTo(285,950); // 웹사이트 로그인 화면 절대 좌표 설정 1920*1080 2.02 3.78
            	
            	else if(deviceHeight == 1776 && deviceWidth == 1080)
            		mWebView.scrollTo(285,950); // 웹사이트 로그인 화면 절대 좌표 설정 1776*1080 G2
            	
            	else if(deviceHeight == 1280 && deviceWidth == 768)
            		mWebView.scrollTo(175,635); // 웹사이트 로그인 화면 절대 좌표 설정 1280*768
            	
            	else if(deviceHeight == 1280 && deviceWidth == 720)
            		mWebView.scrollTo(195,635); // 웹사이트 로그인 화면 절대 좌표 설정 1280*720
            	
            	else if(deviceHeight == 1184 && deviceWidth == 720)
            		mWebView.scrollTo(195,600); // 웹사이트 로그인 화면 절대 좌표 설정 1184*720  베가R3
            	
            	
            	if(chs.equals(mWebView.getUrl()))
            	{
            		mWebView.loadUrl("about:blank");
            		Log.e("로그인", "성공!");
            		flag = 1;
            	}
            }   
            super.handleMessage(msg);
        }
    };
     
    // Thread 클래스
    class CountThread extends Thread implements Runnable {
         
        @Override
        public void run() {
            super.run();
            
            while (check) {
            	
            	try{
            		
            		if(flag == 0)
            		{            	
            			// 메시지 얻어오기
            			Message msg = mMainHandler.obtainMessage();
                      
            			// 메시지 ID 설정
            			msg.what = 0;
                 
            			Log.e("sub 스레드", "성공!");
            			mMainHandler.sendMessage(msg);
            		}
            	
            		else if(flag == 1)
					{
						Log.e("화면전환", "성공");
						itt.setClass(LogMainActivity.this, ThisIsActivity.class);
						startActivity(itt);
						check = false;				
						
						finish();
					}
            		
            		Thread.sleep(300);
            	}
            	catch(Exception e)
            	{
            		Log.e("쓰레드", "로그인 체킹 실패");
            	}
            }
        }
    }
}


