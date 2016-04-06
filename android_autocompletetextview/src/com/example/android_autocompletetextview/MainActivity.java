package com.example.android_autocompletetextview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
	private AutoCompleteTextView textView;
	private ArrayAdapter<String> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (AutoCompleteTextView)this.findViewById(R.id.autoCompleteTextView1);
////		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, getDataSource());
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getDataSource());
//		textView.setAdapter(adapter);
		String[] cities = getResources().getStringArray(R.array.cities);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cities);
		textView.setAdapter(adapter);
	}

	public List<String> getDataSource(){
		List<String> list = new ArrayList<String>();
		list.add("chen");
		list.add("json");
		list.add("java");
		list.add("hello");
		list.add("world");
		list.add("qinshou");
		return list;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
