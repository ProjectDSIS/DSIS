package com.example.dsis;


import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.graphics.Color;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class TimetActivity extends Activity {
	WebView mWebView;
	WebSettings Wset;
	public TextView[] timeT;
	String[] sr;
	String[] sub;
	timeset set[];  // ���� �����ϴ� Ŭ����
	int state[];  // ���� ������ � �ִ°�
	
	int deviceWidth;
	int deviceHeight;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timet);
		
		//����̽� �ػ� ����
				DisplayMetrics displayMetrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
				deviceWidth = displayMetrics.widthPixels;
				deviceHeight =displayMetrics.heightPixels;
				
		
		timeT=new TextView[90];
		sr= new String[90];
		sub= new String[10];
		set = new timeset[90];
		state = new int[10];
		
		Log.d("web","set setting");
		
		for(int c = 0 ; c<90 ; c++){
			set[c] = new timeset();
			set[c].num = 0;
			set[c].num2 = 0;
			set[c].room = "";
			set[c].sub = "";
			set[c].day = 0;
			Log.d("web","set setting : "+c);
		}
		
		mWebView = (WebView) findViewById(R.id.wv_1); // ����� xml �� ����
		mWebView.setWebViewClient(new WebViewClient());
		Log.d("web","view1");
		
		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // �ڹٽ�ũ��Ʈ ���
		mWebView.setHorizontalScrollBarEnabled(false); //���� ��ũ��  
		mWebView.setVerticalScrollBarEnabled(false);

		Log.d("web","view2");
		
		timeT[0]=(TextView)findViewById(R.id.time1_1);
		timeT[1]=(TextView)findViewById(R.id.time1_2);
		timeT[2]=(TextView)findViewById(R.id.time1_3);
		timeT[3]=(TextView)findViewById(R.id.time1_4);
		timeT[4]=(TextView)findViewById(R.id.time1_5);
		
		timeT[5]=(TextView)findViewById(R.id.time2_1);
		timeT[6]=(TextView)findViewById(R.id.time2_2);
		timeT[7]=(TextView)findViewById(R.id.time2_3);
		timeT[8]=(TextView)findViewById(R.id.time2_4);
		timeT[9]=(TextView)findViewById(R.id.time2_5);
		
		timeT[10]=(TextView)findViewById(R.id.time3_1);
		timeT[11]=(TextView)findViewById(R.id.time3_2);
		timeT[12]=(TextView)findViewById(R.id.time3_3);
		timeT[13]=(TextView)findViewById(R.id.time3_4);
		timeT[14]=(TextView)findViewById(R.id.time3_5);
		
		timeT[15]=(TextView)findViewById(R.id.time4_1);
		timeT[16]=(TextView)findViewById(R.id.time4_2);
		timeT[17]=(TextView)findViewById(R.id.time4_3);
		timeT[18]=(TextView)findViewById(R.id.time4_4);
		timeT[19]=(TextView)findViewById(R.id.time4_5);
		
		timeT[20]=(TextView)findViewById(R.id.time5_1);
		timeT[21]=(TextView)findViewById(R.id.time5_2);
		timeT[22]=(TextView)findViewById(R.id.time5_3);
		timeT[23]=(TextView)findViewById(R.id.time5_4);
		timeT[24]=(TextView)findViewById(R.id.time5_5);
		
		timeT[25]=(TextView)findViewById(R.id.time6_1);
		timeT[26]=(TextView)findViewById(R.id.time6_2);
		timeT[27]=(TextView)findViewById(R.id.time6_3);
		timeT[28]=(TextView)findViewById(R.id.time6_4);
		timeT[29]=(TextView)findViewById(R.id.time6_5);
		
		timeT[30]=(TextView)findViewById(R.id.time7_1);
		timeT[31]=(TextView)findViewById(R.id.time7_2);
		timeT[32]=(TextView)findViewById(R.id.time7_3);
		timeT[33]=(TextView)findViewById(R.id.time7_4);
		timeT[34]=(TextView)findViewById(R.id.time7_5);
		
		timeT[35]=(TextView)findViewById(R.id.time8_1);
		timeT[36]=(TextView)findViewById(R.id.time8_2);
		timeT[37]=(TextView)findViewById(R.id.time8_3);
		timeT[38]=(TextView)findViewById(R.id.time8_4);
		timeT[39]=(TextView)findViewById(R.id.time8_5);
		
		timeT[40]=(TextView)findViewById(R.id.time9_1);
		timeT[41]=(TextView)findViewById(R.id.time9_2);
		timeT[42]=(TextView)findViewById(R.id.time9_3);
		timeT[43]=(TextView)findViewById(R.id.time9_4);
		timeT[44]=(TextView)findViewById(R.id.time9_5);
		
		timeT[45]=(TextView)findViewById(R.id.time10_1);
		timeT[46]=(TextView)findViewById(R.id.time10_2);
		timeT[47]=(TextView)findViewById(R.id.time10_3);
		timeT[48]=(TextView)findViewById(R.id.time10_4);
		timeT[49]=(TextView)findViewById(R.id.time10_5);
		
		timeT[50]=(TextView)findViewById(R.id.time11_1);
		timeT[51]=(TextView)findViewById(R.id.time11_2);
		timeT[52]=(TextView)findViewById(R.id.time11_3);
		timeT[53]=(TextView)findViewById(R.id.time11_4);
		timeT[54]=(TextView)findViewById(R.id.time11_5);
		
		timeT[55]=(TextView)findViewById(R.id.time12_1);
		timeT[56]=(TextView)findViewById(R.id.time12_2);
		timeT[57]=(TextView)findViewById(R.id.time12_3);
		timeT[58]=(TextView)findViewById(R.id.time12_4);
		timeT[59]=(TextView)findViewById(R.id.time12_5);
		
		timeT[60]=(TextView)findViewById(R.id.time13_1);
		timeT[61]=(TextView)findViewById(R.id.time13_2);
		timeT[62]=(TextView)findViewById(R.id.time13_3);
		timeT[63]=(TextView)findViewById(R.id.time13_4);
		timeT[64]=(TextView)findViewById(R.id.time13_5);
		
		timeT[65]=(TextView)findViewById(R.id.time14_1);
		timeT[66]=(TextView)findViewById(R.id.time14_2);
		timeT[67]=(TextView)findViewById(R.id.time14_3);
		timeT[68]=(TextView)findViewById(R.id.time14_4);
		timeT[69]=(TextView)findViewById(R.id.time14_5);
		
		timeT[70]=(TextView)findViewById(R.id.time15_1);
		timeT[71]=(TextView)findViewById(R.id.time15_2);
		timeT[72]=(TextView)findViewById(R.id.time15_3);
		timeT[73]=(TextView)findViewById(R.id.time15_4);
		timeT[74]=(TextView)findViewById(R.id.time15_5);
		
		timeT[75]=(TextView)findViewById(R.id.time16_1);
		timeT[76]=(TextView)findViewById(R.id.time16_2);
		timeT[77]=(TextView)findViewById(R.id.time16_3);
		timeT[78]=(TextView)findViewById(R.id.time16_4);
		timeT[79]=(TextView)findViewById(R.id.time16_5);
		
		timeT[80]=(TextView)findViewById(R.id.time17_1);
		timeT[81]=(TextView)findViewById(R.id.time17_2);
		timeT[82]=(TextView)findViewById(R.id.time17_3);
		timeT[83]=(TextView)findViewById(R.id.time17_4);
		timeT[84]=(TextView)findViewById(R.id.time17_5);
		
		timeT[85]=(TextView)findViewById(R.id.time18_1);
		timeT[86]=(TextView)findViewById(R.id.time18_2);
		timeT[87]=(TextView)findViewById(R.id.time18_3);
		timeT[88]=(TextView)findViewById(R.id.time18_4);
		timeT[89]=(TextView)findViewById(R.id.time18_5);
		
		MyJavaScriptInterface inter=new MyJavaScriptInterface(this);
		mWebView.addJavascriptInterface(inter,"HtmlViewer");
		
		Log.d("web","view3");
		
		mWebView.setOnTouchListener(new View.OnTouchListener() {	// ��ġ �����ʷ� ���� ��ġ �߻� �� ��ũ�� ����
			
		    public boolean onTouch(View v, MotionEvent event) {
		      return (event.getAction() == MotionEvent.ACTION_MOVE);
		    }
		  });
		
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				handler.proceed(); // SSL ������ �߻��ص� ��� ����!
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				mWebView.loadUrl("javascript:window.HtmlViewer.showHTML"
						+ "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
				Log.d("���ƾ�", "üũ2&&==");
			}
		});
		
		mWebView.loadUrl("http://student.donga.ac.kr/Univ/SUE/SSUE0020.aspx?m=3"); // ����� �л����� ȭ�� �ƿ�
	}
	
	class MyJavaScriptInterface {
		private TimetActivity activity;
		String sk;  // ���� �ȵ� �Ľ̳���
		int tb_num;
		String sn;	// �й�
		
		
		public MyJavaScriptInterface(TimetActivity activity) {
			Log.d("web","view0");
			this.activity = activity;
			sk=new String();
			sn=new String();
		}

		@android.webkit.JavascriptInterface
		public void showHTML(String html) {
			Log.d("web","view4");
			tb_num=0;
			Document doc = null;
			doc = Jsoup.parse(html);
			Elements rows = doc.select("#htblTime2 td");  			//id�� htblTime2 �� ���̺��� td�� �ܾ��
			Log.d("�Ľ� ����","�߾�");
			
			for(int a=0;a<10;a++){ sub[a]=null; }
			
			for(int c = 0 ; c<90 ; c++){
				set[c].num = 0;
				set[c].num2 = 0;
				set[c].room = "";
				set[c].sub = "";
				set[c].day = 0;
			}
			
			for(int c=21 ; c<146; c++){ 							// ���������� �Ѿ  ////////c�� 3���� �ϴ� ������ 0�� ���� ���̰� 1��2�� ���� �������.
				
				if(deviceHeight == 2560 && deviceWidth == 1440)
					mWebView.scrollTo(1,1); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 2560*1440 ��5
            	
            	else if(deviceHeight == 2392 && deviceWidth == 1440)
             		mWebView.scrollTo(1,1); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 2392*1440 G3
            	
            	else if(deviceHeight == 1920 && deviceWidth == 1080)
            		mWebView.scrollTo(555,905); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1920*1080 ��Ʈ 3
            	
            	else if(deviceHeight == 1776 && deviceWidth == 1080)
            		mWebView.scrollTo(555,955); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1776*1080 G2
            	
            	else if(deviceHeight == 1280 && deviceWidth == 768)
            		mWebView.scrollTo(370,605); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1280*768 �� G
            	
            	else if(deviceHeight == 1280 && deviceWidth == 720)
            		mWebView.scrollTo(370,600); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1280*720 ��Ʈ 2
            	
            	else if(deviceHeight == 1184 && deviceWidth == 720)
            		mWebView.scrollTo(1,1); // ������Ʈ �α��� ȭ�� ���� ��ǥ ���� 1184*720  ����R3
				
				
				if((c % 7)==0)  									// �ð� ������ִ� ������Ʈ�� ����
				{
					Log.d("���ƾ�", "�ð�");					
					continue;
				}
				
				else if((c%7) == 6)   // ����� ����.
				{
					Log.d("���ƾ�", "���");
					continue;
				}
				
				else if((rows.get(c).toString().length()) < 50){    // ������ ��� �ƹ� ���� ���� ������Ʈ�� ��ĭ �־���
					tb_num++;
				}
					
				else{												// �ð� ���� ������Ʈ�� �ƴϰ� ������ ��� ��ĭ�� ������Ʈ�� �ƴ� �����ִ� ������Ʈ��
					sk= rows.get(c).toString();
					StringTokenizer s = new StringTokenizer(sk); 
					/*
					 * <TD class="td20" valign="top" width="115">
					 * GEE029-01&nbsp;&nbsp;������<br><a href="javascript:windowOpenPreKyoCuri('2013','20','GEE029','01');">
					 * ����濵</a><br>RS709</TD>
					*/
					
					s.nextToken("-");
					sn=s.nextToken("&");  	//�й� �߶�
					
					/*
					s.nextToken(";");
					s.nextToken(";");
					tk[tb_num]+=s.nextToken("<");	//������ �̸� �߶��
					*/
					
					s.nextToken(">");
					s.nextToken(">");
					sr[tb_num]=s.nextToken("<");			//���� �̸� �ڸ�
					sr[tb_num]=sr[tb_num].substring(1);
					
					set[tb_num].sub = sr[tb_num].trim();  //trim >> ��Ʈ���� ���� �� ���� �߶󳻴°�

					for(int a=0; a<10 ;a++){		//����Ʈ�� ������ �ִ´�
						if(sub[a]==null){
							sub[a]=sr[tb_num].trim();  //����� �����ؼ� ����
							set[tb_num].num=1;
							break;
						}
						else if(sub[a].equals(set[tb_num].sub)){				//�ߺ��ȰŸ� �Ѿ��.
							Log.d("����","����   : "+tb_num);
							
							if(tb_num>4 && set[tb_num-5].sub.equals(set[tb_num].sub)){  // �� ���ÿ� ���� �����϶� ���� ī���� ++
								Log.d("tb_num","�߰� "+ set[tb_num].sub +" // tb_num : "+tb_num);
								
								set[tb_num].num=set[tb_num-5].num+1;
								break;
							}else{ 			// ù�ð� �Ǵ³��
								Log.d("tb_num","ù�ð� "+ set[tb_num].sub +" // tb_num : "+tb_num);
								
								set[tb_num].num=1;
								break;
							}
						}
					}
					
					switch(tb_num%5){
					case 0:
						set[tb_num].day = 1;		// ��
						break;
					case 1:
						set[tb_num].day = 2;		// ȭ
						break;
					case 2:
						set[tb_num].day = 3;		// �� 
						break;
					case 3:
						set[tb_num].day = 4;		// ��
						break;
					case 4:
						set[tb_num].day = 5;		// ��
						break;
					}
					
					s.nextToken(">");
					s.nextToken(">");
					set[tb_num].room=s.nextToken("<").substring(1)+sn;	// ���ǽ� �ڸ� +�й� ���� (sn)
					
					tb_num++;
				}
				Log.d("tk","tb_num : "+tb_num);
			}
			
			for(int a=0;a<10;a++){
				if(sub[a]==null){
					break;
				}
				Log.d("sub",sub[a]);
				
			}
			
			Log.d("web","view9");
			
			backthread thread = new backthread();
			thread.setDaemon(true);
			thread.start();							// ui�� �ð�ǥ �ִ� ������ ����
			
		}
	}
	
	class backthread extends Thread{
		public void run(){
			mHandler.sendEmptyMessage(0);
		}
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(Message mag){
			if(mag.what==0){
				int a=0;
				int turn=0; // 1�̸� ������ ȸ������ 0�̸� ������ ��ȸ�� ����
				
				for(int c=0 ; c<90; c++){ 				// ���������� �Ѿ  ////////c�� 3���� �ϴ� ������ 0�� ���� ���̰� 1��2�� ���� �������.
					Log.d("thread for",""+c);
					timeT[c].setText("");
					
					if(c%5==0){		// ���پ� turn������ �ٲ���
						switch(turn){
						case 0:
							turn = 1;
							break;
						case 1:
							turn = 0;
							break;
						}
					}
					
					if(set[c].sub.length()<2){ 				// ������ ��� �ƹ� ���� ���� ������Ʈ�� ��ĭ �־���
						Log.d("textview", "����");
						//timeT[c].setText("");
						if(turn==1){ 		//��ȸ�� ����
							timeT[c].setBackgroundColor(Color.parseColor("#eeeeee"));  //eeeeee
						}
						if(turn==0){			//ȸ�� ����
							timeT[c].setBackgroundColor(Color.parseColor("#ffffff"));  //ffffff
						}
					}
							
					else{								// �ð� ���� ������Ʈ�� �ƴϰ� ������ ��� ��ĭ�� ������Ʈ�� �ƴ� �����ִ� ������Ʈ��
						Log.d("textview", "����");
						
						if(set[c].num==1){
							Log.d("timeTable", set[c].room+set[c].sub);
							timeT[c].setText(set[c].room+"\n"+set[c].sub);
						}
						
						for(a=0; a<10; a++){
							if(sr[c].equals(sub[a])){		//sub����� sr������ ���� ������ sub���� ����(a�ε����� ����)�� ��� �ٲ�
								break;
							}
							
							if(set[c].num == state[a]){  // ������ ������
								if(set[c].num2>1 && set[c].num2!=2){  // ������ 3���� �̻��ϋ�
									
								}
								if(set[c].num2==2){
									
								}
							}
						}
						
						switch(a){
						case 0:
							timeT[c].setBackgroundColor(Color.parseColor("#FFCBCB")); //��
							break;
						case 1:
							timeT[c].setBackgroundColor(Color.parseColor("#FFD3B0"));  //��
							break;
						case 2:
							timeT[c].setBackgroundColor(Color.parseColor("#D6F0FF")); //��
							break;
						case 3:
							timeT[c].setBackgroundColor(Color.parseColor("#FFFFA1")); //��
							break;
						case 4:
							timeT[c].setBackgroundColor(Color.parseColor("#F5D6FF")); //��
							break;
						case 5:
							timeT[c].setBackgroundColor(Color.parseColor("#BCFFB5")); //��
							break;
						case 6:
							timeT[c].setBackgroundColor(Color.parseColor("#D6FFFF")); //�ϴ�
							break;
						case 7:
							timeT[c].setBackgroundColor(Color.parseColor("#E8D9FF"));  //����
							break;
						case 8:
							timeT[c].setBackgroundColor(Color.parseColor("#D8D8D8"));  //ȸ
							break;
						case 9:
							timeT[c].setBackgroundColor(Color.parseColor("#FFD6FF")); //��ȫ
							break;
						}
					}
				}
			}
		}
	};
	
	public void save(View v) 
	{ 		// ���崩���� �佺Ʈ�� �����Ҳ��İ� �ϳ��� �ȴٰ� ����ְ� ����
//		if(mWebView.getUrl()!="http://student.donga.ac.kr/Univ/SUE/SSUE0020.aspx?m=3" ){
//		}
		
	}
	
	public void opem(View v) 
	{	// ���� ������ ���� �ֳ� Ȯ���ϰ� ������ �о�ͼ� �ٷ� �ð�ǥ �����  ������ ���� ���ٰ� �佺Ʈ.
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timet, menu);
		return true;
	}
}
