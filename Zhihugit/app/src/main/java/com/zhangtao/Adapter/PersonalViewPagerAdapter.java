package com.zhangtao.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhangtao.fragment.detail_information;
import com.zhangtao.fragment.personal_page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtao on 15/12/2.
 */
public class PersonalViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list;
    List<String> titleList;

    public PersonalViewPagerAdapter(FragmentManager fm,List<String> titleList) {
        super(fm);
        list= new ArrayList<>();
        list.add(new detail_information());
        list.add(new personal_page());
        this.titleList=titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
