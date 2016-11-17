package com.zj.expandviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewArea extends LinearLayout implements ViewBaseAction {

	private ListView provinceListView;
	private ListView citytListView;
	private ListView counyListView;
	private List<Cityinfo> provinceList = new ArrayList<Cityinfo>();
	private List<Cityinfo> cityList = new ArrayList<Cityinfo>();
	private List<Cityinfo> counyList = new ArrayList<Cityinfo>();
	private AreaAdapter citytListViewViewAdapter;
	private AreaAdapter provinceListViewAdapter;
	private AreaAdapter counyListViewViewAdapter;
	private OnSelectListener mOnSelectListener;
	private int provincePosition = -1;
	private int cityPosition = -1;
	private int counyPosition = -1;
	private String resultString = "区域";
	private String resultId = "";

	private AreaInfo area;

	public ViewArea(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		area = new AreaInfo(context);
		provinceList = area.getProvince();

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_region, this, true);
		provinceListView = (ListView) findViewById(R.id.listView1);
		citytListView = (ListView) findViewById(R.id.listView2);
		counyListView = (ListView) findViewById(R.id.listView3);
		setBackgroundDrawable(getResources().getDrawable(
				R.drawable.choosearea_bg_left));

		provinceListViewAdapter = new AreaAdapter(context, provinceList,
				R.drawable.choose_item_selected,
				R.drawable.choose_eara_item_selector);
		provinceListViewAdapter.setTextSize(17);
		provinceListViewAdapter.setSelectedPositionNoNotify(provincePosition);
		provinceListView.setAdapter(provinceListViewAdapter);
		provinceListViewAdapter
				.setOnItemClickListener(new AreaAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(View view, int position) {
						if (position == 0) {
							showResult(0);
							return;
						}
						provincePosition = position;
						cityList = area.getCityByCode(provinceList
								.get(position).getId());
						citytListViewViewAdapter.updateData(cityList);
						counyListViewViewAdapter.updateData(null);
					}
				});

		citytListViewViewAdapter = new AreaAdapter(context, cityList,
				R.drawable.choose_item_selected,
				R.drawable.choose_plate_item_selector);
		citytListViewViewAdapter.setTextSize(15);
		citytListViewViewAdapter.setSelectedPositionNoNotify(cityPosition);
		citytListView.setAdapter(citytListViewViewAdapter);
		citytListViewViewAdapter
				.setOnItemClickListener(new AreaAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(View view, final int position) {
						cityPosition = position;
						if (position == 0) {
							showResult(1);
							return;
						}
						counyList = area.getCounyByCode(cityList.get(position)
								.getId());
						if (counyList == null || counyList.size() == 0) {
							counyListViewViewAdapter.updateData(counyList);
							showResult(2);
							return;
						}
						counyListViewViewAdapter.updateData(counyList);

					}
				});
		counyListViewViewAdapter = new AreaAdapter(context, counyList,
				R.drawable.choose_item_selected,
				R.drawable.choose_plate_item_selector);
		counyListViewViewAdapter.setTextSize(15);
		counyListViewViewAdapter.setSelectedPositionNoNotify(0);
		counyListView.setAdapter(counyListViewViewAdapter);
		counyListViewViewAdapter
				.setOnItemClickListener(new AreaAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(View view, int position) {
						counyPosition = position;
						if (position == 0) {
							showResult(2);
							return;
						}
						showResult(3);
					}
				});

	}

	// type：1省级 2：市級 3：區級
	public void showResult(int type) {
		switch (type) {
		case 0:
			resultString = "";
			resultId = "";// 显示全国幼儿园信息
			break;
		case 1:
			resultString = provinceList.get(provincePosition).getCity_name();
			resultId = "|province="+provinceList.get(provincePosition).getId();
			break;
		case 2:
			resultString = cityList.get(cityPosition).getCity_name();
			resultId = "|city="+cityList.get(cityPosition).getId();
			break;
		case 3:
			resultString = counyList.get(counyPosition).getCity_name();
			resultId = "|area="+counyList.get(counyPosition).getId();
			break;
		default:
			break;
		}

		if (mOnSelectListener != null) {
			mOnSelectListener.getValue(resultString);
			mOnSelectListener.getId(resultId);
		}
	}

	public String getShowText() {
		return resultString;
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		public void getValue(String showText);
		public void getId(String id);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
}
