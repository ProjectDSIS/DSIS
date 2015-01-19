package com.example.dsis;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.example.dsis.TimetActivity.MyJavaScriptInterface;

import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("JavascriptInterface")
public class ScholarshipActivity extends Activity {
	WebView mWebView;
	WebSettings Wset;
	String[] student_inf;
	TextView text[];
	ListView listview;
	Context context;

	ArrayList<Schol_cus_View> data_list;
	Schol_cus_Adapter customAdapter;
	Schol_cus_View temp;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scholarship);
		student_inf = new String[7];
		text = new TextView[7];
		context = this;
		data_list = new ArrayList<Schol_cus_View>();
		listview = (ListView) findViewById(R.id.listView);

		for (int a = 0; a < 7; a++) {
			int id = getResources().getIdentifier("text" + (a + 1), "id",
					"com.example.dsis");
			text[a] = (TextView) findViewById(id);
		}

		mWebView = (WebView) findViewById(R.id.wv); // ����� xml �� ����
		mWebView.setWebViewClient(new WebViewClient());

		Wset = mWebView.getSettings();
		Wset.setJavaScriptEnabled(true); // �ڹٽ�ũ��Ʈ ���
		mWebView.setHorizontalScrollBarEnabled(false); // ���� ��ũ��
		mWebView.setVerticalScrollBarEnabled(false);

		MyJavaScriptInterface inter = new MyJavaScriptInterface(this);
		mWebView.addJavascriptInterface(inter, "HtmlViewer");

		mWebView.setOnTouchListener(new View.OnTouchListener() { // ��ġ �����ʷ� ����
																	// ��ġ �߻� ��
																	// ��ũ�� ����
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
			}
		});
		mWebView.loadUrl("http://student.donga.ac.kr/Univ/SUL/SSUL0010.aspx?m=1");
	}

	class MyJavaScriptInterface {
		private ScholarshipActivity activity;

		public MyJavaScriptInterface(ScholarshipActivity activity) {
			this.activity = activity;
		}

		@android.webkit.JavascriptInterface
		public void showHTML(String html) {
			Document doc = null;
			doc = Jsoup.parse(html);
			Elements rows1 = null;
			Elements rows2 = null;
			String[] data;
			String sk;

			data_list.clear();

			data = new String[5];

			rows1 = doc.select("#Table1 td"); // �л� ����
			rows2 = doc.select("#Table8 td"); // ���г���

			for (int a = 1, b = 0; a < 14; a++) { // �л����� ��ö
				if (a % 2 == 1) {
					sk = rows1.get(a).toString();
					StringTokenizer s = new StringTokenizer(sk);
					s.nextToken(";").substring(1);
					student_inf[b] = s.nextToken("<").substring(1);

					b++;
				}
			}
			
			temp = new Schol_cus_View("�г⵵/�б�", "���и�", "��������", "����", "�ݾ�");
			data_list.add(temp);

			for (int a = 7, institution = 12, b = 0; a < rows2.size(); a++) {
				
				if(a != institution) { // (0(�⵵) 1(�б�)) 2(����) 3(���и�) 4(��������) 6(�ݾ�)
					sk = rows2.get(a).toString();
					StringTokenizer s = new StringTokenizer(sk);
					s.nextToken(">").substring(1);
					
					if(a == rows2.size()-2){
						data[b] = s.nextToken("<").substring(1);
						b++;
					}else if(a == rows2.size()-1){
						data[b] = s.nextToken("&").substring(1);
						temp = new Schol_cus_View("","","",data[0],data[1]);
						data_list.add(temp);
					}else if (a % 7 == 1) {
						data[b - 1] += "-" + s.nextToken("&").substring(1); // 0 �⵵-�б�  , 1 ���� , 2 ���и� , 3 �������� , 4 �ݾ�
					}else{
						data[b] = s.nextToken("&").substring(1);
						b++;
					}
					
					if (a > 1 && a % 7 == 6){
						b = 0;
						temp = new Schol_cus_View(data[0], data[2], data[3],
								data[1], data[4]);
						data_list.add(temp);
					}
				}else			// 5(���б��) ���� ����
					institution += 7;
			}

			customAdapter = new Schol_cus_Adapter(context,
					R.layout.schol_custom, data_list);

			backthread thread = new backthread();
			thread.setDaemon(true);
			thread.start(); // ui�� �ð�ǥ �ִ� ������ ����
			thread.stop();
			thread.destroy();
		}
	}

	class backthread extends Thread {
		public void run() {
			mHandler.sendEmptyMessage(0);
		}
	}

	Handler mHandler = new Handler() {
		public void handleMessage(Message mag) {
			if (mag.what == 0) {
				listview.setAdapter(customAdapter);
				for (int a = 0; a < 7; a++) { // �л����� ��ö
					text[a].setText(student_inf[a]);
				}
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.scholarship, menu);
		return true;
	}
}
