package com.zhangtao.zhihuclient;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhangtao.Adapter.MessageAdapter;
import com.zhangtao.fragment.AgreeFragment;
import com.zhangtao.fragment.PersonalMessageFragment;
import com.zhangtao.fragment.RemindFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangtao on 15/11/30.
 */
public class MessageActivity extends AppCompatActivity {


    private ViewPager mViewPager;
    private List<Fragment> viewPagerFragmentList;
    private MessageAdapter mMessageAdapter;
    private ImageView RemindImageView,AgreeImageView,MessageImageView,Indicator;
    private List<ImageView> IndicatorsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        initData();

        initViews();


    }

    private void initData() {
        IndicatorsList=new ArrayList<>();
        viewPagerFragmentList=new ArrayList<>();
        AgreeFragment agreeFragment=new AgreeFragment();
        viewPagerFragmentList.add(agreeFragment);
        RemindFragment remindFragment=new RemindFragment();
        viewPagerFragmentList.add(remindFragment);
        PersonalMessageFragment personalMessageFragment=new PersonalMessageFragment();
        viewPagerFragmentList.add(personalMessageFragment);
        mMessageAdapter=new MessageAdapter(getSupportFragmentManager(),viewPagerFragmentList);
    }

    private void initViews() {

        RemindImageView= (ImageView) findViewById(R.id.message_remind);
        AgreeImageView= (ImageView) findViewById(R.id.message_agree);
        MessageImageView= (ImageView) findViewById(R.id.message_personal);
        RemindImageView.setAlpha(0.3f);
        AgreeImageView.setAlpha(0.3f);
        MessageImageView.setAlpha(0.3f);
        Indicator= (ImageView) findViewById(R.id.message_indicator);
        IndicatorsList.add(RemindImageView);
        IndicatorsList.add(AgreeImageView);
        IndicatorsList.add(MessageImageView);



        mViewPager= (ViewPager) findViewById(R.id.message_viewpager);
        mViewPager.setAdapter(mMessageAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int marginleft = getResources().getDimensionPixelSize(R.dimen.indicator_marginleft);
            int indicatorWidth = getResources().getDimensionPixelSize(R.dimen.indicator_width);

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) Indicator.getLayoutParams();

                if (positionOffset > 0) {
                    ImageView left = IndicatorsList.get(position);
                    ImageView right = IndicatorsList.get(position + 1);

                    left.setAlpha(1.3f-positionOffset);
                    right.setAlpha (positionOffset*0.7f+0.3f);
                    if (position==1)
                    {
                        lp.leftMargin= (int) (indicatorWidth+indicatorWidth*positionOffset);
                    }else {
                        lp.leftMargin = (int) (indicatorWidth * positionOffset);
                    }
                    Indicator.setLayoutParams(lp);
                }
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    public static void startActivity(Context context,String string){
        Intent intent=new Intent(context,MessageActivity.class);
        intent.putExtra("Data",string);
        context.startActivity(intent);
    }
}
