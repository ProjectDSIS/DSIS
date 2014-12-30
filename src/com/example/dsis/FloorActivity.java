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

        itt = new Intent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.floor, menu);
        return true;
    }

    private void showActivity(int _floor)
    {
        itt.setClass(FloorActivity.this, SFMajorActivity.class);
        itt.putExtra("code", _floor);
        startActivityForResult(itt, 0);
    }

    public void ck_b1(View v) // 지하 1층
    {
        showActivity(10);
    }

    public void ck_f1(View v) // 1층
    {
        showActivity(1);
    }

    public void ck_f2(View v) // 2층
    {
        showActivity(2);
    }

    public void ck_f3(View v) // 3층
    {
        showActivity(3);
    }

    public void ck_f4(View v) // 4층
    {
        showActivity(4);
    }

    public void ck_f5(View v) // 5층
    {
        showActivity(5);
    }

    public void ck_f6(View v) // 6층
    {
        showActivity(6);
    }

    public void ck_f7(View v) // 7층
    {
        showActivity(7);
    }

    public void ck_f8(View v) // 8층
    {
        showActivity(8);
    }

    public void ck_f9(View v) // 9층
    {
        showActivity(9);
    }
}