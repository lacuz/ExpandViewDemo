package com.zj.expandviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zj.expandviewdemo.CommonUtil;
import com.zj.expandviewdemo.R;

/**
 * Created by Administrator on 2016/11/18.
 */
public class ExTextView extends TextView{
    public ExTextView(Context context) {
        super(context);
        setAtribute();
    }

    public ExTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setAtribute(){
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.dip2px(getContext(),45)));
        setTextColor(getResources().getColor(R.color.black));
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(CommonUtil.dip2px(getContext(),10),0,0,0);
        setTextSize((float) 17);
        setBackgroundResource(R.drawable.expand_eara_item_selector);
    }
}
