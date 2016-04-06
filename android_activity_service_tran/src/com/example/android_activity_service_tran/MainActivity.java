package com.example.android_activity_service_tran;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textView;
	private Button button1,button2,button3,button4;
	private boolean flag;
	private DownloadService.LocalBinder localBinder;
	private DownloadService downloadService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView)this.findViewById(R.id.msg);
		button1 = (Button)this.findViewById(R.id.button1);
		button2 = (Button)this.findViewById(R.id.button2);
		button3 = (Button)this.findViewById(R.id.button3);
		button4 = (Button)this.findViewById(R.id.button4);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//绑定service
				Intent intent = new Intent(MainActivity.this,DownloadService.class);
				bindService(intent, connection, Context.BIND_AUTO_CREATE);
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flag){
					int value = downloadService.getRandom();
					textView.setText(value+"");
				}
			}
		});
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				unbindService(connection);
				flag = false;
			}
		});
		
		button4.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("Recycle")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Parcel parcel = Parcel.obtain();
				parcel.writeInt(23);
				parcel.writeString("chenhuaijie");
				Parcel reply = Parcel.obtain();
				try {
					localBinder.transact(Binder.LAST_CALL_TRANSACTION, parcel,reply, 0);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("---从Service中回传的值--->"+reply.readInt());
				System.out.println("---从Service中回传的值--->"+reply.readString());
			}
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Intent intent = new Intent(MainActivity.this,DownloadService.class);
		bindService(intent, connection, Context.BIND_AUTO_CREATE);
	}
//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//	}
	public ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			flag = false;
		}
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			localBinder = (DownloadService.LocalBinder)service;
			downloadService = localBinder.getService();
			flag = true;
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
