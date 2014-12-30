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
    timeset set[];  // 과목 저장하는 클래스
    int state[];  // 같은 과목이 몇개 있는가

    int deviceWidth;
    int deviceHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timet);

        //디바이스 해상도 리턴
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

        mWebView = (WebView) findViewById(R.id.wv_1); // 웹뷰와 xml 간 연동
        mWebView.setWebViewClient(new WebViewClient());
        Log.d("web","view1");

        Wset = mWebView.getSettings();
        Wset.setJavaScriptEnabled(true); // 자바스크립트 허용
        mWebView.setHorizontalScrollBarEnabled(false); //가로 스크롤
        mWebView.setVerticalScrollBarEnabled(false);

        Log.d("web","view2");

        for(int i = 0; i<timeT.length; i++){
            int id = getResources().getIdentifier("time"+(i/5+1)+"_"+(i+1), "id", "com.example.dsis");
            timeT[i] = (TextView) findViewById(id);
        }

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

        mWebView.loadUrl("http://student.donga.ac.kr/Univ/SUE/SSUE0020.aspx?m=3"); // 웹뷰로 학생정보 화면 아옴
    }

    class MyJavaScriptInterface {
        private TimetActivity activity;
        String sk;  // 여과 안됨 파싱내용
        int tb_num;
        String sn;	// 분반


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
            Elements rows = doc.select("#htblTime2 td");  			//id가 htblTime2 인 테이블의 td를 긁어옴
            Log.d("파싱 했음","했어");

            for(int a=0;a<10;a++){ sub[a]=null; }

            for(int c = 0 ; c<90 ; c++){
                set[c].num = 0;
                set[c].num2 = 0;
                set[c].room = "";
                set[c].sub = "";
                set[c].day = 0;
            }

            for(int c=21 ; c<146; c++){ 							// 다음행으로 넘어감  ////////c를 3부터 하는 이유는 0은 요일 행이고 1과2는 보통 비어있음.

                if(deviceHeight == 2560 && deviceWidth == 1440)
                    mWebView.scrollTo(1,1); // 웹사이트 로그인 화면 절대 좌표 설정 2560*1440 갤5

                else if(deviceHeight == 2392 && deviceWidth == 1440)
                    mWebView.scrollTo(1,1); // 웹사이트 로그인 화면 절대 좌표 설정 2392*1440 G3

                else if(deviceHeight == 1920 && deviceWidth == 1080)
                    mWebView.scrollTo(555,905); // 웹사이트 로그인 화면 절대 좌표 설정 1920*1080 노트 3

                else if(deviceHeight == 1776 && deviceWidth == 1080)
                    mWebView.scrollTo(555,955); // 웹사이트 로그인 화면 절대 좌표 설정 1776*1080 G2

                else if(deviceHeight == 1280 && deviceWidth == 768)
                    mWebView.scrollTo(370,605); // 웹사이트 로그인 화면 절대 좌표 설정 1280*768 옵 G

                else if(deviceHeight == 1280 && deviceWidth == 720)
                    mWebView.scrollTo(370,600); // 웹사이트 로그인 화면 절대 좌표 설정 1280*720 노트 2

                else if(deviceHeight == 1184 && deviceWidth == 720)
                    mWebView.scrollTo(1,1); // 웹사이트 로그인 화면 절대 좌표 설정 1184*720  베가R3


                if((c % 7)==0)  									// 시간 내용들어가있는 엘리먼트는 생략
                {
                    Log.d("으아아", "시간");
                    continue;
                }

                else if((c%7) == 6)   // 토요일 생략.
                {
                    Log.d("으아아", "토욜");
                    continue;
                }

                else if((rows.get(c).toString().length()) < 50){    // 수업이 없어서 아무 내용 없는 엘리먼트는 빈칸 넣어줌
                    tb_num++;
                }

                else{												// 시간 내용 엘리먼트도 아니고 수업이 없어서 빈칸인 엘리먼트도 아닌 수업있는 엘리먼트들
                    sk= rows.get(c).toString();
                    StringTokenizer s = new StringTokenizer(sk);
					/*
					 * <TD class="td20" valign="top" width="115">
					 * GEE029-01&nbsp;&nbsp;허지희<br><a href="javascript:windowOpenPreKyoCuri('2013','20','GEE029','01');">
					 * 기술경영</a><br>RS709</TD>
					*/

                    s.nextToken("-");
                    sn=s.nextToken("&");  	//분반 잘라냄
					
					/*
					s.nextToken(";");
					s.nextToken(";");
					tk[tb_num]+=s.nextToken("<");	//교수님 이름 잘라님
					*/

                    s.nextToken(">");
                    s.nextToken(">");
                    sr[tb_num]=s.nextToken("<");			//과목 이름 자름
                    sr[tb_num]=sr[tb_num].substring(1);

                    set[tb_num].sub = sr[tb_num].trim();  //trim >> 스트링에 양쪽 끝 공백 잘라내는것

                    for(int a=0; a<10 ;a++){		//리스트에 과목를을 넣는다
                        if(sub[a]==null){
                            sub[a]=sr[tb_num].trim();  //빈공간 제거해서 저장
                            set[tb_num].num=1;
                            break;
                        }
                        else if(sub[a].equals(set[tb_num].sub)){				//중복된거면 넘어간다.
                            Log.d("같다","같다   : "+tb_num);

                            if(tb_num>4 && set[tb_num-5].sub.equals(set[tb_num].sub)){  // 전 교시와 같은 과목일때 순서 카운터 ++
                                Log.d("tb_num","중간 "+ set[tb_num].sub +" // tb_num : "+tb_num);

                                set[tb_num].num=set[tb_num-5].num+1;
                                break;
                            }else{ 			// 첫시간 되는놈들
                                Log.d("tb_num","첫시간 "+ set[tb_num].sub +" // tb_num : "+tb_num);

                                set[tb_num].num=1;
                                break;
                            }
                        }
                    }

                    switch(tb_num%5){
                        case 0:
                            set[tb_num].day = 1;		// 월
                            break;
                        case 1:
                            set[tb_num].day = 2;		// 화
                            break;
                        case 2:
                            set[tb_num].day = 3;		// 수
                            break;
                        case 3:
                            set[tb_num].day = 4;		// 목
                            break;
                        case 4:
                            set[tb_num].day = 5;		// 금
                            break;
                    }

                    s.nextToken(">");
                    s.nextToken(">");
                    set[tb_num].room=s.nextToken("<").substring(1)+sn;	// 강의실 자름 +분반 붙임 (sn)

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
            thread.start();							// ui에 시간표 넣는 스레드 돌림

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
                int turn=0; // 1이면 공강때 회색바탕 0이면 공강때 진회색 바탕

                for(int c=0 ; c<90; c++){ 				// 다음행으로 넘어감  ////////c를 3부터 하는 이유는 0은 요일 행이고 1과2는 보통 비어있음.
                    Log.d("thread for",""+c);
                    timeT[c].setText("");

                    if(c%5==0){		// 한줄씩 turn변수를 바꿔줌
                        switch(turn){
                            case 0:
                                turn = 1;
                                break;
                            case 1:
                                turn = 0;
                                break;
                        }
                    }

                    if(set[c].sub.length()<2){ 				// 수업이 없어서 아무 내용 없는 엘리먼트는 빈칸 넣어줌
                        Log.d("textview", "공강");
                        //timeT[c].setText("");
                        if(turn==1){ 		//진회색 바탕
                            timeT[c].setBackgroundColor(Color.parseColor("#eeeeee"));  //eeeeee
                        }
                        if(turn==0){			//회색 바탕
                            timeT[c].setBackgroundColor(Color.parseColor("#ffffff"));  //ffffff
                        }
                    }

                    else{								// 시간 내용 엘리먼트도 아니고 수업이 없어서 빈칸인 엘리먼트도 아닌 수업있는 엘리먼트들
                        Log.d("textview", "수업");

                        if(set[c].num==1){
                            Log.d("timeTable", set[c].room+set[c].sub);
                            timeT[c].setText(set[c].room+"\n"+set[c].sub);
                        }

                        for(a=0; a<10; a++){
                            if(sr[c].equals(sub[a])){		//sub과목과 sr과목이 서로 같으면 sub과목 색깔(a인덱스로 결정)로 배경 바뀜
                                break;
                            }

                            if(set[c].num == state[a]){  // 과목이 있을때
                                if(set[c].num2>1 && set[c].num2!=2){  // 수업이 3교시 이상일

                                }
                                if(set[c].num2==2){

                                }
                            }
                        }

                        switch(a){
                            case 0:
                                timeT[c].setBackgroundColor(Color.parseColor("#FFCBCB")); //빨
                                break;
                            case 1:
                                timeT[c].setBackgroundColor(Color.parseColor("#FFD3B0"));  //주
                                break;
                            case 2:
                                timeT[c].setBackgroundColor(Color.parseColor("#D6F0FF")); //파
                                break;
                            case 3:
                                timeT[c].setBackgroundColor(Color.parseColor("#FFFFA1")); //노
                                break;
                            case 4:
                                timeT[c].setBackgroundColor(Color.parseColor("#F5D6FF")); //보
                                break;
                            case 5:
                                timeT[c].setBackgroundColor(Color.parseColor("#BCFFB5")); //초
                                break;
                            case 6:
                                timeT[c].setBackgroundColor(Color.parseColor("#D6FFFF")); //하늘
                                break;
                            case 7:
                                timeT[c].setBackgroundColor(Color.parseColor("#E8D9FF"));  //연보
                                break;
                            case 8:
                                timeT[c].setBackgroundColor(Color.parseColor("#D8D8D8"));  //회
                                break;
                            case 9:
                                timeT[c].setBackgroundColor(Color.parseColor("#FFD6FF")); //분홍
                                break;
                        }
                    }
                }
            }
        }
    };

    public void save(View v)
    { 		// 저장누르면 토스트로 저장할꺼냐고 하나만 된다고 띄워주고 저장
//		if(mWebView.getUrl()!="http://student.donga.ac.kr/Univ/SUE/SSUE0020.aspx?m=3" ){
//		}

    }

    public void opem(View v)
    {	// 오픈 누르면 파일 있나 확인하고 있으면 읽어와서 바로 시간표 띄워줌  없으면 파일 없다고 토스트.

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timet, menu);
        return true;
    }
}
