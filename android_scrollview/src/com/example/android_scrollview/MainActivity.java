package com.example.android_scrollview;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private LinearLayout linearLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		linearLayout = (LinearLayout) this.findViewById(R.id.linearLayout1);
		for(int i=0;i<10;i++){
			ImageView imageView = new ImageView(this);
			Drawable drawable = getResources().getDrawable(R.drawable.a);
			imageView.setImageDrawable(drawable);
			linearLayout.addView(imageView,i);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
