package com.zhangtao.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhangtao on 15/12/1.
 */
public class MessageAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public MessageAdapter(FragmentManager fm,List<Fragment> fragmentsList) {
        super(fm);
        this.list=fragmentsList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
