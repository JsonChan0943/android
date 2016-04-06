package db;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	private DBHelper dbHelper;
	private SQLiteDatabase database;
	
	public DBManager(Context context){
		dbHelper = new DBHelper(context);
	}
	
	/**
	 * 获取数据库连接
	 */
	public void getConn(){
		database = dbHelper.getWritableDatabase();//打开数据库连接
	}
	
	/**
	 * 关闭数据库连接
	 */
	public void realeseConn(){
		if(database!=null){
			database.close();
		}
	}
	
	/**
	 * 实现对数据库的增加 修改 删除
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean updateBySQL(String sql,Object[] params){
		boolean flag = false;
		try {
			database.execSQL(sql,params);
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return flag;
	}
	
	/**
	 * 查找单一记录
	 * @param sql
	 * @param selectionArgs
	 * @return
	 */
	public Map<String, String> queryBySql(String sql,String[] selectionArgs){
		Map<String, String> map = new HashMap<String, String>();
		Cursor cursor = database.rawQuery(sql, selectionArgs);//返回游标
		int col_count = cursor.getColumnCount();//列的总数
		while (cursor.moveToNext()) {
			for(int i=0;i<col_count;i++){
				String col_name = cursor.getColumnName(i);
				String col_value = cursor.getString(cursor.getColumnIndex(col_name));
				if(col_value==null){
					col_value ="";
				}
				map.put(col_name, col_value);
			}
		}
		return map;
	}
	
	/**
	 * 查找多条记录
	 * @param sql
	 * @param selectionArgs
	 * @return
	 */
	public List<Map<String, String>> queryMutiMaps(String sql,String[] selectionArgs){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Cursor cursor = database.rawQuery(sql, selectionArgs);
		int col_count = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			Map<String, String> map = new HashMap<String, String>();
			for(int i=0;i<col_count;i++){
				String col_name = cursor.getColumnName(i);
				String col_value = cursor.getString(cursor.getColumnIndex(col_name));
				if(col_value==null){
					col_value ="";
				}
				map.put(col_name, col_value);
			}
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 查找单一记录 封装成T类型对象
	 * @param sql
	 * @param selectionArgs
	 * @param cls
	 * @return
	 */
	public <T> T querySingleCursor(String sql,String[] selectionArgs,Class<T> cls){
		T t = null;
		Cursor cursor = database.rawQuery(sql, selectionArgs);
		int col_count = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			try {
				t = cls.newInstance();
				for(int i=0;i<col_count;i++){
					String col_name = cursor.getColumnName(i);
					String col_value = cursor.getString(cursor.getColumnIndex(col_name));
					if(col_value==null){
						col_value ="";
					}
					Field field = cls.getDeclaredField(col_name);
					field.setAccessible(true);
					field.set(t, col_value);
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return t;
	}
	
	public <T> List<T> queryMutiCursor(String sql,String[] selectionArgs,Class<T> cls){
		List<T> list = new ArrayList<T>();
		Cursor cursor = database.rawQuery(sql, selectionArgs);
		int col_count = cursor.getColumnCount();
		while (cursor.moveToNext()) {
			try {
				T t = cls.newInstance();
				for(int i=0;i<col_count;i++){
					String col_name = cursor.getColumnName(i);
					String col_value = cursor.getString(cursor.getColumnIndex(col_name));
					if(col_value==null){
						col_value ="";
					}
					Field field = cls.getDeclaredField(col_name);
					field.setAccessible(true);
					field.set(t, col_value);
					list.add(t);
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * SQLiteDatabase的insert语句
	 * @param table
	 * @param nullColumnHack
	 * @param values
	 * @return
	 */
	public boolean insert(String table,String nullColumnHack,ContentValues values){
		boolean flag = false;
		//返回新添加记录的行号，该行号是一个内部值，与主键id无关，发生错误返回-1
		long id = database.insert(table, nullColumnHack, values);
		flag = (id>0?true:false);
		return flag;
	}
	
	/**
	 * SQLiteDatabase的update语句
	 * @param table
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public boolean update(String table,ContentValues values,String whereClause,String[] whereArgs){
		boolean flag = false;
		//返回此语句影响的行数
		int count = database.update(table, values, whereClause, whereArgs);
		flag = (count>0?true:false);
		return flag;
	}
	
	/**
	 * SQLiteDatabase的delete语句
	 * @param table
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public boolean delete(String table, String whereClause, String[] whereArgs){
		boolean flag = false;
		int count = database.delete(table, whereClause, whereArgs);
		flag = (count>0?true:false);
		return flag;
	}
	public Cursor queryMutiCursor(String sql,String[] selectionArgs){
		Cursor cursor = database.rawQuery(sql, selectionArgs);
		return cursor;
	}
	
	/**
	 * SQLiteDatabase的query语句
	 * @param table
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 */
	public Cursor queryCursor(boolean distinct, String table, String[] columns,String selection, String[] selectionArgs, 
			String groupBy,String having, String orderBy, String limit){
		Cursor cursor = null;
		cursor = database.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		return cursor;
	}
}
