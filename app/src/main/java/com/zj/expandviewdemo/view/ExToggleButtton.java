package com.zj.expandviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.zj.expandviewdemo.R;

/**
 * Created by Administrator on 2016/11/18.
 */
public class ExToggleButtton extends ToggleButton{
    public ExToggleButtton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ExToggleButtton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExToggleButtton(Context context) {
        super(context);
        setAtribute();
    }

    private void setAtribute(){
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,1));
        setSingleLine(true);
        setGravity(Gravity.CENTER);
        setBackgroundResource(R.drawable.expand_tab_selector);
        setTextColor(getResources().getColor(R.color.black));
        setTextOff(null);
        setTextOn(null);

    }
}
