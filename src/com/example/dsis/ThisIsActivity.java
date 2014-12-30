package com.example.dsis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class ThisIsActivity extends Activity {

	Intent itt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thisis);
		
		itt = new Intent();
	}
	
	public void ck_timetable(View v) // 시간표
	{
		Log.d("web","time1");
		itt.setClass(ThisIsActivity.this, TimetActivity.class);
		Log.d("web","time2");
		startActivity(itt);
		Log.d("web","time3");
	}
	
	public void ck_student(View v) // 학적부
	{
		Log.d("web","stu1");
		itt.setClass(ThisIsActivity.this, Student.class);
		Log.d("web","stu2");
		startActivity(itt);
		Log.d("web","stu3");
	}
	
	public void ck_mark(View v) // 성적조회
	{
		Log.d("web","mark1");
		itt.setClass(ThisIsActivity.this, Logmark.class);
		Log.d("web","mark2");
		startActivity(itt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.this_is, menu);
		return true;
	}

}
