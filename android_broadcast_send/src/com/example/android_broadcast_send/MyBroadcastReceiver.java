package com.example.android_broadcast_send;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {
	private NotificationManager notificationManager;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
//		Toast.makeText(context, "你好！", 1).show();
		notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		builder.setSmallIcon(R.drawable.ic_launcher);
		builder.setTicker("广播来了");
		builder.setContentTitle("没有网络");
		builder.setContentText("wifi掉了");
		notificationManager.notify(100, builder.build());
		System.out.println("-------1111111111111");
	}	

}
