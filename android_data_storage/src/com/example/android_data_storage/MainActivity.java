package com.example.android_data_storage;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button submitButton,resetButton;
	private EditText username,password;
	private CheckBox rePwd,queitLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		submitButton = (Button)this.findViewById(R.id.button1);
//		resetButton = (Button)this.findViewById(R.id.button2);
//		username = (EditText)this.findViewById(R.id.editText1);
////		password = (EditText)this.findViewById(R.id.editText2);
//		rePwd = (CheckBox)this.findViewById(R.id.checkBox1);
//		queitLogin = (CheckBox)this.findViewById(R.id.checkBox2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
