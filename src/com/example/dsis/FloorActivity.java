package com.example.dsis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class FloorActivity extends Activity {

	Intent itt;
	int num = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.floor);
		
		//itt = getIntent();
		//num = itt.getIntExtra("code", 0);
		
		itt = new Intent();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.floor, menu);
		return true;
	}

	public void ck_b1(View v) // 雖ж 1類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 10);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
	
	public void ck_f1(View v) // 1類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 1);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
	
	public void ck_f2(View v) // 2類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 2);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
	
	public void ck_f3(View v) // 3類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 3);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
	
	public void ck_f4(View v) // 4類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 4);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
	
	public void ck_f5(View v) // 5類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 5);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
	
	public void ck_f6(View v) // 6類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 6);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
	
	public void ck_f7(View v) // 7類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 7);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
	
	public void ck_f8(View v) // 8類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 8);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
	
	public void ck_f9(View v) // 9類
	{
		itt.setClass(FloorActivity.this, SFMajorActivity.class);
		itt.putExtra("code", 9);
		startActivityForResult(itt, 0);
		// startActivity(itt);
	}
}
