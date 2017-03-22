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
 * Created by Kevin on 2017/3/17.
 * Blog:http://blog.csdn.net/student9128.
 * Description:
 */

public class TabLayoutFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragments;
    private List<String> mTabList;
    private int[] mTabImgs;

    private ImageView mTabIcon;
    private TextView mTabTitle;

    public TabLayoutFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabLayoutFragmentAdapter(FragmentManager fm, Context context, List<Fragment> fragments, List<String> tabList, int[] tabImgs) {
        super(fm);
        this.mContext = context;
        this.mFragments = fragments;
        this.mTabList = tabList;
        this.mTabImgs = tabImgs;
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

    public View getTabView(int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_tab_view, null);
        mTabIcon = (ImageView) view.findViewById(R.id.iv_tab_icon);
        mTabTitle = (TextView) view.findViewById(R.id.tv_tab_text);
        mTabIcon.setImageResource(mTabImgs[position]);
        mTabTitle.setText(mTabList.get(position));
        if (0 == position) {//the default color of item home is green
            mTabTitle.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            mTabIcon.setImageResource(R.drawable.ic_home_fill);
        }
        return view;
    }
}
