package com.example.android_service_aidl_server;

import com.example.service.DataService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

public class ServerService extends Service {
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}
	
	Binder binder = new DataService.Stub() {
		
		@Override
		public int getData(String name) throws RemoteException {
			// TODO Auto-generated method stub
			if("hello".equals(name)){
				return 1;
			}
			return -1;
		}
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return binder;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
