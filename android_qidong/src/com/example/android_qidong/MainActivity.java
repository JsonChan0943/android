package com.example.android_qidong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) this.findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,NextActivity.class);
				intent.putExtra("name", "chenhuaijie");
//				intent.putExtra("age", 24);
//				Bundle bundle = new Bundle();
//				bundle.putString("address", "…œ∫£ –");
//				intent.putExtra("bundle", bundle);
//				startActivity(intent);
				startActivityForResult(intent, 1000);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1000&&resultCode==1001){
			String result_value = data.getStringExtra("result");
			Toast.makeText(MainActivity.this,result_value,Toast.LENGTH_LONG).show();
		}
		
	}
	public boolean onCreateOptiohisnsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
