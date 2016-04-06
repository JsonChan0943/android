package com.example.android_qidong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NextActivity extends Activity {
	private  final String TAG = "NextActivity";
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.next);
		button = (Button)this.findViewById(R.id.button1);
		Intent intent = getIntent();
		final String name = intent.getStringExtra("name");
		Log.i(TAG, "--->"+name);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("result", name);
				//设置回传意图
				setResult(1001, intent);
				finish();//结束Activity的声明周期
			}
		});
	}
}
