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

public class LibActivity extends Activity {

	Intent itt;

	WebView mWebView;
	WebSettings Wset;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lib);

		// xml�� ������ ���α׷����ٸ� �����Ѵ�. id�� xml�� ����
		final ProgressBar mProgCircle = (ProgressBar) findViewById(R.id.bar);

		mWebView = (WebView) findViewById(R.id.wv_lib); // ����� xml �� ����
		mWebView.setWebViewClient(new WebViewClient());
		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // �ڹٽ�ũ��Ʈ ���

		// loadUrl �Ϸ���� ���α׷����ٸ� ���̰� ����
		mProgCircle.setVisibility(View.VISIBLE);

		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				if (mProgCircle.getVisibility() == View.VISIBLE) {
					mProgCircle.setVisibility(View.INVISIBLE);
				}
			}
		});
		mWebView.loadUrl("http://m.donga.ac.kr/SUB003/SUB_003003.asp?PID=003003");

		itt = new Intent();
		itt.setClass(LibActivity.this, LibInfoActivity.class);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lib, menu);
		return true;
	}

	public void ck_st4(View v) // 4�� �׷콺�͵�
	{
		itt.putExtra("code", 1);
		startActivityForResult(itt, 0);
	}

	public void ck_st5a(View v) // 5�� A
	{
		itt.putExtra("code", 2);
		startActivityForResult(itt, 0);
	}

	public void ck_st5b(View v) // 5�� B
	{
		itt.putExtra("code", 3);
		startActivityForResult(itt, 0);
	}

	public void ck_st5c(View v) // 5�� C
	{
		itt.putExtra("code", 4);
		startActivityForResult(itt, 0);
	}

	public void ck_st5d(View v) // 5�� D
	{
		itt.putExtra("code", 5);
		startActivityForResult(itt, 0);
	}

}
