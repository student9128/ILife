package com.kevin.live.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project ILife on 2017/8/25.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class TabLayoutStudyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> tabList;

    public TabLayoutStudyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabLayoutStudyFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> tabList) {
        super(fm);
        this.fragments = fragments;
        this.tabList = tabList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList == null ? "default" : tabList.get(position);
    }
}
