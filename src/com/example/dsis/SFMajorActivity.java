package com.example.dsis;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class SFMajorActivity extends Activity {

	Intent itt;
	int num;
	int deviceWidth;
	int deviceHeight;
	ImageView imgview;
	int check = 0;
	
	 // ��ġ �� x��ǥ
    float x1=0 , x2=0;
    // ��ġ �� y��ǥ
    float y1=0 , y2=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sfmajor);
		
		itt = getIntent();
		num = itt.getIntExtra("code", 0);

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		deviceWidth = displayMetrics.widthPixels;
		deviceHeight = displayMetrics.heightPixels;
		// �� �־� �־�� �Ѵ�. �̷��� �ؾ� displayMetrics�� ������ �ȴ�.
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		imgview = (ImageView) findViewById(R.id.sfmap); // xml �̹�����
																	// �� �̹����並
																	// �����´�.
		draw();
		
		Toast.makeText(this, " �¿� ��ġ�� ���� �� �� �̵� ����  :- ) ",
				Toast.LENGTH_SHORT).show(); // Toast ��ü ���� �� , " "������ ��� show
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sfmajor, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		 // ������ ��ġ �׼��� ������ �޾ƿ´�.
        int action = event.getAction();
       
        // �׼��� ������ ���� ���� ����
        switch (action) {
        // �巡�� �Ǿ��� ���� �̺�Ʈ ó��
        case MotionEvent.ACTION_DOWN :
        	x1 = event.getX();
        	y1 = event.getX();
            break;
            
        case MotionEvent.ACTION_UP :
        	x2 = event.getX();
        	y2 = event.getX();
        	
        	
        	if(x1 < x2)
            {
            	if(num>1 && num!=10)
            	{
            		num--;
            	}
            	else if(num==1)
            	{
            		num = 10;
            	}
            	
            	draw();
            }
            else if(x2 < x1)
            {
            	if(num<9)
            	{
            		num++;
            	}
            	else if(num == 10)
            		num = 1;
            	
            	draw();
            }
        	
        	break;
        	
        }        

        return true;
   	}

	void draw()
	{
		if (num != 0 && num != 10) {
			
			int temp = num-1;
			
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.sf1+temp); // ��Ʈ�� �̹����� �����.
			Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, deviceWidth,
					deviceHeight, true); // �̹��� ������ ����
			imgview.setImageBitmap(resizedbitmap); // �̹����信 ������ �̹��� �ֱ�
		}
		else if(num == 10)
		{
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.sfb1); // ��Ʈ�� �̹����� �����.
			Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, deviceWidth,
					deviceHeight, true); // �̹��� ������ ����
			imgview.setImageBitmap(resizedbitmap); // �̹����信 ������ �̹��� �ֱ�
		}	
	}
}



