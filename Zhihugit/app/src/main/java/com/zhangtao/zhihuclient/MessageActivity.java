package com.zhangtao.zhihuclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhangtao on 15/11/30.
 */
public class MessageActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        initViews();
    }

    private void initViews() {
        mTabLayout= (TabLayout) findViewById(R.id.message_tab);
        mTabLayout.addTab(mTabLayout.newTab().setText(""));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.ic_menu));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.agree));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.message));
        mTabLayout.addTab(mTabLayout.newTab().setText(""));
        mTabLayout.addTab(mTabLayout.newTab().setText(""));
        mTabLayout.addTab(mTabLayout.newTab().setText(""));
        mTabLayout.addTab(mTabLayout.newTab().setText(""));


        mViewPager= (ViewPager) findViewById(R.id.message_viewpager);
        //mTabLayout.setupWithViewPager(mViewPager);
    }

    public static void startActivity(Context context,String string){
        Intent intent=new Intent(context,MessageActivity.class);
        intent.putExtra("Data",string);
        context.startActivity(intent);
    }
}
