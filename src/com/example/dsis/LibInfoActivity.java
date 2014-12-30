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
		
		//xml�� ������ ���α׷����ٸ� �����Ѵ�. id�� xml�� ����
		final ProgressBar mProgCircle = (ProgressBar)findViewById(R.id.bar);
		
		mWebView = (WebView)findViewById(R.id.wv_libinfo); // ����� xml �� ����
		mWebView.setWebViewClient(new WebViewClient());
		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // �ڹٽ�ũ��Ʈ ���		
		
		Wset.setSupportZoom(true);
		Wset.setBuiltInZoomControls(true);
		
		mWebView.setInitialScale(0);//default,no zoom
		
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

		else if (num == 1) // 4��
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=1"); // ����
																							// �ּ�
		else if (num == 2) // 5�� A
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=2"); // ����
																							// �ּ�
		else if (num == 3) // 5�� B
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=3"); // ����
																						// �ּ�
		else if (num == 4) // 5�� C
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=4"); // ����
		
		else if (num == 5) // 5�� D
			mWebView.loadUrl("http://168.115.76.58/seat/roomview5.asp?room_no=5"); // ����
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lib_info, menu);
		return true;
	}

}
