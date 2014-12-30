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
		
		mWebView = (WebView)findViewById(R.id.mb_Wv); // ����� xml �� ����
		mWebView.setWebViewClient(new WebViewClient());
		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // �ڹٽ�ũ��Ʈ ���

		//xml�� ������ ���α׷����ٸ� �����Ѵ�. id�� xml�� ����
		final ProgressBar mProgCircle = (ProgressBar)findViewById(R.id.bar);
				
		//loadUrl �Ϸ���� ���α׷����ٸ� ���̰� ����
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

		else if (num == 1) // ����
			mWebView.loadUrl("http://m.donga.ac.kr/WebApp/BOARD/MBASIC/List.asp?BIDX=15"); // ����
																							// �ּ�
		else if (num == 2) // �л�
			mWebView.loadUrl("http://m.donga.ac.kr/WebApp/BOARD/MBASIC/List.asp?BIDX=102"); // ����
																							// �ּ�
		else if (num == 3) // �н�
			mWebView.loadUrl("http://m.donga.ac.kr/SUB003/SUB_003001.asp?PID=003001"); // ����
																						// �ּ�
		else if (num == 4) // �ڰ�
			mWebView.loadUrl("http://m.donga.ac.kr/WebApp/BOARD/MBASIC/List.asp?BIDX=20"); // ����
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mdonga, menu);
		return true;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {// �ڷΰ��� �������� ����������
		// ��������
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.loadUrl("javascript:history.go(-1);");// ��ĭ�ڷ�
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
	

