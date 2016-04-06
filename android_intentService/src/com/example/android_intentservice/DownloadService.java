package com.example.android_intentservice;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class DownloadService extends IntentService {
	private static String IMAGE_PATH = "http://img4.cache.netease.com/photo/0026/2013-05-29/902H86BN4CJ80026.jpg";
	
	public DownloadService() {
		super("DownloadService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet(IMAGE_PATH);
		try {
			HttpResponse response = httpClient.execute(get);
			if(response.getStatusLine().getStatusCode()==200){
				byte[] data = EntityUtils.toByteArray(response.getEntity());
				boolean flag = DiskTools.saveDisk("chen.gif", data);
				if(flag){
					Toast.makeText(getApplicationContext(), "图片下载完毕！", Toast.LENGTH_LONG).show();
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

}
