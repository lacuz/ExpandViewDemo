package com.zj.expandviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandTabView tabView = (ExpandTabView)findViewById(R.id.expandTabView);
        ArrayList<View> mViewArray = new ArrayList<View>();
        ArrayList<String> mTextArray = new ArrayList<String>();
        mViewArray.add(new ViewArea(this));
        mViewArray.add(new ViewType(this));
        mViewArray.add(new ViewType(this));
        mTextArray.add("区域");
        mTextArray.add("类型");
        mTextArray.add("价格");
        tabView.setValue(mTextArray, mViewArray);
        tabView.setTitle("区域", 0);
        tabView.setTitle("类型", 1);
        tabView.setTitle("价格", 2);
    }
}
