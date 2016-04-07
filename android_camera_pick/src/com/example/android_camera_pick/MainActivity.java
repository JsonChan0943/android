package com.example.android_camera_pick;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Android
 * @author Administrator
 *
 */
public class MainActivity extends Activity {
	private Button openCamera,pickPhoto;
	private ImageView imageView;
	private final int OPEN_CAMERA = 1;
	private final int PICK_PHOTO = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		openCamera = (Button)this.findViewById(R.id.button1);
		pickPhoto = (Button)this.findViewById(R.id.button2);
		imageView = (ImageView)this.findViewById(R.id.imageView1);
		openCamera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, OPEN_CAMERA);
			}
		});
		
		pickPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//��ȡ��Ƭ
				Intent intent = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, PICK_PHOTO);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case OPEN_CAMERA:
			if(resultCode==RESULT_OK){
				Bundle bundle = data.getExtras();
				Bitmap bitmap = (Bitmap)bundle.get("data");
				imageView.setImageBitmap(bitmap);
			}
			break;
		case PICK_PHOTO:
			if(resultCode==RESULT_OK){
				Uri uri = data.getData();
				imageView.setImageURI(uri);
			}
			break;

		default:
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
