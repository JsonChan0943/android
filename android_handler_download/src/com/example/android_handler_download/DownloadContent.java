package com.example.android_handler_download;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class DownloadContent {
	private Context context;
	private ProgressDialog dialog;
	public DownloadContent(Context context){
		this.context = context;
		dialog = new ProgressDialog(context);
		dialog.setTitle("提示");
		dialog.setMessage("正在下载中，请稍候......");
	}
	public void downLoad(final String path,final DownloadCallback callback){
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				byte[] result = (byte[])msg.obj;
				callback.download(result);
				if(msg.what==1){
					dialog.dismiss();
				}
			}
		};
		
		class MyThread implements Runnable{
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet get = new HttpGet(path);
				try {
					HttpResponse response = httpClient.execute(get);
					if(response.getStatusLine().getStatusCode()==200){
						byte[] result = EntityUtils.toByteArray(response.getEntity());
						Message message = new Message();
						message.obj = result;
						message.what = 1;
						handler.sendMessage(message);
					}
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					httpClient.getConnectionManager().shutdown();
				}
			}
		}
		
		new Thread(new MyThread()).start();
		dialog.show();
	}
	
	public interface DownloadCallback{
		public void download(byte[] result);
	}
}
