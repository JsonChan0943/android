package com.example.android_service_download;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class DownloadService extends Service {
	private static String IMAGE_PATH = "http://pic41.nipic.com/20140531/18107775_143020547199_2.jpg";
	private NotificationManager notificationManager;
	private NotificationCompat.Builder builder;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (msg.what==1) {
				stopSelf();//停止当前的Service
				Toast.makeText(getApplicationContext(), "下载完毕", Toast.LENGTH_LONG).show();
			}
			builder.setProgress(100, msg.arg1, false);
			notificationManager.notify(1000, builder.build());
		}
	};
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	public class MyThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			HttpClient client = new DefaultHttpClient();
			HttpGet post = new HttpGet(IMAGE_PATH);
			try {
				HttpResponse response = client.execute(post);
				if(response.getStatusLine().getStatusCode()==200){
					byte[] result = EntityUtils.toByteArray(response.getEntity());
//					boolean isSuccess = DiskTools.saveDisk("aa.gif", result);
//					if(isSuccess){
//						Message message = new Message();
//						message.what = 1;
//						handler.sendMessage(message);
//					}
					InputStream fileInputStream = null;
					long total_length = response.getEntity().getContentLength();
					long sum_length = 0;//每次读取下载的总长度
					byte[] data = new byte[256];
					int len = 0;
					fileInputStream = response.getEntity().getContent();
					while((len = fileInputStream.read(data))!=-1){
						sum_length+=len;
						int value = (int)(sum_length/(float)total_length*100);
						Message message = Message.obtain();
						message.arg1 = value;
						handler.sendMessage(message);
					}
					if(DiskTools.saveDisk("aa.gif", result)){
						handler.sendEmptyMessage(1);
					}
					
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				client.getConnectionManager().shutdown();
			}
			
		}
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		builder = new NotificationCompat.Builder(getApplicationContext());
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setTicker("下载。。。");
		builder.setContentTitle("图片下载");
		notificationManager.notify(1000, builder.build());
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		new Thread(new MyThread()).start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
}
