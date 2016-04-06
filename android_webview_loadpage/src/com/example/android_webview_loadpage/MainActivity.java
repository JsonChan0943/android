package com.example.android_webview_loadpage;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	private WebView webView;
	private static final String URL = "http://www.baidu.com";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		webView = (WebView)this.findViewById(R.id.webView1);
		WebSettings webSettings = webView.getSettings();
		webSettings.setBuiltInZoomControls(true);//可以在网页设置放大或者缩小
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

}
