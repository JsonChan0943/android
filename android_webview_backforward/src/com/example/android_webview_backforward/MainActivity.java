package com.example.android_webview_backforward;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button backButton,forwardButton,zoomInButton,zoomOuButton;
	private WebView webView;
	private final String URL = "http://www.baidu.com";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		backButton = (Button)this.findViewById(R.id.button1);
		forwardButton = (Button)this.findViewById(R.id.button2);
		zoomInButton = (Button)this.findViewById(R.id.button3);
		zoomOuButton = (Button)this.findViewById(R.id.button4);
		webView = (WebView)this.findViewById(R.id.webView1);
		backButton.setOnClickListener(this);
		forwardButton.setOnClickListener(this);
		zoomInButton.setOnClickListener(this);
		zoomOuButton.setOnClickListener(this);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(URL);
		webView.setWebViewClient(new WebViewClient(){
	         @Override
	         public boolean shouldOverrideUrlLoading(WebView view, String url) {
	     		webView.loadUrl(url);   //在当前的webview中跳转到新的url
	     		return true;
	         }
        });
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
		switch (v.getId()) {
		case R.id.button1:
				if(webView.canGoBack()){
					webView.goBack();
				}
			break;
		case R.id.button2:
			if(webView.canGoForward()){
				webView.goForward();
			}	
			break;
		case R.id.button3:
			if(webView.canZoomIn()){
				webView.zoomIn();
			}
			break;
		case R.id.button4:
			if(webView.canZoomOut()){
				webView.zoomOut();
			}
			break;
		default:
			break;
		}
	}

}
