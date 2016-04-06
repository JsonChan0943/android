package com.example.android_videoview_play;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity implements MediaPlayerControl{
	private VideoView videoView;
	private MediaController controller;//媒体的播放控制器
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		videoView = (VideoView)this.findViewById(R.id.videoView1);
		controller = new MediaController(this);
		videoView.setMediaController(controller);
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File root = Environment.getExternalStorageDirectory();
			File videoFile = new File(root,"触电.mp4");
			Uri uri = Uri.fromFile(videoFile);
			videoView.setVideoURI(uri);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		videoView.start();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if(videoView.isPlaying()){
			videoView.pause();
		}
	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return videoView.getDuration();
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return videoView.getCurrentPosition();
	}

	@Override
	public void seekTo(int pos) {
		// TODO Auto-generated method stub
		videoView.seekTo(pos);
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return videoView.isPlaying();
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return videoView.canPause();
	}

	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return videoView.canSeekBackward();
	}

	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return videoView.canSeekForward();
	}

	@Override
	public int getAudioSessionId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
