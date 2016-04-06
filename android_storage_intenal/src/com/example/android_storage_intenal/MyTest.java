package com.example.android_storage_intenal;

import com.example.android_storage_intenal.file.FileService;

import android.content.Context;
import android.test.AndroidTestCase;

public class MyTest extends AndroidTestCase {
	public void save(){
		FileService fileService = new FileService(getContext());
		boolean flag = fileService.saveContentToFile("chen.txt", Context.MODE_PRIVATE, "你好吗？".getBytes());
		System.out.println("------>"+flag);
	}
	
	public void read(){
		FileService fileService = new FileService(getContext());
		String value = fileService.readContentFromFile("chen.txt");
		System.out.println("------>"+value);
	}
	
	public void test(){
		FileService fileService = new FileService(getContext());
//		fileService.saveCacheToFile("my.txt","我真的很想你".getBytes());
		fileService.listCacheFile();
	}
}
