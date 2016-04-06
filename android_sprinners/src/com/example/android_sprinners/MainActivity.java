package com.example.android_sprinners;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

/**
 * 
 * @author Administrator
 *
 */
public class MainActivity extends Activity implements OnItemSelectedListener{
	private Spinner spinner;
	private SpinnerAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		spinner = (Spinner) this.findViewById(R.id.spinner1);
		adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item,getDataSource());
		spinner.setAdapter(adapter);
		//ע�������
		spinner.setOnItemSelectedListener(this);
	}

	/**
	 * 获取数据源
	 * @return
	 */
	public List<String> getDataSource(){
		List<String> list = new ArrayList<String>();
		list.add("同义词");
		list.add("AAAA");
		list.add("BBBB");
		return list;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
		// TODO Auto-generated method stub
		String itemString = spinner.getItemAtPosition(position).toString();
		Toast.makeText(MainActivity.this, itemString, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
