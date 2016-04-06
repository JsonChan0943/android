package com.example.android_data_sdcard;

import android.test.AndroidTestCase;

import com.example.android_data_sdcard.file.FileService;

public class MyTest extends AndroidTestCase {
	public void save(){
		FileService fileService = new FileService();
		boolean flag = fileService.saveFileToSdcard("hhh.txt", "你真的好吗？".getBytes());
		System.out.println("----->1111"+flag);
	}
	
	public void read(){
		FileService fileService = new FileService();
		String flag = fileService.readContentFromSdcard("hhh.txt");
		System.out.println("----->1111"+flag);
	}
	
	public void save2(){
		FileService fileService = new FileService();
		boolean flag = fileService.saveFileToSdcard("hhdh.txt", "你真的好吗？".getBytes());
		System.out.println("----->1111"+flag);
	}
}
