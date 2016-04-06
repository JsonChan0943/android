package com.example.android_notification_create;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button button,button2,button3,button4;
	private Notification.Builder notification;
	private NotificationManager notificationManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//获取系统的通知服务
		notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		notification = new Notification.Builder(this);
		notification.setSmallIcon(R.drawable.ic_launcher);
		notification.setTicker("有短信来了");
		notification.setContentTitle("晚上有时间吗？");
		notification.setContentText("老地方见");
		button = (Button)this.findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,MainActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1,intent, PendingIntent.FLAG_ONE_SHOT);
				notification.setContentIntent(pendingIntent);
				notificationManager.notify((int)System.currentTimeMillis(),notification.build());
			}
		});
		button2 = (Button)this.findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
				builder.setSmallIcon(R.drawable.ic_launcher);
				builder.setTicker("有短信来了");
				builder.setContentTitle("晚上有时间吗？");
				builder.setContentText("老地方ffafd见");
				NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
				builder.setStyle(inboxStyle);
				String[] events = new String[6];
				for(int i = 0;i<events.length;i++){
					events[i] = "chenhuaijie"+i;
				}
				inboxStyle.setBigContentTitle("相信信息....");
				for(int i = 0;i<events.length;i++){
					inboxStyle.addLine(events[i]);
				}
				
				Intent intent = new Intent(MainActivity.this,MainActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1,intent, PendingIntent.FLAG_ONE_SHOT);
				builder.setContentIntent(pendingIntent);
				notificationManager.notify(1003,builder.build());
			}
		});
		button3 = (Button)this.findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				notificationManager.cancelAll();
			}
		});
		button4 = (Button)this.findViewById(R.id.button4);
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
				builder.setSmallIcon(R.drawable.ic_launcher);
				builder.setTicker("开始下载");
				builder.setContentTitle("图片下载...");
				Intent intent = new Intent(MainActivity.this,MainActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 1,intent, PendingIntent.FLAG_ONE_SHOT);
				builder.setContentIntent(pendingIntent);
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						for(int i=1;i<100;i++){
							builder.setProgress(100, i, false);
							notificationManager.notify(1001, builder.build());
							try {
								Thread.sleep(30);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						builder.setContentText("下载结束").setProgress(0, 0, false);
						notificationManager.notify(1001, builder.build());
					}
				}).start();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
