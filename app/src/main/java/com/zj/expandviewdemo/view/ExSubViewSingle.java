package com.zj.expandviewdemo.view;


import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zj.expandviewdemo.R;
import com.zj.expandviewdemo.action.ExSubViewGravity;
import com.zj.expandviewdemo.action.ViewBaseAction;
import com.zj.expandviewdemo.adapter.ExpandListAdapter;


public class ExSubViewSingle extends RelativeLayout implements ViewBaseAction {

	private ListView mListView;
	private String[] data;
	private OnSelectListener mOnSelectListener;
	private ExpandListAdapter adapter;


	public ExSubViewSingle(Context context, ExSubViewGravity gravity, String[] data) {
		super(context);
		this.data = data;
		init(context,gravity);
	}


	private void init(Context context, ExSubViewGravity gravity) {
		if(ExSubViewGravity.LEFT == gravity){
			setBackgroundResource(R.drawable.expand_bg_left);
		}else if(ExSubViewGravity.MID == gravity){
			setBackgroundResource(R.drawable.expand_bg_mid);
		}else if(ExSubViewGravity.RIGHT == gravity){
			setBackgroundResource(R.drawable.expand_bg_right);
		}
		mListView = new ExListView(getContext());
		addView(mListView);
		adapter = new ExpandListAdapter(context, data, R.drawable.expand_list_item_selected, R.drawable.expand_eara_item_selector);
		adapter.setTextSize(17);
		mListView.setAdapter(adapter);
		adapter.setOnItemClickListener(new ExpandListAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(View view, int position) {
				if (mOnSelectListener != null) {
					mOnSelectListener.getValue(position,data[position]);
				}
			}
		});
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		void getValue(int position, String value);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void show() {
		
	}

}
