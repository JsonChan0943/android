package com.example.android_send_msg;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText number,content;
	private Button sendMsg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		number = (EditText)this.findViewById(R.id.number);
		content = (EditText)this.findViewById(R.id.content);
		sendMsg = (Button)this.findViewById(R.id.button);
		sendMsg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phoneNumberString = number.getText().toString();
				String msgContent = content.getText().toString();
				SmsManager smsManager = SmsManager.getDefault();//取得系统默认的短信管理器
				ArrayList<String> strings = smsManager.divideMessage(msgContent);//拆分短信
				for(String s:strings){
					smsManager.sendTextMessage(phoneNumberString, null, s, null, null);
					//第四个参数得到发送的状态 第五个参数可以得到对方是否可以收到短信的额状态
				}
				Toast.makeText(MainActivity.this, "短信发送成功！", Toast.LENGTH_LONG).show();
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
