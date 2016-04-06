package com.example.android_handler;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button start,end;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button)this.findViewById(R.id.button1);
		end = (Button)this.findViewById(R.id.button2);
		start.setOnClickListener(this);
		end.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	Handler handler = new Handler();
	
	Runnable updateThread = new Runnable() {
		
		@Override
		public void run() {
			System.out.println("updateThread update");
			handler.postDelayed(updateThread, 3000);
		}
	};
	
	@Override
	public void onClick(View v) {
		if(v.getId()==start.getId()){
			Toast.makeText(MainActivity.this, "启动", Toast.LENGTH_LONG).show();
			handler.post(updateThread);
		}else{
			Toast.makeText(MainActivity.this, "停止", Toast.LENGTH_LONG).show();
			handler.removeCallbacks(updateThread);
		}
	}

}
