package com.example.android_fragment_backstack;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private Button button1,button2,button3,button4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getFragmentManager();
		button1 = (Button) this.findViewById(R.id.button1);
		button1.setOnClickListener(this);
		button2 = (Button) this.findViewById(R.id.button2);
		button2.setOnClickListener(this);
		button3 = (Button) this.findViewById(R.id.button3);
		button3.setOnClickListener(this);
		button4 = (Button) this.findViewById(R.id.button4);
		button4.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		fragmentTransaction = fragmentManager.beginTransaction();
		switch (v.getId()) {
			case R.id.button1:
				Fragment1 fragment1 = new Fragment1();
				fragmentTransaction.replace(R.id.center, fragment1, "fragment1");
				fragmentTransaction.addToBackStack("fragment1");
				break;
			case R.id.button2:
				Fragment2 fragment2 = new Fragment2();
				fragmentTransaction.replace(R.id.center, fragment2, "fragment2");
				fragmentTransaction.addToBackStack("fragment2");
				break;
			case R.id.button3:
				Fragment3 fragment3 = new Fragment3();
				fragmentTransaction.replace(R.id.center, fragment3, "fragment3");
				fragmentTransaction.addToBackStack("fragment3");
				break;
			case R.id.button4:
				Fragment4 fragment4 = new Fragment4();
				fragmentTransaction.replace(R.id.center, fragment4, "fragment4");
				fragmentTransaction.addToBackStack("fragment4");	
				break;
	
			default:
				break;
			}
		fragmentTransaction.commit();
	}

}
