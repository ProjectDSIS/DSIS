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
		
		//����̽� �ػ� ����
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		deviceWidth = displayMetrics.widthPixels;
		deviceHeight =displayMetrics.heightPixels;
		
		mWebView = (WebView)findViewById(R.id.wv_login);	// ����� xml �� ����
		mWebView.setWebViewClient(new WebViewClient());
		
		Wset = mWebView.getSettings();		
		Wset.setJavaScriptEnabled(true);	 // �ڹٽ�ũ��Ʈ ���
		
		itt = new Intent();
		
		mWebView.setHorizontalScrollBarEnabled(false); //���� ��ũ��  
		mWebView.setVerticalScrollBarEnabled(false);
		
		mMainHandler = new SendMassgeHandler();
		mCountThread = new CountThread();
				
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				handler.proceed(); // SSL ������ �߻��ص� ��� ����!
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {	// ������ �ε� �Ϸ� �� 
				if(checkZoom == 0)
				{
					mWebView.zoomIn();
					checkZoom = 1;
				}
			}			
		});
		
		mWebView.loadUrl("https://student.donga.ac.kr/Login.aspx");
		mCountThread.start();
		
		Log.e("����","�ǴϽ�");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_main, menu);
		return true;
	}
	
	private SendMassgeHandler mMainHandler = null;
	private CountThread mCountThread = null;
	
	// Handler Ŭ����
    class SendMassgeHandler extends Handler {
         
        @Override
        public void handleMessage(Message msg) {
                   	
            if(msg.what == 0)
            {               	
            	mWebView.setOnTouchListener(new View.OnTouchListener() {	// ��ġ �����ʷ� ���� ��ġ �߻� �� ��ũ�� ����
					
				    public boolean onTouch(View v, MotionEvent event) {
				      return (event.getAction() == MotionEvent.ACTION_MOVE);
				    }
				  });
            	
            	if(deviceHeight == 2560 && deviceWidth == 1440)
             		mWebView.scrollTo(380,1265); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 2560*1440
            	
            	else if(deviceHeight == 2392 && deviceWidth == 1440)
             		mWebView.scrollTo(380,1250); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 2392*1440
            	
            	else if(deviceHeight == 1920 && deviceWidth == 1080)
            		mWebView.scrollTo(285,950); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1920*1080 2.02 3.78
            	
            	else if(deviceHeight == 1776 && deviceWidth == 1080)
            		mWebView.scrollTo(285,950); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1776*1080 G2
            	
            	else if(deviceHeight == 1280 && deviceWidth == 768)
            		mWebView.scrollTo(175,635); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1280*768
            	
            	else if(deviceHeight == 1280 && deviceWidth == 720)
            		mWebView.scrollTo(195,635); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1280*720
            	
            	else if(deviceHeight == 1184 && deviceWidth == 720)
            		mWebView.scrollTo(195,600); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1184*720  ����R3
            	
            	
            	if(chs.equals(mWebView.getUrl()))
            	{
            		mWebView.loadUrl("about:blank");
            		Log.e("�α���", "����!");
            		flag = 1;
            	}
            }   
            super.handleMessage(msg);
        }
    };
     
    // Thread Ŭ����
    class CountThread extends Thread implements Runnable {
         
        @Override
        public void run() {
            super.run();
            
            while (check) {
            	
            	try{
            		
            		if(flag == 0)
            		{            	
            			// �޽��� ������
            			Message msg = mMainHandler.obtainMessage();
                      
            			// �޽��� ID ����
            			msg.what = 0;
                 
            			Log.e("sub ������", "����!");
            			mMainHandler.sendMessage(msg);
            		}
            	
            		else if(flag == 1)
					{
						Log.e("ȭ����ȯ", "����");
						itt.setClass(LogMainActivity.this, ThisIsActivity.class);
						startActivity(itt);
						check = false;				
						
						finish();
					}
            		
            		Thread.sleep(300);
            	}
            	catch(Exception e)
            	{
            		Log.e("������", "�α��� üŷ ����");
            	}
            }
        }
    }
}


