package com.example.android_handler_textview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button button;
	private TextView textView;
	private MyHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button1);
		textView = (TextView)this.findViewById(R.id.msg);
		handler = new MyHandler();
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Thread(new MyThread()).start();
			}
		});
	}

	public class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int count = msg.arg1;
			String name = (String)msg.obj;
			textView.append(name+count+" ");
		}
	}
	public class MyThread implements Runnable{
		private int count = 0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (count<=20) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//从消息池中获取消息：如果没有消息，创建一个消息，如果有，取出来携带数据，有Handler发送
				Message message = Message.obtain();
				message.arg1 = count;
				message.obj = "chenhuaijie";
				count++;
				handler.sendMessage(message);
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
