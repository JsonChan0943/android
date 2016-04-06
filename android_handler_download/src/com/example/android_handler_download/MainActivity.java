package com.example.android_handler_download;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android_handler_download.DownloadContent.DownloadCallback;

public class MainActivity extends Activity {
	private Button button;
	private ImageView imageView;
	private static String IMAGE_PATH = "http://pic.58pic.com/58pic/12/42/17/59e58PICyB8.jpg"; 
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			byte[] data =(byte[])msg.obj;
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			imageView.setImageBitmap(bitmap);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)this.findViewById(R.id.button1);
		imageView = (ImageView)this.findViewById(R.id.imageView1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				new Thread(new DownloadImage()).start();
//				Toast.makeText(MainActivity.this, "dfsdfds", 1).show();
				DownloadContent content = new DownloadContent(MainActivity.this);
				content.downLoad(IMAGE_PATH, new DownloadCallback() {
					
					@Override
					public void download(byte[] result) {
						// TODO Auto-generated method stub
						Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
						imageView.setImageBitmap(bitmap);
					}
				});
			}
		});
	}
	public class DownloadImage implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			HttpClient client = new DefaultHttpClient();
			HttpGet post = new HttpGet(IMAGE_PATH);
			try {
				HttpResponse response = client.execute(post);
				if(response.getStatusLine().getStatusCode()==200){
					byte[] result = EntityUtils.toByteArray(response.getEntity());
					Message message = Message.obtain();
					message.obj = result;
					handler.sendMessage(message);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
