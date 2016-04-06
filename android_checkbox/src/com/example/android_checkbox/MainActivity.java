package com.example.android_checkbox;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onCheckClick(View view){
		CheckBox checkBox = (CheckBox)view;
		boolean isChecked = checkBox.isChecked();
		switch(view.getId()){
			case R.id.checkBox1:
				if(isChecked){
					Toast.makeText(MainActivity.this, "选择"+checkBox.getText(), 1).show();
				}else{
					Toast.makeText(MainActivity.this, "取消"+checkBox.getText(), 1).show();
				}
				break;
			case R.id.checkBox2:
				if(isChecked){
					Toast.makeText(MainActivity.this, "选择"+checkBox.getText(), 1).show();
				}else{
					Toast.makeText(MainActivity.this, "取消"+checkBox.getText(), 1).show();
				}
				break;
			case R.id.checkBox3:
				if(isChecked){
					Toast.makeText(MainActivity.this, "选择"+checkBox.getText(), 1).show();
				}else{
					Toast.makeText(MainActivity.this, "取消"+checkBox.getText(), 1).show();
				}
				break;
			case R.id.checkBox4:
				if(isChecked){
					Toast.makeText(MainActivity.this, "选择"+checkBox.getText(), 1).show();
				}else{
					Toast.makeText(MainActivity.this, "取消"+checkBox.getText(), 1).show();
				}
				break;
		}	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
