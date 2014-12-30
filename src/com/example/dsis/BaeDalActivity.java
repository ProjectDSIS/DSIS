package com.example.dsis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BaeDalActivity extends Activity {

	Intent itnt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baedal);
	itnt = new Intent();
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bae_dal, menu);
		return true;
	}
	
	public void ck_sh(View v){
		itnt.setClass(BaeDalActivity.this,ShActivity.class);
		startActivity(itnt);
	}
	public void ck_bm(View v){
		itnt.setClass(BaeDalActivity.this,BmActivity.class);
		startActivity(itnt);
	}
	public void ck_gd(View v){
		itnt.setClass(BaeDalActivity.this,GdActivity.class);
		startActivity(itnt);
	}

}
