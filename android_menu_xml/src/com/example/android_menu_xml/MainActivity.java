package com.example.android_menu_xml;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * 单独注册事件监听
	 * @param item
	 */
	public void save(MenuItem item){
		Toast.makeText(MainActivity.this, "哈哈哈哈", Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.open:
			Intent intent = new Intent(MainActivity.this,OpenActivity.class);
			startActivity(intent);
			break;
		case R.id.close:
			Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_LONG).show();
			break;
//		case R.id.save:
//			Toast.makeText(MainActivity.this, "保存", Toast.LENGTH_LONG).show();
//			break;
		case R.id.paste:
			Toast.makeText(MainActivity.this, "粘贴", Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
