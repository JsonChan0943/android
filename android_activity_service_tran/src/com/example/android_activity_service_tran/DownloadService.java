package com.example.android_activity_service_tran;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class DownloadService extends Service {

	private final String TAG = "DownloadService";
	private final Binder localBinder = new LocalBinder();
	private final Random random = new Random();
	
	public class LocalBinder extends Binder{
		public DownloadService getService(){
			return DownloadService.this;
		}
		@Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
				int flags) throws RemoteException {
			// TODO Auto-generated method stub
			System.out.println("---从Activity中读取数据："+data.readInt());
			System.out.println("---从Activity中读取数据："+data.readString());
			reply.writeInt(54);
			reply.writeString("rose");
			return super.onTransact(code, data, reply, flags);
		}
	}
	
	public int getRandom(){
		return random.nextInt(100);
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "---->onCreate");
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "---->onBind");
		return localBinder;
	}
	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
		Log.i(TAG, "---->onRebind");
	}
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "---->onUnbind");
		return super.onUnbind(intent);
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "---->onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i(TAG, "---->onDestroy");
	}
}
