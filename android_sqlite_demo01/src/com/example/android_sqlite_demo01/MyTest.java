package com.example.android_sqlite_demo01;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;
import db.DBManager;

public class MyTest extends AndroidTestCase {
	
	
	public void insert(){
		String sql = "insert into person(name,address,age) values(?,?,?)";
		Object[] params = {"石亭","上海",24};
		DBManager dbManager = new DBManager(getContext());
		dbManager.realeseConn();
		dbManager.updateBySQL(sql, params);
		dbManager.getConn();
	}
	
	public void update(){
		String sql = "update person set name=?,address=?,age=? where id = ?";
		Object[] params = {"陈怀结","上海",20,1};
		DBManager dbManager = new DBManager(getContext());
		dbManager.getConn();
		dbManager.updateBySQL(sql, params);
		dbManager.realeseConn();
	}
	
	public void delete(){
		String sql = "delete from person where id = ?";
		Object[] params = {1};
		DBManager dbManager = new DBManager(getContext());
		dbManager.getConn();
		dbManager.updateBySQL(sql, params);
		dbManager.realeseConn();
	}
	
	public void query(){
		String sql = "select * from person ";
		DBManager dbManager = new DBManager(getContext());
		dbManager.getConn();
		dbManager.queryBySql(sql, null);
		dbManager.realeseConn();
	}
	
	public void query2(){
		String sql = "select * from person where id = ? ";
		DBManager dbManager = new DBManager(getContext());
		dbManager.getConn();
		Map<String, String> map = dbManager.queryBySql(sql, new String[]{"2"});
		System.out.println("----->"+map.get("name")+"--"+map.get("address")+"--"+map.get("age")+"----");
		dbManager.realeseConn();
	}
	
	public void query3(){
		String sql = "select * from person ";
		DBManager dbManager = new DBManager(getContext());
		dbManager.getConn();
		List<Map<String, String>> list = dbManager.queryMutiMaps(sql,null);
		for(Map<String,String> map:list){
			System.out.println("----->"+map.get("name")+"--"+map.get("address")+"--"+map.get("age")+"----\n");
		}
		dbManager.realeseConn();
	}
	

	public void inser2(){
		DBManager dbManager = new DBManager(getContext());
		dbManager.getConn();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", "芳");
		contentValues.put("address", "杭州");
		contentValues.put("age", 23);
		dbManager.insert("person", null, contentValues);
		dbManager.realeseConn();
	}
	
	public void update2(){
		DBManager dbManager = new DBManager(getContext());
		dbManager.getConn();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", "罗瑶瑶");
		contentValues.put("address", "杭州");
		contentValues.put("age", 23);
		dbManager.insert("person", null, contentValues);
		dbManager.update("person", contentValues, " id = ?", new String[]{"5"});
		dbManager.realeseConn();
	}
	
	public void delete2(){
		DBManager dbManager = new DBManager(getContext());
		dbManager.getConn();
		dbManager.delete("person", "id = ?", new String[]{"5"});
		dbManager.realeseConn();
	}
	
	public void query4(){
		DBManager dbManager = new DBManager(getContext());
		dbManager.getConn();
		Cursor cursor = dbManager.queryCursor(false, "person", null, null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			System.out.println("------>"+cursor.getString(cursor.getColumnIndex("name")));
		}
		dbManager.realeseConn();
	}
}
