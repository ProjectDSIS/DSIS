package com.example.dsis;

import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.dsis.TimetActivity.MyJavaScriptInterface;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.SslErrorHandler;
import android.widget.TextView;
import android.net.http.SslError;

public class Student extends Activity {

	WebView mWebView, picWebView;
	WebSettings Wset;
	public TextView[] text;
	String[] str;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student);
		
		text = new TextView[24];
		str = new String[30];
		
		picWebView = (WebView) findViewById(R.id.picture);
		picWebView.setWebViewClient(new WebViewClient());
		
		mWebView = (WebView) findViewById(R.id.webView1); // 웹뷰와 xml 간 연동
		mWebView.setWebViewClient(new WebViewClient());
		Log.d("web","view1");
		
		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // 자바스크립트 허용
		mWebView.setHorizontalScrollBarEnabled(false); //가로 스크롤  
		mWebView.setVerticalScrollBarEnabled(false);
		
		text[0]=(TextView)findViewById(R.id.text0);
		text[1]=(TextView)findViewById(R.id.text1);
		text[2]=(TextView)findViewById(R.id.text2);
		text[3]=(TextView)findViewById(R.id.text3);
		text[4]=(TextView)findViewById(R.id.text4);
		
		text[5]=(TextView)findViewById(R.id.text5);
		text[6]=(TextView)findViewById(R.id.text6);
		text[7]=(TextView)findViewById(R.id.text7);
		text[8]=(TextView)findViewById(R.id.text8);
		text[9]=(TextView)findViewById(R.id.text9);
		
		text[10]=(TextView)findViewById(R.id.text10);
		text[11]=(TextView)findViewById(R.id.text11);
		text[12]=(TextView)findViewById(R.id.text12);
		text[13]=(TextView)findViewById(R.id.text13);
		text[14]=(TextView)findViewById(R.id.text14);
		
		text[15]=(TextView)findViewById(R.id.text15);
		text[16]=(TextView)findViewById(R.id.text16);
		text[17]=(TextView)findViewById(R.id.text17);
		text[18]=(TextView)findViewById(R.id.text18);
		text[19]=(TextView)findViewById(R.id.text19);
		
		text[20]=(TextView)findViewById(R.id.text20);
		text[21]=(TextView)findViewById(R.id.text21);
		text[22]=(TextView)findViewById(R.id.text22);
		text[23]=(TextView)findViewById(R.id.text23);
		
		MyJavaScriptInterface inter=new MyJavaScriptInterface(this);
		mWebView.addJavascriptInterface(inter,"HtmlViewer");
		
		/*
		mWebView.setOnTouchListener(new View.OnTouchListener() {	// 터치 리스너로 무빙 터치 발생 시 스크롤 없게
		    public boolean onTouch(View v, MotionEvent event) {
		      return (event.getAction() == MotionEvent.ACTION_MOVE);
		    }
		  });
		*/
		
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				handler.proceed(); // SSL 에러가 발생해도 계속 진행!
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				mWebView.loadUrl("javascript:window.HtmlViewer.showHTML"
						+ "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
				Log.d("학적부", "체크2");
			}
		});
		
		Log.d("학적부", "체크3");
		mWebView.loadUrl("https://student.donga.ac.kr/Univ/SUD/SSUD0000.aspx?m=1");
		Log.d("학적부", "체크4");
		picWebView.loadUrl("https://student.donga.ac.kr/Univ/SUD/SSUD0022.aspx");
	}

	
	class MyJavaScriptInterface {
		private Student activity;
		int a;
		int i;
		String sk;
		
		public MyJavaScriptInterface(Student activity) {
			this.activity = activity;
		}

		@android.webkit.JavascriptInterface
		public void showHTML(String html) {
			a=0;
			i=0;
			mWebView.scrollTo(500,550);
			Document doc = null;
			doc = Jsoup.parse(html);
			Elements rows1 = doc.select("#Table4 td");  			//id가 Table4 인 테이블의 td를 긁어옴
			Elements rows2 = doc.select("#Table5 td");  			//id가 Table5 인 테이블의 td를 긁어옴
			Elements rows3 = doc.select("#Table7 td");  			//id가 Table7 인 테이블의 td를 긁어옴
			
			Log.d("학적부", "체크5 - elements입력완료");
			
			//a=rows1.size();
			
			for(int e=0; e<27 ; e++){
				if(e==1 || e==2){  // 학번 이름
					sk= rows1.get(e).toString();
					StringTokenizer s0 = new StringTokenizer(sk); 
					
					s0.nextToken(">");
					s0.nextToken(">");
					if(e==2){
						str[0]=str[0]+"  "+s0.nextToken("<").substring(1);
						//i++;
					}
					else{
						str[0]=" "+s0.nextToken("<").substring(1);
					}
					
					continue;
				}
				if(e%2==0){
					sk= rows1.get(e).toString();
					StringTokenizer s0 = new StringTokenizer(sk); 
					
					s0.nextToken(">");
					s0.nextToken(">");
					str[i]=" "+s0.nextToken("<").substring(1);
					i++;
				}
			}
			
			//a=rows2.size();
			//i--;
			for(int e=0; e<18 ; e++){
				if(e%2==1){
					sk= rows2.get(e).toString();
					StringTokenizer s0 = new StringTokenizer(sk); 
					
					s0.nextToken(">");
					s0.nextToken(">");
					str[i]=" "+s0.nextToken("<").substring(1);
					i++;
				}
				
			}
			
			//a=rows3.size();
			
			for(int e=26; e<29 ; e++){ 
				if(e%2==0){
					sk= rows3.get(e).toString();
					StringTokenizer s0 = new StringTokenizer(sk);
					
					
					s0.nextToken(">");
					s0.nextToken(">");
					str[i]=" "+s0.nextToken("<").substring(1);
					i++;
				}
			}
			
			Log.d("학적부", "체크6 - 스트링 잘라내기 완료");
			
			backthread thread = new backthread();
			thread.setDaemon(true);
			thread.start();							// ui에 시간표 넣는 스레드 돌림
			
			Log.d("학적부", "thread start");
		}
	}
	
	
	
	
	
	class backthread extends Thread{
		public void run(){
			Log.d("학적부", "체크7 - 스레드 들어옴");
			mHandler.sendEmptyMessage(0);
		}
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(Message mag){
			if(mag.what==0){
				Log.d("학적부", "체크8 - 핸들러 사용");
				mWebView.scrollTo(500,550);
				Log.d("학적부", "화면고정");
				
				for(int e=0; e<24 ; e++){
					if(str[e].length()<2){
						text[e].setText(" -");
					}
					else{
						text[e].setText(str[e]);
					}
				}
				
			}
		}
	};
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student, menu);
		return true;
	}

}
