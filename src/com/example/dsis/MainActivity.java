package com.example.dsis;

import java.util.Calendar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

	Intent itt;

	boolean sw = true;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebView mWebView = (WebView) findViewById(R.id.counting);
		WebSettings Wset;
		mWebView.setWebViewClient(new WebViewClient());
		
		Wset = mWebView.getSettings();		
		Wset.setJavaScriptEnabled(true);	 // �ڹٽ�ũ��Ʈ ���
		mWebView.loadUrl("http://sjhan2343.cafe24.com/counter/counter.php");  

		Toast.makeText(this, " \'������\'�� ������ ����� �����  :- ) ",
				Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show

		itt = new Intent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void ck_notice(View v) // ��������
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, MdongaActivity.class);
			itt.putExtra("code", 1);
			startActivityForResult(itt, 0);
			// startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " �ش� ���������� ���ѵ� ����Դϴ� :- )", Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
		}
	}

	public void ck_study(View v) // �л����
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, MdongaActivity.class);
			itt.putExtra("code", 2);
			startActivityForResult(itt, 0);
		}
		else
		{
			Toast.makeText(this, " �ش� ���������� ���ѵ� ����Դϴ� :- )", Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
		}
	}

	public void ck_eat(View v) // �б��Ĵ�
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, MdongaActivity.class);
			itt.putExtra("code", 3);
			startActivityForResult(itt, 0);
		}
		else
		{
			Toast.makeText(this, " �ش� ���������� ���ѵ� ����Դϴ� :- )", Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
		}
	}

	public void ck_free(View v) // �����Խ���
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, MdongaActivity.class);
			itt.putExtra("code", 4);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " �ش� ���������� ���ѵ� ����Դϴ� :- )", Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
		}
	}

	public void ck_lib(View v) // ������
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, LibActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " �ش� ���������� ���ѵ� ����Դϴ� :- )", Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
		}
	}

	public void ck_dsis(View v) // ������
	{
		if(sw == false)
		{
			itt.setClass(MainActivity.this, LogMainActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " �ش� ���������� ���ѵ� ����Դϴ� :- )", Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
		}
	}

	public void ck_service(View v) // �������
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, BaeDalActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " �ش� ���������� ���ѵ� ����Դϴ� :- )", Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
		}
	}

	public void ck_map(View v) // ����
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, MapActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " �ش� ���������� ���ѵ� ����Դϴ� :- )", Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
		}
	}

	public void ck_dt(View v) // ������
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, DeveloperActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " �ش� ���������� ���ѵ� ����Դϴ� :- )", Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
		}
	}

	/* Back key �ι����� ���� �ڵ� ���� */
	private static final int MSG_TIMER_EXPIRED = 1;
	private static final int BACKEY_TIMEOUT = 2000;
	private boolean mIsBackKeyPressed = false;
	private long mCurrentTimeInMillis = 0;

	@Override
	public void onBackPressed() {

		if (mIsBackKeyPressed == false) {
			mIsBackKeyPressed = true;
			mCurrentTimeInMillis = Calendar.getInstance().getTimeInMillis();
			Toast.makeText(this, "\'�ڷ�\' ��ư�� �ѹ� �� ������ ����� :- (",
					Toast.LENGTH_SHORT).show();
			startTimer();
		} else {
			mIsBackKeyPressed = false;

			if (Calendar.getInstance().getTimeInMillis() <= (mCurrentTimeInMillis + (BACKEY_TIMEOUT))) {
				finish();
			}
		}
	}

	private void startTimer() {
		mTimerHander.sendEmptyMessageDelayed(MSG_TIMER_EXPIRED, BACKEY_TIMEOUT);
	}

	private Handler mTimerHander = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_TIMER_EXPIRED: {
				mIsBackKeyPressed = false;
			}
				break;
			}
		}
	};
	/* Back key �ι����� ���� �ڵ� �� */
}
