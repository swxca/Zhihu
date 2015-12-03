package com.zhangtao.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhangtao.fragment.DetailInformationFragment;
import com.zhangtao.fragment.PersonalPageFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtao on 15/12/2.
 */
public class PersonalViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list;
    List<String> titleList;
    DetailInformationFragment detailInformationFragment;
    PersonalPageFragment personalPageFragment;

    public PersonalViewPagerAdapter(FragmentManager fm,List<String> titleList) {
        super(fm);
        list= new ArrayList<>();
        if (detailInformationFragment ==null)
        {
            detailInformationFragment =new DetailInformationFragment();
        }
        if (personalPageFragment ==null)
        {
            personalPageFragment =new PersonalPageFragment();
        }
        list.add(personalPageFragment);
        list.add(detailInformationFragment);
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
