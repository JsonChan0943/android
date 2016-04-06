package com.example.android_httppost.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpUtils{
	/**
	 * 
	 * @param path
	 * @param map ģ��httpPost�?�ύ���
	 * @param encoding
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String sendPostMethod(String path,Map<String,String> map,String encoding){
		List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(path);
		try {
			if(map!=null&&!map.isEmpty()){
				for(Map.Entry<String, String> entry:map.entrySet()){
					BasicNameValuePair pair = new BasicNameValuePair(entry.getKey(), URLEncoder.encode((String) entry.getValue(),"utf-8"));
					parameters.add(pair);
				}
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, encoding);
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
