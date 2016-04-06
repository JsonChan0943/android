package com.example.android_data_sdcard.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;

public class FileService {
	
	public boolean saveFileToSdcard(String fileName,byte[] data){
		boolean flag = false;
		//判断是否挂载
		String state = Environment.getExternalStorageState();
		//获得根目录
		File root = Environment.getExternalStorageDirectory();
		FileOutputStream fileOutputStream = null;
		//表示SdCard挂载在手机上并且可以读写
		if(state.equals(Environment.MEDIA_MOUNTED)){
			File file = new File(root,fileName);
			try {
				fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(data, 0, data.length);
				flag = true;
			} catch (FileNotFoundException e) {
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
		}
		return flag;
	}
	
	public String readContentFromSdcard(String fileName){
		File root = Environment.getExternalStorageDirectory();
		FileInputStream fileInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File file = new File(root,fileName);
			int len = 0;
			byte[] data = new byte[1024];
			if(file.exists()){
				try {
					fileInputStream = new FileInputStream(file);
					while ((len= fileInputStream.read(data))!=-1) {
						byteArrayOutputStream.write(data, 0, data.length);
					}
					return new String(byteArrayOutputStream.toByteArray());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally{
					if(fileInputStream!=null){
						try {
							fileInputStream.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return null; 
	}
	
	public void saveFileToSdcardBySuff(String fileName,byte[] data){
		File file = null;//保存文件的目录
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			if(fileName.endsWith(".mp3")){
				file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			}else if(fileName.endsWith(".txt")){
				file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			}
		}
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(new File(file,fileName));
			fileOutputStream.write(data, 0, data.length);
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
	}
	
	public boolean deleteFileFromSdcard(String folder,String filename){
		boolean flag = false;
		File file = Environment.getExternalStorageDirectory();
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File file2 = new File(file.getAbsolutePath()+"/"+folder+"/"+filename);
			if(file2.exists()){
				file2.delete();
				flag = true;
			}
		}
		return flag;
	}
}
