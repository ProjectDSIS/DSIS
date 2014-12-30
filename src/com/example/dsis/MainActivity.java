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
		Wset.setJavaScriptEnabled(true);	 // 자바스크립트 허용
		mWebView.loadUrl("http://sjhan2343.cafe24.com/counter/counter.php");  

		Toast.makeText(this, " \'디스이즈\'는 데이터 통신을 사용함  :- ) ",
				Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show

		itt = new Intent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void ck_notice(View v) // 공지사항
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
			Toast.makeText(this, " 해당 버전에서는 제한된 기능입니다 :- )", Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
		}
	}

	public void ck_study(View v) // 학사공지
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, MdongaActivity.class);
			itt.putExtra("code", 2);
			startActivityForResult(itt, 0);
		}
		else
		{
			Toast.makeText(this, " 해당 버전에서는 제한된 기능입니다 :- )", Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
		}
	}

	public void ck_eat(View v) // 학교식당
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, MdongaActivity.class);
			itt.putExtra("code", 3);
			startActivityForResult(itt, 0);
		}
		else
		{
			Toast.makeText(this, " 해당 버전에서는 제한된 기능입니다 :- )", Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
		}
	}

	public void ck_free(View v) // 자유게시판
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, MdongaActivity.class);
			itt.putExtra("code", 4);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " 해당 버전에서는 제한된 기능입니다 :- )", Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
		}
	}

	public void ck_lib(View v) // 도서관
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, LibActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " 해당 버전에서는 제한된 기능입니다 :- )", Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
		}
	}

	public void ck_dsis(View v) // 디스이즈
	{
		if(sw == false)
		{
			itt.setClass(MainActivity.this, LogMainActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " 해당 버전에서는 제한된 기능입니다 :- )", Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
		}
	}

	public void ck_service(View v) // 배달정보
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, BaeDalActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " 해당 버전에서는 제한된 기능입니다 :- )", Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
		}
	}

	public void ck_map(View v) // 지도
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, MapActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " 해당 버전에서는 제한된 기능입니다 :- )", Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
		}
	}

	public void ck_dt(View v) // 개발팀
	{
		if(sw == true)
		{
			itt.setClass(MainActivity.this, DeveloperActivity.class);
			startActivity(itt);
		}
		else
		{
			Toast.makeText(this, " 해당 버전에서는 제한된 기능입니다 :- )", Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
		}
	}

	/* Back key 두번눌러 종료 코드 시작 */
	private static final int MSG_TIMER_EXPIRED = 1;
	private static final int BACKEY_TIMEOUT = 2000;
	private boolean mIsBackKeyPressed = false;
	private long mCurrentTimeInMillis = 0;

	@Override
	public void onBackPressed() {

		if (mIsBackKeyPressed == false) {
			mIsBackKeyPressed = true;
			mCurrentTimeInMillis = Calendar.getInstance().getTimeInMillis();
			Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르면 종료됨 :- (",
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
	/* Back key 두번눌러 종료 코드 끝 */
}
