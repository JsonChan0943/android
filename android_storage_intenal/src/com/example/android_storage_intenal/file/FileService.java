package com.example.android_storage_intenal.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class FileService {
	private Context context;
	public FileService(Context context){
		this.context = context;
	}
	
	public String readContentFromFile(String fileName){
		FileInputStream fileInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			fileInputStream = context.openFileInput(fileName);
			int len = 0;
			byte[] data = new byte[1024];
			while ((len=fileInputStream.read(data))!=-1) {
				byteArrayOutputStream.write(data, 0, data.length);
			}
			return new String(byteArrayOutputStream.toByteArray());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean saveContentToFile(String fileName,int mode,byte[] data){
		boolean flag = false;
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = context.openFileOutput(fileName,mode);
			fileOutputStream.write(data,0,data.length);
			flag = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if(fileOutputStream!=null){
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

	/**
	 * 
	 * @return
	 */
	public boolean saveCacheToFile(String fileName,byte[] data){
		boolean flag = false;
		File file = context.getFilesDir();
		FileOutputStream fileOutputStream = null;
		try {
			File folder = new File(file.getAbsolutePath()+"/txt");
			if(!folder.exists()){
				folder.mkdirs();
			}
			fileOutputStream = new FileOutputStream(folder.getAbsolutePath()+"/"+fileName);
			fileOutputStream.write(data, 0, data.length);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} if(fileOutputStream!=null){
			try {
				fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public void listCacheFile(){
		String[] filelist = context.fileList();
		for(String str:filelist){
			System.out.println("----->"+str);
		}
	}
}
