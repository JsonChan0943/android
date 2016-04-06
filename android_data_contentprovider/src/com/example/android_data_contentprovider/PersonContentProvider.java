package com.example.android_data_contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.CancellationSignal;

public class PersonContentProvider extends ContentProvider{
	private DBHelper dbHelper;
	private final static UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
	
	private static final int PERSONS = 1;//操作多条记录
	private static final int PERSON = 2;//操作单条记录
	static{
		/*
		 * 往URI_MATCHER匹配器里添加匹配规则
		 * 该方法用于向Urimatcher对象注册Uri,前两个参数合成一个Uri,最后一个参数代表该Uri对应的标识码
		 * 第二个参数代表资源 第一个参数代表域名
		 */
		URI_MATCHER.addURI("com.example.android_data_contentprovider.PersonContentProvider", "persons", PERSONS);
		URI_MATCHER.addURI("com.example.android_data_contentprovider.PersonContentProvider", "person/#", PERSON);
	}
	
	/**
	 * 第一次调用ContentProvider时调用此方法
	 */
	@Override
	public boolean onCreate() {
		dbHelper = new DBHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		Cursor cursor = null;
		int flag = URI_MATCHER.match(uri);//判断是单条操作还是多条操作
		SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
		switch (flag) {
		case PERSON:
			long _id = ContentUris.parseId(uri);
			String where_value = "_id = "+_id;
			if(selection!=null&&!selection.equals("")){
				where_value+=selection;
			}
			cursor = sqLiteDatabase.query("person", projection, where_value,selectionArgs, null,null,sortOrder);
			break;
		case PERSONS:
			cursor = sqLiteDatabase.query("person", projection, selection,selectionArgs, null,null,sortOrder);
			break;
		}
		return cursor;
	}

	//返回指定Uri参数对应的数据的MINE类型
	@Override
	public String getType(Uri uri) {
		int flag = URI_MATCHER.match(uri);
		switch (flag) {
			case PERSON:
				return "vnd.android.cursor.item/person";
			case PERSONS:
				return "vnd.android.cursor.dir/persons";
			}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Uri result = null;
		int flag = URI_MATCHER.match(uri);
		switch (flag) {
			case PERSONS:
				SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
				long id = sqLiteDatabase.insert("person", null, values);
				if(id>0){
					result = ContentUris.withAppendedId(uri, id);
				}
				System.out.println("------>"+result.toString());
				break;
			}
		return result;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int flag = URI_MATCHER.match(uri);
		SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
		int count = 0;
		switch (flag) {
			case PERSON:
				long _id = ContentUris.parseId(uri);
				String where_value = " _id = " +_id;
				if(selection!=null&&!selection.equals("")){
					where_value+=selection;
				}
				count = sqLiteDatabase.delete("person", where_value, selectionArgs);
				break;
			
			case PERSONS:
				count = sqLiteDatabase.delete("person", selection, selectionArgs);
				break;
		}
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int flag = URI_MATCHER.match(uri);
		SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
		int count = 0;
		switch (flag) {
			case PERSON:
				long _id = ContentUris.parseId(uri);
				String where_value = " _id = " +_id;
				if(selection!=null&&!selection.equals("")){
					where_value+=selection;
				}
				count = sqLiteDatabase.update("person",values,where_value, selectionArgs);
				break;
		}
		return count;
	}
	
}
