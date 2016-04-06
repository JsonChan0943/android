package com.example.android_listview_dividepage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listview1.httpUtil.HttpUtils;
import com.example.listview1.json.JsonTools;

public class MainActivity extends Activity {
	private final String EMP_PATH = "http://192.168.1.101:8080/SSHE/app/listByPage.html?pageNo=";
	private ListView listView;
	private MyAdapter myAdapter  = new MyAdapter();
	private boolean is_dividepage;
	List<String> total = new ArrayList<String>();//放置记录的总条数
	private ProgressDialog dialog;
	private static int pageNo = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		listView = (ListView)this.findViewById(R.id.listView1);
//		myAdapter = new MyAdapter();
//		myAdapter.bindData(getDataSource());
//		listView.setAdapter(myAdapter);
		dialog = new ProgressDialog(MainActivity.this);
		dialog.setTitle("提示");
		dialog.setMessage("下载中....");
		new MyTask().execute(EMP_PATH+pageNo);
		listView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				//到最后一条了，而且listview不在滚动 就加载下一页
				if(is_dividepage&&scrollState==OnScrollListener.SCROLL_STATE_IDLE){
					//OnScrollListener.SCROLL_STATE_IDLE表示listview不在挪动
					new MyTask().execute(EMP_PATH+pageNo);
				}
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				is_dividepage =(firstVisibleItem+visibleItemCount==totalItemCount);
			}
		});
	}
	
	
	class MyTask extends AsyncTask<String, Void, List<String>>{
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.show();
		}
		@Override
		protected List<String> doInBackground(String... params) {
			String jsonString = HttpUtils.sendPostMethod(params[0], "utf-8");
			List<String> list = JsonTools.parseJsonList(jsonString);
			return list;
		}
		@Override
		protected void onPostExecute(List<String> result) {
			super.onPostExecute(result);
			total.addAll(result);
			myAdapter.bindData(total);
			if(pageNo==1){
				listView.setAdapter(myAdapter);
			}
			myAdapter.notifyDataSetChanged();
			pageNo++;
			dialog.dismiss();
		}
	}
	
	
	class MyAdapter extends BaseAdapter{
		private List<String> list;
		public MyAdapter(){
			
		}
		public void bindData(List<String> list){
			this.list = list;
		}
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView = null;
			if(convertView==null){
				textView = new TextView(MainActivity.this);
			}else{
				textView = (TextView)convertView;
			}
			textView.setTextSize(20);
			textView.setText(list.get(position));
			return textView;
		}
		
	}
	
	public List<String> getDataSource(){
		List<String> list = new ArrayList<String>();
		for(int i=1;i<=30;i++){
			list.add("chenhuaijie"+i);
		}
		return list;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
