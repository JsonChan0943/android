package com.example.android_fragment_activity;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends Activity {
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		LeftFragment leftFragment = new LeftFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("id", 101);
		leftFragment.setArguments(bundle);
		fragmentTransaction.add(R.id.left, leftFragment,"left");
		RightFragment rightFragment = new RightFragment();
		Bundle bundle2 = new Bundle();
		bundle2.putInt("id", 1001);
		rightFragment.setArguments(bundle2);
		fragmentTransaction.add(R.id.right, rightFragment,"right");
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
