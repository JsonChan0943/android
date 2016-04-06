package com.example.android_broadcast_bootcomplete;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartBootComplete extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Intent intent2 = new Intent(context,MainActivity.class);
		intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//表示新启动一个Activity
		context.startActivity(intent2);
	}

}
