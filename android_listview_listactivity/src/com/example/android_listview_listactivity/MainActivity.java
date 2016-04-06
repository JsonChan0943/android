package com.example.android_listview_listactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
		HashMap<String, String> map1 = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();
		HashMap<String, String> map3 = new HashMap<String, String>();
		map1.put("user_name", "张三");
		map1.put("user_ip", "127.0.0.1");
		map2.put("user_name", "张三");
		map2.put("user_ip", "127.0.0.1");
		map3.put("user_name", "张三");
		map3.put("user_ip", "127.0.0.1");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		SimpleAdapter listaAdapter = new SimpleAdapter(this, list, R.layout.user, 
				new String[]{"user_name","user_ip"},
				new int[]{R.id.user_name,R.id.user_ip});
		setListAdapter(listaAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Toast.makeText(MainActivity.this, position+"  "+id,Toast.LENGTH_LONG).show();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
