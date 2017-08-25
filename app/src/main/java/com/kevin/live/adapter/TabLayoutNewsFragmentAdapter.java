package com.kevin.live.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.live.R;

import java.util.List;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2017/8/24 for Project ILife.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */

public class TabLayoutNewsFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragments;
    private List<String> mTabList;
    private int[] mTabImgs, mTabImgsFill;

    private ImageView mTabIcon, mTabIconFill;
    private TextView mTabTitle;

    public TabLayoutNewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabLayoutNewsFragmentAdapter(FragmentManager fm, Context context, List<Fragment> fragments,
                                        List<String> tabList) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
        this.mTabList = tabList;

    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.get(position);
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_news_fragment_tab_view, null);
        mTabTitle = (TextView) view.findViewById(R.id.tv_tab_text);
        mTabTitle.setText(mTabList.get(position));
        if (0 == position) {//the default color of item home is green
            mTabTitle.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            mTabTitle.setTextColor(ContextCompat.getColor(mContext, R.color.black));
        }
        return view;
    }
}
