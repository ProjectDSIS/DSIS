package com.example.dsis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.ImageView;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(StartActivity.this,
						MainActivity.class);
				// Intent intent = new Intent(StartvActivity.this, Test.class);
				startActivity(intent);
				// 뒤로가기 했을경우 안나오도록 없애주기 >> finish!!
				finish();
			}
		}, 1500);
	}
	
	public void onBackPressed() {
		moveTaskToBack(true);
		finish();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

}
