package com.example.android_fragment_activity2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 在Fragment中获取Activity控件的值
 * @author Administrator
 *
 */
public class LeftFragment extends Fragment {
	private Button button;
	private EditText editText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.left, null);
		editText = (EditText) view.findViewById(R.id.editText1);
//		button = (Button)view.findViewById(R.id.button1);
//		button.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				EditText editText = (EditText) getActivity().findViewById(R.id.editText1);
//				Toast.makeText(getActivity(), editText.getText(), 1).show();
//			}
//		});
		return view;
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	public void getResult(CallBack callBack){
		callBack.getResult(editText.getText().toString());
	}
	
	public interface CallBack{
		public void getResult(String result);
	}
}
