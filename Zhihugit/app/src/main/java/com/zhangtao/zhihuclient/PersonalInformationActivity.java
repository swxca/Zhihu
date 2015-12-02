package com.zhangtao.zhihuclient;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.zhangtao.Adapter.PersonalViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtao on 15/12/2.
 */
public class PersonalInformationActivity extends AppCompatActivity{

    private static final String INFORMATION_DATA="INFORMATION_DATA";

    private Toolbar toolbar;
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private PersonalViewPagerAdapter personalViewPagerAdapter;
    private List<String> TabTitleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalinformation);
        TabTitleList=new ArrayList<>();
        TabTitleList.add("个人主页");
        TabTitleList.add("详细信息");
        personalViewPagerAdapter=new PersonalViewPagerAdapter(getSupportFragmentManager(),TabTitleList);

        initView();

        initToolbar();

        initEvent();

    }

    private void initEvent() {

    }

    private void initToolbar() {
        toolbar= (Toolbar) findViewById(R.id.personal_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        ActionBar ab=getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(false);
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.personal_toolbar);
        mTablayout= (TabLayout) findViewById(R.id.personal_tablayout);
        mViewPager= (ViewPager) findViewById(R.id.personal_viewpager);

        mTablayout.addTab(mTablayout.newTab().setText("个人主页"));
        mTablayout.addTab(mTablayout.newTab().setText("详细信息"));


        mViewPager.setAdapter(personalViewPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);
        //mTablayout.setTabMode();

    }

    public static void startPersonalInformation(Context context,String string){
        Intent intent=new Intent(context,PersonalInformationActivity.class);
        intent.putExtra(INFORMATION_DATA,string);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.personal_menu,menu);

        return true;

    }
}
