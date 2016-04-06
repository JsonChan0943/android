package com.example.android_httppost;

import java.util.HashMap;
import java.util.Map;

import com.example.android_httppost.http.HttpUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Button  button;
	private EditText usernameEditText;
	private EditText passwordEditText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button1);
		usernameEditText = (EditText) this.findViewById(R.id.editText1);
		passwordEditText = (EditText) this.findViewById(R.id.editText2);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String username = usernameEditText.getText().toString().trim();
				String password = passwordEditText.getText().toString().trim();
				Map<String, String> params = new HashMap<String, String>();
				params.put("url", "http://192.168.1.100:8080/SSHE/login/doLogin.html");
				params.put("username", username);
				params.put("password", password);
				new LoginAsyncTask().execute(params);
			}
		});
	}

	class LoginAsyncTask extends AsyncTask<Map<String, String>, Void, String>{

		@Override
		protected String doInBackground(Map<String, String>... params) {
			Map<String, String> map = params[0];
			Map<String, String> params2 = new HashMap<String, String>();
			params2.put("username", map.get("username"));
			params2.put("password", map.get("password"));
			HttpUtils.sendPostMethod(map.get("url"), params2, "utf-8");
			return null;
		}

		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
