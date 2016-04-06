package com.example.android_storage_intenal;

import com.example.android_storage_intenal.file.FileService;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText editText;
	private Button button;
	private FileService fileService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fileService = new FileService(this);
		editText = (EditText)this.findViewById(R.id.editText1);
		button = (Button)this.findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String valueString = editText.getText().toString().trim();
				fileService.saveContentToFile("nihao.txt", Context.MODE_PRIVATE, valueString.getBytes());
				Toast.makeText(MainActivity.this, "保存成功！", Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
