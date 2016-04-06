package com.example.android_menu_showasaction;

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
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.close:
			Toast.makeText(MainActivity.this, "关闭", Toast.LENGTH_LONG).show();
			break;
		case R.id.close1:
			Toast.makeText(MainActivity.this, "关闭1", Toast.LENGTH_LONG).show();
			break;
		case R.id.open:
			Intent intent = new Intent(MainActivity.this,NewActivity.class);
			startActivity(intent);
			break;
		case R.id.open1:
			Toast.makeText(MainActivity.this, "打开1", Toast.LENGTH_LONG).show();
			break;
		case R.id.save:
			Toast.makeText(MainActivity.this, "保存", Toast.LENGTH_LONG).show();
			break;
		case R.id.save1:
			Toast.makeText(MainActivity.this, "保存1", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
