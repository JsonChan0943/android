package com.example.android_intentservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;


public class DiskTools {
	public static boolean saveDisk(String fileName,byte[] data){
		boolean flag = false;
		File file = Environment.getExternalStorageDirectory();
		//判断是否挂载
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(new File(file,fileName));
				fileOutputStream.write(data,0,data.length);
				flag = true;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally{
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}
