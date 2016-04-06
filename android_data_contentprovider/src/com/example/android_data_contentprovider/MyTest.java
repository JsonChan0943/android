package com.example.android_data_contentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;

public class MyTest extends AndroidTestCase {
	public void add(){
		ContentResolver contentResolver = getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", "陈怀结");
		values.put("address", "上海");
		Uri uri = Uri.parse("content://com.example.android_data_contentprovider.PersonContentProvider/persons");
		contentResolver.insert(uri, values);
	}
	
	public void del(){
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.example.android_data_contentprovider.PersonContentProvider/person");
		Uri new_uri = ContentUris.withAppendedId(uri, 1);
		contentResolver.delete(new_uri, null, null);
	}
	
	public void update(){
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.example.android_data_contentprovider.PersonContentProvider/person");
		Uri new_uri = ContentUris.withAppendedId(uri, 1);
		ContentValues values = new ContentValues();
		values.put("name", "Json Chan");
		values.put("address", "上海");
		contentResolver.update(new_uri, values, null, null);
	}
	
	public void query(){
		ContentResolver contentResolver = getContext().getContentResolver();
		Uri uri = Uri.parse("content://com.example.android_data_contentprovider.PersonContentProvider/person");
		Uri new_uri = ContentUris.withAppendedId(uri, 1);
		Cursor cursor = contentResolver.query(new_uri, null, null, null, null);
		while (cursor.moveToNext()) {
			System.out.println("---->>"+cursor.getString(cursor.getColumnIndex("name")));			
		}
	}
}
