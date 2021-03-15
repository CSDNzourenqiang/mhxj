package menghuanxianjing.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	
	public static int POST(String ip,String path,String body) throws URISyntaxException, ClientProtocolException, IOException {
		HttpClient client = HttpClients.createDefault();
        URIBuilder builder = new URIBuilder();
        URI uri = null;
        uri = builder.setScheme("http")
                .setHost(ip)
                .setPath(path)
                .build();
      
	      HttpPost post = new HttpPost(uri);
	      post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	      //items
	      //设置请求体
	      post.setEntity(new StringEntity(body,"UTF-8"));
	      //获取返回信息
	      HttpResponse response = client.execute(post);
	      System.out.println(response.toString());
	      return response.getStatusLine().getStatusCode();

	}
	public static void sendPaycb() {
		HttpClient client= new DefaultHttpClient();
	    HttpPost request = new HttpPost("http://yuwotongxing.net/demisdkcb/paycb");
	    List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	    pairs.add(new BasicNameValuePair("client_id", "client2"));
	    pairs.add(new BasicNameValuePair("client_secret", "clientsecret2"));
	    pairs.add(new BasicNameValuePair("grant_type", "client_credentials"));
	    net.sf.json.JSONObject object = null;
	    try {
	        request.setEntity(new UrlEncodedFormEntity(pairs ));
	        HttpResponse resp = client.execute(request);
	        
	        HttpEntity entity = resp.getEntity();
	        if(entity!=null){
	            String result = EntityUtils.toString(entity,"UTF-8");//解析返回数据
	            
	            object = net.sf.json.JSONObject.fromObject(result);
	            System.out.println(object);
	            
	        }
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	}
	//获取客户端IP地址
		public static String getIpAddress(HttpServletRequest request) {
			String ip = request.getHeader("x-forwarded-for");
			if(ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
		        ip = request.getHeader ("WL-Proxy-Client-IP");
		    }
			 if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
			        ip = request.getRemoteAddr ();
			        if (ip.equals ("127.0.0.1")) {
			            //根据网卡取本机配置的IP
			            InetAddress inet = null;
			            try {
			                inet = InetAddress.getLocalHost ();
			            } catch (Exception e) {
			                e.printStackTrace ();
			            }
			            ip = inet.getHostAddress ();
			        }
			    }
			    // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			    if (ip != null && ip.length () > 15) {
			        if (ip.indexOf (",") > 0) {
			            ip = ip.substring (0, ip.indexOf (","));
			        }
			    }
			    return ip;
	 
			
			
		}
	
}
