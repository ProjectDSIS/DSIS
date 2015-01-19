package com.example.dsis;

import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.example.dsis.TimetActivity.MyJavaScriptInterface;

import android.os.AsyncTask;
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
    DongAWebClient client;
    
    String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        
        this.client = DongAWebClient.getInstance();

        text = new TextView[24];
        str = new String[30];

        Log.d("web","view1");

        for(int i =0; i<text.length; i++)
        {
            int id = getResources().getIdentifier("text"+(i), "id", "com.example.dsis");
            text[i] = (TextView) findViewById(id);
        }
        
        new ProcessSetData().execute(null,null,null);
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
            doc = new Document(new String("asd"));
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

            
           						// ui에 시간표 넣는 스레드 돌림

            Log.d("학적부", "thread start");
        }
    }
    
    
    private class ProcessSetData extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			 if(!client.getIsLogin()){
             	Log.e("로그인안됨", "non-login");
             	return null;
             }
             
             source = client.getHtml(ConstTable.URL.STUDENT_INFO);
             backthread thread = new backthread();
             thread.setDaemon(true);
             thread.start();
			return null;
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
                //mWebView.scrollTo(500,550);
                Log.d("학적부", "화면고정");
               
                
                int a;
                int i;
                String sk;
                
                a=0;
                i=0;
                //mWebView.scrollTo(500,550);

                Document doc = null;
        		doc = Jsoup.parse(source);

                //doc = new Document(new String("asd"));
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

        							// ui에 시간표 넣는 스레드 돌림

                Log.d("학적부", "thread start");
                
                
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

    private void parseHTML(String _source){
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student, menu);
        return true;
    }

}
