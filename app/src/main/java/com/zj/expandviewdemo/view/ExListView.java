package com.zj.expandviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zj.expandviewdemo.CommonUtil;
import com.zj.expandviewdemo.R;

/**
 * Created by Administrator on 2016/11/18.
 */
public class ExListView extends ListView {
    public ExListView(Context context) {
        super(context);
        setAtribute();
    }

    public ExListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setAtribute(){
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1));
        setPadding(CommonUtil.dip2px(getContext(),4),0,0,0);
        setBackgroundResource(R.color.white);
        setDividerHeight(1);
        setDivider(getResources().getDrawable(R.drawable.shape_line_vertical));
        setVerticalScrollBarEnabled(false);
    }
}
