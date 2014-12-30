package com.example.dsis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MapActivity extends Activity {
    Intent itt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        itt = new Intent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    public void ck_s06(View v) // 산학협력관
    {
        itt.setClass(MapActivity.this, FloorActivity.class);
        itt.putExtra("code", 1);
        startActivityForResult(itt, 0);
    }

}
