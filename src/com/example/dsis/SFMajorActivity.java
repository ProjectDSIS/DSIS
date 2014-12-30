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
	
	 // 터치 된 x좌표
    float x1=0 , x2=0;
    // 터치 된 y좌표
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
		// 꼭 넣어 주어야 한다. 이렇게 해야 displayMetrics가 세팅이 된다.
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		imgview = (ImageView) findViewById(R.id.sfmap); // xml 이미지가
																	// 들어갈 이미지뷰를
																	// 가져온다.
		draw();
		
		Toast.makeText(this, " 좌우 터치를 통해 층 간 이동 가능  :- ) ",
				Toast.LENGTH_SHORT).show(); // Toast 객체 생성 후 , " "문구를 출력 show
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sfmajor, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		 // 현재의 터치 액션의 종류를 받아온다.
        int action = event.getAction();
       
        // 액션의 종류에 따른 역할 수행
        switch (action) {
        // 드래그 되었을 때의 이벤트 처리
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
					R.drawable.sf1+temp); // 비트맵 이미지를 만든다.
			Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, deviceWidth,
					deviceHeight, true); // 이미지 사이즈 조정
			imgview.setImageBitmap(resizedbitmap); // 이미지뷰에 조정한 이미지 넣기
		}
		else if(num == 10)
		{
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.sfb1); // 비트맵 이미지를 만든다.
			Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, deviceWidth,
					deviceHeight, true); // 이미지 사이즈 조정
			imgview.setImageBitmap(resizedbitmap); // 이미지뷰에 조정한 이미지 넣기
		}	
	}
}



