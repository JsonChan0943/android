package com.example.android_progressbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button button1;
	private Button button2;
	private ProgressDialog dialog;
	private Button button3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button)this.findViewById(R.id.button1);
		dialog = new ProgressDialog(MainActivity.this);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.setMessage("正在下载,请稍候....");
				dialog.setTitle("提示");
				dialog.setCancelable(false);//设置对话框不可取消
				dialog.show();
			}
		});
		button2 = (Button) this.findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.setMessage("正在下载,请稍候....");
				dialog.setTitle("提示");
//				dialog.setCancelable(false);//设置对话框不可取消
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setProgress(60);
				dialog.setSecondaryProgress(80);//第二进度条 缓冲
				dialog.setMax(100);
				dialog.show();
			}
		});
		button3 = (Button) this.findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder dialoBuilder = new AlertDialog.Builder(MainActivity.this);
				View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.login, null);
				dialoBuilder.setView(view);
				dialoBuilder.setTitle("登陆");
				final EditText username = (EditText) view.findViewById(R.id.editText1);
				final EditText password = (EditText) view.findViewById(R.id.editText2);
				dialoBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						String valueString = username.getText()+" "+password.getText();
						Toast.makeText(MainActivity.this, valueString, Toast.LENGTH_LONG).show();
					}
				});
				dialoBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_LONG).show();
					}
				});
				dialoBuilder.show();
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
