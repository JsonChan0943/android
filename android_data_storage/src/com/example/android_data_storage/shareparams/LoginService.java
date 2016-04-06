package com.example.android_data_storage.shareparams;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginService {
	private Context context;
	public LoginService(Context context) {
		this.context = context;
	}
	public boolean saveLoginMsg(String name,String password){
		boolean flag = false;
		//不要加后缀名，系统自动已xml的格式保存
		SharedPreferences sharedPreferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);//只有当前的Activity才能去访问
		Editor editor = sharedPreferences.edit();
		editor.putString("username", name);
		editor.putString("password", password);
		flag = editor.commit();
		return flag;
	}
}
