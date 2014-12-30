package com.example.dsis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.dsis.TimetActivity.MyJavaScriptInterface;
import com.example.dsis.TimetActivity.backthread;

import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Logmark extends Activity {
	WebView mWebView;
	WebSettings Wset;
	TextView student2;
	TextView student1;
	ListView listView;
	
	ArrayList<customView> data_list;
	CustomAdapter customAdapter;
	customView temp;
	Context context;
	
	String selectedItem;
	String selectedyear;
	public int num=1;
	int sel=0;
	
	String text;
	String name;
	int ok=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_mark);
		
		mWebView = (WebView) findViewById(R.id.web); // 웹뷰와 xml 간 연동
		mWebView.setWebViewClient(new WebViewClient());
		Log.d("web","view1");
		
		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // 자바스크립트 허용
		mWebView.setHorizontalScrollBarEnabled(false); //가로 스크롤  
		mWebView.setVerticalScrollBarEnabled(false);
		//edit = (TextView) findViewById(R.id.grade);
		
		student2 = (TextView)findViewById(R.id.student2);
		student1 = (TextView)findViewById(R.id.student1);
		listView = (ListView)findViewById(R.id.listView);
		
		text = new String();
		
		context = this;
		
		ArrayList array1= new ArrayList<String>();
		ArrayList array2= new ArrayList<String>();
		
		data_list = new ArrayList<customView>();
		
		array1.add("1학기");						//10
		array1.add("계절학기(하계)");				//11
		array1.add("2학기");						//20
		array1.add("계절학기(동계)");				//21

		selectedItem = new String();
		selectedyear = new String();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
							(this,android.R.layout.simple_spinner_dropdown_item,array1);
		Spinner sp = (Spinner)findViewById(R.id.spinner2);
		sp.setPrompt("학기를 선택하세요");
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {		// 학기
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				* 스피너 선택 리스너
				*/
				// 선택한 아이템 
				selectedItem=parent.getItemAtPosition(position).toString();
				sel = parent.getSelectedItemPosition();
				
				switch(sel){
				case 0:
					sel=10;
					break;
				case 1:
					sel=11;
					break;
				case 2:
					sel=20;
					break;
				case 3:
					sel=21;
					break;
				}
				
				Log.d("파싱 했음", Integer.toString(sel) );
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {	
				/*
				* 스피너 선택 안했을 때 리스너
				*/
				// TODO Auto-generated method stub
			}
		});
		
		Calendar cal=Calendar.getInstance();
		int year = Integer.parseInt(cal.getTime().toString().substring(30));
		
		for(int a=0; a<20; a++){
			array2.add(Integer.toString(year));
			year--;
		}
		
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
								(this,android.R.layout.simple_spinner_dropdown_item,array2);
		Spinner sp2 = (Spinner)findViewById(R.id.spinner1);
		sp2.setPrompt("학년도를 선택하세요");
		sp2.setAdapter(adapter1);
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
				/*
				* 스피너 선택 리스너
				*/
				// 선택한 아이템 
				selectedyear=parent.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {	
				/*
				* 스피너 선택 안했을 때 리스너
				*/
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Nothing", 
					Toast.LENGTH_SHORT).show();
			}
		});
		
		findViewById(R.id.ra1).setOnClickListener(new Button.OnClickListener(){  //전체성적 선택
			public void onClick(View v){ //전체성적
				num=1;
				/*
				Toast.makeText(getApplicationContext(), "전체", 
						0).show();*/
			}
		});
		findViewById(R.id.ra2).setOnClickListener(new Button.OnClickListener(){  //누계성적 선택
			public void onClick(View v){
				num=2;
				/*Toast.makeText(getApplicationContext(), "누계", 
						0).show();*/
			}
		});
		findViewById(R.id.ra3).setOnClickListener(new Button.OnClickListener(){  //일부성적 선택
			public void onClick(View v){
				num=3;				
				/*Toast.makeText(getApplicationContext(), "일부", 
						0).show();*/
			}
		});			
	}
	
	class MyJavaScriptInterface {
		private Logmark activity;
		
		
		public MyJavaScriptInterface(Logmark activity) {
			this.activity = activity;
			Log.d("web","파싱 1");
		}

		@android.webkit.JavascriptInterface
		public void showHTML(String html) {
			Log.d("web","view4");
			Document doc = null;
			doc = Jsoup.parse(html);
			String sk;
			String hj;
			data_list.clear();
			
			String st1=new String();
			String st2=new String();
			String st3=new String();
			String st4=new String();
			String st5=new String();
			String st6=new String();
			
			Elements rows1=null;  			//id가 htblTime2 인 테이블의 td를 긁어옴
			Elements rows2=null;  			
			Elements rows3=null;  			// 
			Elements rows4=null;  			//
			int a=0;
			
			rows4 = doc.select("#Table1 td");        // 개인 정보 출력하는 부분
			name ="";
			for(int e=1; e<12; e++){
				if(e==1 || e==3 || e==7 || e==9 || e==11){
					sk= rows4.get(e).toString();
					StringTokenizer s0 = new StringTokenizer(sk);
					s0.nextToken(";").substring(1);
					
					if (e==9){
						name += "\n";
					}
					else if(e== 11 && e==7)
					{
						name+=s0.nextToken("<").substring(1);
						break;
					}
					name+=s0.nextToken("<").substring(1)+ "  ";
				}
			}
			
			Log.d("web","for 들어간다");
			
			if(num==1){											// 전체학기
					rows1 = doc.select("#dgList1 td");
					rows2 = doc.select("#lblCdtPass");  // 취득학점
					rows3 = doc.select("#lblGpa");		// 학점평균
					Log.d("showHTML", "긁음" );
					
					sk= rows2.get(0).toString();
					StringTokenizer s0 = new StringTokenizer(sk); 
					s0.nextToken("취");
					
					text = s0.nextToken("<");
					
					sk= rows3.get(0).toString();
					StringTokenizer s1 = new StringTokenizer(sk); 
					s1.nextToken("평");
					hj = s1.nextToken("&");
					s1.nextToken("(");
					hj +=" "+s1.nextToken("<");
					
					text = text+"  "+ hj;
					Log.d("파싱 했음",text);
					
					a=rows1.size();
					Log.d("파싱 했음",Integer.toString(a)+"개");
					
					/* 년도 / 학기 / 교과목번호 / 교과목명 / 이수구분 / 학점 / 성적               >> 7개 한세트
					 * 5칸       3칸     pass        
					 * 
					 */
					
					temp =new customView("년도","학기","교과목명","이수구분","학점","성적");
					Log.d("전체성적","리스트 추가전");
					
					data_list.add(temp);
					
					Log.d("전체성적","리스트 추가1");
					
					for(int e=0 ; e<a ; e++){
						sk=rows1.get(e).toString();
						StringTokenizer s2 = new StringTokenizer(sk); 
						hj=null;
						if(e%7==2){ // 교과목 번호 생략
							continue;
						}
						else if(e<7){  // 제일 처음 메뉴 부분 (년도 / 학기 / 교과목번호 / 교과목명 / 이수구분 / 학점 / 성적 ) 에서 교과목 번호는 생략
							continue;
						}
						else if(e%7==0){  // 년도   //줄띄움
							s2.nextToken(">");
							s2.nextToken(">");
							
							st1=s2.nextToken("<").substring(1);
							continue;
						}
						else if(e%7==1){   // 학기
							s2.nextToken(">");
							s2.nextToken(">");
							st2=s2.nextToken("<").substring(1);
							continue;
						}
						else if(e%7==3){		// 과목명
							s2.nextToken(">");
							s2.nextToken(">");
							st3=s2.nextToken("<").substring(1);
							continue;
						}
						
						s2.nextToken(">");
						s2.nextToken(">");
						
						switch(e%7){
						case 4:	//이수구분
							st4=s2.nextToken("<").substring(1);
							break;
						case 5:	// 학점
							st5=s2.nextToken("<").substring(1);
							break;
						case 6:	//성적
							st6=s2.nextToken("<").substring(1);
							
							temp = new customView(st1,st2,st3,st4,st5,st6);
							data_list.add(temp);
							
							break;
						}
					}
			}
			
			else if(num==2){
				rows1 = doc.select("#dgList1 td");
				
				a=rows1.size();
				
				temp =new customView("년도","학기","신청학점","취득학점","평점","");
				data_list.add(temp);
				
				for(int e=5; e<a ;e++){
					sk=rows1.get(e).toString();
					StringTokenizer s2 = new StringTokenizer(sk); 
					s2.nextToken(">");
					s2.nextToken(">");
					
					if(e%5==0){ // 년도
						st1=s2.nextToken("<").substring(1);
						if(e==(a-5)){  //마지막 합계 row는 "계"
							st1="계";
							continue;
						}
						continue;
					}
					else if(e%5==1){  //학기
						st2=s2.nextToken("<").substring(1);
						if(e==(a-4)){  //마지막 합계 row는 빈칸으로
							st2="";
							continue;
						}
					}
					else if(e%5==2){ //신청학점
						st3=s2.nextToken("<").substring(1);
						continue;
					}
					else if(e%5==3){  //취득학점
						st4=s2.nextToken("<").substring(1);
					}
					else if(e%5==4){   //평점
						st5=s2.nextToken("<").substring(2);
						temp = new customView(st1,st2,st3,st4,st5,"");
						data_list.add(temp);
						continue;
					}
				}
			}
			
			else if(num==3){
				rows1 = doc.select("#dgList1 td"); 		//성적
				rows2 = doc.select("#lblCdtPass"); 		// 취득 학점 
				rows3 = doc.select("#lblGpa");		 	// 학점 평균
				rows4 = doc.select("#lblRanking");		// 석차
				
				a=rows1.size();
				
				if(a<=9){ 		// 성적 없는 년도 조회시
					text = "취득 학점이 없습니다.";
					st1 = "    ";
					st2 = "";
					st3 = selectedyear+"년도";
					st5 = "";
					st6 = "";
					switch(sel){
					case 10:
						st4 = "1학기";
						break;
					case 11:
						st4 = "계절학기(하계)";
						break;
					case 20:
						st4 = "2학기";
						break;
					case 21:
						st4 = "계절학기(동계)";
						break;
					}
					temp = new customView(st1,st2,st3,st4,st5,st6);
					data_list.add(temp);
					
					st3 = "성적 내역이";
					st4 = "없습니다.";
					temp = new customView(st1,st2,st3,st4,st5,st6);
					data_list.add(temp);
				}
				
				else{
					sk= rows2.get(0).toString();
					StringTokenizer s0 = new StringTokenizer(sk); 
					s0.nextToken("취");
					text = s0.nextToken("<");
	
					sk= rows3.get(0).toString();
					StringTokenizer s1 = new StringTokenizer(sk); 
					s1.nextToken("평");
					text +="   "+s1.nextToken("<");
					
					sk= rows4.get(0).toString();
					StringTokenizer s2 = new StringTokenizer(sk); 
					s2.nextToken("석");
					
					text +="   "+s2.nextToken("<")+"\n\n 평점 : A+(4.5), A(4.0), " +
							"B+(3.5), B(3.0), C+(2.5), C(2.0), D+(1.5), D(1.0), F(0.0)";
					
					temp =new customView("년도","학기","교과목명","이수구분","학점","성적");
					data_list.add(temp);
					
					for(int e=9 ; e<a ; e++){  				//한 줄에 9개의 td가 있다
						// 0:년도 	 1:학기  	2:교과목 번호   	3:교과목명 	 4:이수구분  	5:학점 	 6:성적  	7:평점   	8:평점*학점
						//   *     *                   *         *         *       * 
						
						sk=rows1.get(e).toString();
						StringTokenizer s3 = new StringTokenizer(sk); 
						
						if(e==9 || e==10){ 			// 맨처음 년도 학기 표시
							s3.nextToken(">");
							s3.nextToken(">");
							if(e==9){
								st1=s3.nextToken("<").substring(1);
							}
							else{
								st2=s3.nextToken("<").substring(1);
							}
							continue;
						}
						else if((e%9)==2||(e%9)==7||(e%9)==8)  		// 교과목번호 , 평점 , 평점*학점 생략
							continue;
						
						switch((e%9)){
						case 0:			//처음 row에 년도 표현후 이후는 빈칸으로 출력
							st1="";
							break;
						case 1:			//처음 row에 학기 표현후 이후는 빈칸으로 출력
							st2="";
							break;
						case 3:  		// 교과목명
							s3.nextToken(">");
							s3.nextToken(">");
							st3=s3.nextToken("<").substring(1);
							break;
						case 4:  		// 이수구분
							s3.nextToken(">");
							s3.nextToken(">");
							st4=s3.nextToken("<").substring(1);
							break;
						case 5:  		// 학점
							s3.nextToken(">");
							s3.nextToken(">");
							st5=s3.nextToken("<").substring(1);
							break;
						case 6:  		// 성적
							s3.nextToken(">");
							s3.nextToken(">");
							st6=s3.nextToken("<").substring(1);
							
							temp = new customView(st1,st2,st3,st4,st5,st6);
							data_list.add(temp);
							break;
						}
					}
				}
			}
			
			Log.d("wep parse","adapter before");
			customAdapter = new CustomAdapter(context,R.layout.custom, data_list);
			Log.d("wep parse","adapter after");
			
			backthread thread = new backthread();
			thread.setDaemon(true);
			thread.start();							// ui에 시간표 넣는 스레드 돌림
		}
	}
	
	////// 핸들 쓰레드 사용     UI  변경
	class backthread extends Thread{
		public void run(){
			if(ok==1){
				Log.d("thread","run");
				mHandler.sendEmptyMessage(0);
				ok=0;
			}
		}
	}
	
	Handler mHandler = new Handler(){
		public void handleMessage(Message mag){
			if(mag.what==0){
				Log.d("thread","run gogo");
				student2.setText("");
				
				student1.setText(name);
				listView.setAdapter(customAdapter);
				Log.d("wep parse","!!!set adapter after");
				if(num!=2){
					student2.setText(text);
				}
			}
		}
	};
	
	/// 확인 버튼 
	public void search(View v) {
		
		MyJavaScriptInterface inter=new MyJavaScriptInterface(this);
		mWebView.addJavascriptInterface(inter,"HtmlViewer");
		
		Log.d("web","view3");
		mWebView.setOnTouchListener(new View.OnTouchListener() {	// 터치 리스너로 무빙 터치 발생 시 스크롤 없게
		    public boolean onTouch(View v, MotionEvent event) {
		      return (event.getAction() == MotionEvent.ACTION_MOVE);
		    }
		  });
		
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
				Log.d("으아아", "체크2&&==");
			}
		});
		
		student2.setText("");
		
		ok=1;
		switch(num){  // 여기서 url을 정해주면  showHTML메서드가 싱행된다
		case 1:
			Log.d("check", "전체 성적");
			mWebView.loadUrl("https://student.donga.ac.kr/Univ/SUH/SSUH0011.aspx?m=6&rbtn=1");  //전체
			break;
		case 2:
			Log.d("check", "누계성적");
			mWebView.loadUrl("https://student.donga.ac.kr/Univ/SUH/SSUH0014.aspx?m=6&rbtn=4"); // 누계
			break;
		case 3:
			Log.d("check", "부분 성적");
			mWebView.loadUrl("http://student.donga.ac.kr/Univ/SUH/SSUH0012.aspx?m=6&rbtn=2&year="+selectedyear+"&smt="+sel); // 일부
			break;		
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logmark , menu);
		return true;
	}
}
