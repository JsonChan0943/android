package com.example.android_fragment_activity2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.android_fragment_activity2.LeftFragment.CallBack;

public class MainActivity extends Activity {
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		final LeftFragment fragment = new LeftFragment();
		fragmentTransaction.add(R.id.left, fragment, "left");
		fragmentTransaction.commit();
		button = (Button)this.findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fragment.getResult(new CallBack() {
					
					@Override
					public void getResult(String result) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this, result, 1).show();
					}
				});
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
