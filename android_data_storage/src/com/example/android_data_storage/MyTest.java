package com.example.android_data_storage;

import com.example.android_data_storage.shareparams.LoginService;

import android.test.AndroidTestCase;
import android.util.Log;

public class MyTest extends AndroidTestCase {
	private static String TAG = "MyTest";
	
	public void save(){
		LoginService service = new LoginService(getContext());
		boolean flag = service.saveLoginMsg("陈怀结", "123456");
		Log.i(TAG, "------>"+flag);
	}
}
