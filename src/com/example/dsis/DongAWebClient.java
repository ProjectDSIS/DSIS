package com.example.dsis;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class DongAWebClient {
	private static DongAWebClient instance = new DongAWebClient();
	protected HttpResponse res; // 결과
	protected HttpPost post;
	protected HttpClient client;
	private boolean isLoginSuccess = false;
	
	private DongAWebClient(){
		post = new HttpPost("http://student.donga.ac.kr/Login.aspx");
		client = getHttpClient();
	}
	
	public static DongAWebClient getInstance(){ //싱글 톤
		return instance;
	}
	
	public boolean getIsLogin(){
		return this.isLoginSuccess;
	}
	
	//로그인 함수
	public boolean doLogin(String _id, String _pw){
		
		if(this.isLoginSuccess){
			return true;
			
		}
		
		List<NameValuePair> parms = new ArrayList<NameValuePair>(2);
		parms.add(new BasicNameValuePair(ConstTable.ID.VIEW_STATE, ConstTable.VALUE.VIEW_STATE_VAL));
		parms.add(new BasicNameValuePair(ConstTable.ID.ID, _id)); //가변 값 - 아이디
		parms.add(new BasicNameValuePair(ConstTable.ID.PW, _pw)); //가변 값 - 암호
		parms.add(new BasicNameValuePair(ConstTable.ID.LOGIN_BTN_X, ConstTable.VALUE.LOG_IN_BTN_VAL)); //고정 값
		parms.add(new BasicNameValuePair(ConstTable.ID.LOGIN_BTN_Y, ConstTable.VALUE.LOG_IN_BTN_VAL)); //고정 값
		
		try {
			post.setEntity(new UrlEncodedFormEntity(parms));
			res = client.execute(post);
			String str = EntityUtils.toString(res.getEntity(), HTTP.UTF_8);
			if (str.equals("<script language='javascript'>alert('학번 또는 비밀번호 오류입니다.');window.location.href='login.aspx';</script>")) {
				isLoginSuccess = false; // responseString.length() = 100
				return false;
			} else {
				isLoginSuccess = true;
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getHtml(String _html){
		if(this.isLoginSuccess){
			post = new HttpPost("_html");
			try {
				res = client.execute(post);
				String resString = EntityUtils.toString(res.getEntity(),HTTP.UTF_8);
				
				return resString;
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			return new String("Not Login");
		}
		return new String("Not Login");
	}
	
	//Https용 Client 리턴 함수
	private HttpClient getHttpClient(){
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new SFSSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
			// ConnManagerParams.setTimeout(params, 50000);
			SchemeRegistry registry = new SchemeRegistry();

			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}
	
	//Https 통신용 소켓 -> 어지간하면 건들지 말것
	private class SFSSLSocketFactory extends SSLSocketFactory{
		SSLContext sslContext = SSLContext.getInstance("TLS");

	    public SFSSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
	        super(truststore);

	        TrustManager tm = new X509TrustManager() {
	        	@Override
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}
	        };

	        sslContext.init(null, new TrustManager[]{tm}, null);
	    }

	    @Override
	    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
	        return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
	    }

	    @Override
	    public Socket createSocket() throws IOException {
	        return sslContext.getSocketFactory().createSocket();
	    }
	}
}
