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
		
		mWebView = (WebView) findViewById(R.id.web); // ����� xml �� ����
		mWebView.setWebViewClient(new WebViewClient());
		Log.d("web","view1");
		
		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // �ڹٽ�ũ��Ʈ ���
		mWebView.setHorizontalScrollBarEnabled(false); //���� ��ũ��  
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
		
		array1.add("1�б�");						//10
		array1.add("�����б�(�ϰ�)");				//11
		array1.add("2�б�");						//20
		array1.add("�����б�(����)");				//21

		selectedItem = new String();
		selectedyear = new String();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
							(this,android.R.layout.simple_spinner_dropdown_item,array1);
		Spinner sp = (Spinner)findViewById(R.id.spinner2);
		sp.setPrompt("�б⸦ �����ϼ���");
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {		// �б�
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				/*
				* ���ǳ� ���� ������
				*/
				// ������ ������ 
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
				
				Log.d("�Ľ� ����", Integer.toString(sel) );
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {	
				/*
				* ���ǳ� ���� ������ �� ������
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
		sp2.setPrompt("�г⵵�� �����ϼ���");
		sp2.setAdapter(adapter1);
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
				/*
				* ���ǳ� ���� ������
				*/
				// ������ ������ 
				selectedyear=parent.getItemAtPosition(position).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {	
				/*
				* ���ǳ� ���� ������ �� ������
				*/
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Nothing", 
					Toast.LENGTH_SHORT).show();
			}
		});
		
		findViewById(R.id.ra1).setOnClickListener(new Button.OnClickListener(){  //��ü���� ����
			public void onClick(View v){ //��ü����
				num=1;
				/*
				Toast.makeText(getApplicationContext(), "��ü", 
						0).show();*/
			}
		});
		findViewById(R.id.ra2).setOnClickListener(new Button.OnClickListener(){  //���輺�� ����
			public void onClick(View v){
				num=2;
				/*Toast.makeText(getApplicationContext(), "����", 
						0).show();*/
			}
		});
		findViewById(R.id.ra3).setOnClickListener(new Button.OnClickListener(){  //�Ϻμ��� ����
			public void onClick(View v){
				num=3;				
				/*Toast.makeText(getApplicationContext(), "�Ϻ�", 
						0).show();*/
			}
		});			
	}
	
	class MyJavaScriptInterface {
		private Logmark activity;
		
		
		public MyJavaScriptInterface(Logmark activity) {
			this.activity = activity;
			Log.d("web","�Ľ� 1");
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
			
			Elements rows1=null;  			//id�� htblTime2 �� ���̺��� td�� �ܾ��
			Elements rows2=null;  			
			Elements rows3=null;  			// 
			Elements rows4=null;  			//
			int a=0;
			
			rows4 = doc.select("#Table1 td");        // ���� ���� ����ϴ� �κ�
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
			
			Log.d("web","for ����");
			
			if(num==1){											// ��ü�б�
					rows1 = doc.select("#dgList1 td");
					rows2 = doc.select("#lblCdtPass");  // �������
					rows3 = doc.select("#lblGpa");		// �������
					Log.d("showHTML", "����" );
					
					sk= rows2.get(0).toString();
					StringTokenizer s0 = new StringTokenizer(sk); 
					s0.nextToken("��");
					
					text = s0.nextToken("<");
					
					sk= rows3.get(0).toString();
					StringTokenizer s1 = new StringTokenizer(sk); 
					s1.nextToken("��");
					hj = s1.nextToken("&");
					s1.nextToken("(");
					hj +=" "+s1.nextToken("<");
					
					text = text+"  "+ hj;
					Log.d("�Ľ� ����",text);
					
					a=rows1.size();
					Log.d("�Ľ� ����",Integer.toString(a)+"��");
					
					/* �⵵ / �б� / �������ȣ / ������� / �̼����� / ���� / ����               >> 7�� �Ѽ�Ʈ
					 * 5ĭ       3ĭ     pass        
					 * 
					 */
					
					temp =new customView("�⵵","�б�","�������","�̼�����","����","����");
					Log.d("��ü����","����Ʈ �߰���");
					
					data_list.add(temp);
					
					Log.d("��ü����","����Ʈ �߰�1");
					
					for(int e=0 ; e<a ; e++){
						sk=rows1.get(e).toString();
						StringTokenizer s2 = new StringTokenizer(sk); 
						hj=null;
						if(e%7==2){ // ������ ��ȣ ����
							continue;
						}
						else if(e<7){  // ���� ó�� �޴� �κ� (�⵵ / �б� / �������ȣ / ������� / �̼����� / ���� / ���� ) ���� ������ ��ȣ�� ����
							continue;
						}
						else if(e%7==0){  // �⵵   //�ٶ��
							s2.nextToken(">");
							s2.nextToken(">");
							
							st1=s2.nextToken("<").substring(1);
							continue;
						}
						else if(e%7==1){   // �б�
							s2.nextToken(">");
							s2.nextToken(">");
							st2=s2.nextToken("<").substring(1);
							continue;
						}
						else if(e%7==3){		// �����
							s2.nextToken(">");
							s2.nextToken(">");
							st3=s2.nextToken("<").substring(1);
							continue;
						}
						
						s2.nextToken(">");
						s2.nextToken(">");
						
						switch(e%7){
						case 4:	//�̼�����
							st4=s2.nextToken("<").substring(1);
							break;
						case 5:	// ����
							st5=s2.nextToken("<").substring(1);
							break;
						case 6:	//����
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
				
				temp =new customView("�⵵","�б�","��û����","�������","����","");
				data_list.add(temp);
				
				for(int e=5; e<a ;e++){
					sk=rows1.get(e).toString();
					StringTokenizer s2 = new StringTokenizer(sk); 
					s2.nextToken(">");
					s2.nextToken(">");
					
					if(e%5==0){ // �⵵
						st1=s2.nextToken("<").substring(1);
						if(e==(a-5)){  //������ �հ� row�� "��"
							st1="��";
							continue;
						}
						continue;
					}
					else if(e%5==1){  //�б�
						st2=s2.nextToken("<").substring(1);
						if(e==(a-4)){  //������ �հ� row�� ��ĭ����
							st2="";
							continue;
						}
					}
					else if(e%5==2){ //��û����
						st3=s2.nextToken("<").substring(1);
						continue;
					}
					else if(e%5==3){  //�������
						st4=s2.nextToken("<").substring(1);
					}
					else if(e%5==4){   //����
						st5=s2.nextToken("<").substring(2);
						temp = new customView(st1,st2,st3,st4,st5,"");
						data_list.add(temp);
						continue;
					}
				}
			}
			
			else if(num==3){
				rows1 = doc.select("#dgList1 td"); 		//����
				rows2 = doc.select("#lblCdtPass"); 		// ��� ���� 
				rows3 = doc.select("#lblGpa");		 	// ���� ���
				rows4 = doc.select("#lblRanking");		// ����
				
				a=rows1.size();
				
				if(a<=9){ 		// ���� ���� �⵵ ��ȸ��
					text = "��� ������ �����ϴ�.";
					st1 = "    ";
					st2 = "";
					st3 = selectedyear+"�⵵";
					st5 = "";
					st6 = "";
					switch(sel){
					case 10:
						st4 = "1�б�";
						break;
					case 11:
						st4 = "�����б�(�ϰ�)";
						break;
					case 20:
						st4 = "2�б�";
						break;
					case 21:
						st4 = "�����б�(����)";
						break;
					}
					temp = new customView(st1,st2,st3,st4,st5,st6);
					data_list.add(temp);
					
					st3 = "���� ������";
					st4 = "�����ϴ�.";
					temp = new customView(st1,st2,st3,st4,st5,st6);
					data_list.add(temp);
				}
				
				else{
					sk= rows2.get(0).toString();
					StringTokenizer s0 = new StringTokenizer(sk); 
					s0.nextToken("��");
					text = s0.nextToken("<");
	
					sk= rows3.get(0).toString();
					StringTokenizer s1 = new StringTokenizer(sk); 
					s1.nextToken("��");
					text +="   "+s1.nextToken("<");
					
					sk= rows4.get(0).toString();
					StringTokenizer s2 = new StringTokenizer(sk); 
					s2.nextToken("��");
					
					text +="   "+s2.nextToken("<")+"\n\n ���� : A+(4.5), A(4.0), " +
							"B+(3.5), B(3.0), C+(2.5), C(2.0), D+(1.5), D(1.0), F(0.0)";
					
					temp =new customView("�⵵","�б�","�������","�̼�����","����","����");
					data_list.add(temp);
					
					for(int e=9 ; e<a ; e++){  				//�� �ٿ� 9���� td�� �ִ�
						// 0:�⵵ 	 1:�б�  	2:������ ��ȣ   	3:������� 	 4:�̼�����  	5:���� 	 6:����  	7:����   	8:����*����
						//   *     *                   *         *         *       * 
						
						sk=rows1.get(e).toString();
						StringTokenizer s3 = new StringTokenizer(sk); 
						
						if(e==9 || e==10){ 			// ��ó�� �⵵ �б� ǥ��
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
						else if((e%9)==2||(e%9)==7||(e%9)==8)  		// �������ȣ , ���� , ����*���� ����
							continue;
						
						switch((e%9)){
						case 0:			//ó�� row�� �⵵ ǥ���� ���Ĵ� ��ĭ���� ���
							st1="";
							break;
						case 1:			//ó�� row�� �б� ǥ���� ���Ĵ� ��ĭ���� ���
							st2="";
							break;
						case 3:  		// �������
							s3.nextToken(">");
							s3.nextToken(">");
							st3=s3.nextToken("<").substring(1);
							break;
						case 4:  		// �̼�����
							s3.nextToken(">");
							s3.nextToken(">");
							st4=s3.nextToken("<").substring(1);
							break;
						case 5:  		// ����
							s3.nextToken(">");
							s3.nextToken(">");
							st5=s3.nextToken("<").substring(1);
							break;
						case 6:  		// ����
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
			thread.start();							// ui�� �ð�ǥ �ִ� ������ ����
		}
	}
	
	////// �ڵ� ������ ���     UI  ����
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
	
	/// Ȯ�� ��ư 
	public void search(View v) {
		
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
		
		student2.setText("");
		
		ok=1;
		switch(num){  // ���⼭ url�� �����ָ�  showHTML�޼��尡 ����ȴ�
		case 1:
			Log.d("check", "��ü ����");
			mWebView.loadUrl("https://student.donga.ac.kr/Univ/SUH/SSUH0011.aspx?m=6&rbtn=1");  //��ü
			break;
		case 2:
			Log.d("check", "���輺��");
			mWebView.loadUrl("https://student.donga.ac.kr/Univ/SUH/SSUH0014.aspx?m=6&rbtn=4"); // ����
			break;
		case 3:
			Log.d("check", "�κ� ����");
			mWebView.loadUrl("http://student.donga.ac.kr/Univ/SUH/SSUH0012.aspx?m=6&rbtn=2&year="+selectedyear+"&smt="+sel); // �Ϻ�
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
