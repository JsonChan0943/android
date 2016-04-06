package com.example.android_handler_dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button button;
	private static ProgressDialog dialog;
	private static Handler handler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			int count = msg.arg1;
			dialog.setProgress(count);
			dialog.setSecondaryProgress(msg.arg2);
			if(msg.what==1){
				dialog.dismiss();
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)this.findViewById(R.id.button1);
		dialog = new ProgressDialog(this);
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setTitle("提示");
		dialog.setMessage("正在下载中,请稍候...");
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new MyThread()).start();
				dialog.show();
			}
		});
	}

	class MyThread implements Runnable{
		int count = 1;
		int index = 1;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(count<=100){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Message message = Message.obtain();
				message.arg1 = count;
				message.arg2 = count+5;
				handler.sendMessage(message);
				count++;
			}
			Message end = Message.obtain();
			end.what = 1;
			handler.sendMessage(end);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
