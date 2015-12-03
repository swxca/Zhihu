package com.zhangtao.zhihuclient;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangtao.Adapter.DrawerAdapter;
import com.zhangtao.circleavatar.CircleImageView;
import com.zhangtao.divider.SampleDivider;
import com.zhangtao.fragment.Searchfragment;

import java.util.zip.Inflater;

/**
 * Created by zhangtao on 15/11/29.
 */
public class MainActivity extends AppCompatActivity {

    private static DrawerLayout mDrawerLayout;
    private RecyclerView mDrawer, mContent;
    private Toolbar MainToolbar;
    private DrawerAdapter mDrawerAdapter;
    //header的布局
    private RelativeLayout mRelativeLayout;
    Intent intent;
    //记录选中item的位置
    int CheckedPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        initViews();

        initToolbar();

        initDatas();

        initEvent();
    }




    private void initEvent() {
        //给每个Item设置监听
        mDrawerAdapter.setOnItemOnClickListener(new DrawerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Log.d("des", position + "");
                switch (position) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                        mDrawerReSet(position);
                        break;
                }
            }
        });


        //DraweLayout的抽屉设置监听
        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset > 0) {
                    mDrawerAdapter.setCheckedItem(CheckedPosition);

                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }

    public void mDrawerReSet(int position) {
        mDrawerAdapter.ClearAllCheckedItem();
        //mDrawerAdapter.setCheckedItem(position);
        CheckedPosition = position;
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }


    private void initViews() {
        mDrawer = (RecyclerView) findViewById(R.id.drawer);
        mContent = (RecyclerView) findViewById(R.id.maincontent);
        MainToolbar = (Toolbar) findViewById(R.id.maintoolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);




    }

    private void initToolbar() {
        //设置Activity的Lable字体的颜色
        MainToolbar.setTitleTextColor(Color.WHITE);
        //设置toolBar
        setSupportActionBar(MainToolbar);
        //切记是getSupportActionBar!!!!!!
        ActionBar ab = getSupportActionBar();

        //设置三条杠那个图标
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        //接下里是重头戏,实现drawer的header
        //设置列表的布局的布局管理器
    }

    private void initDatas() {
        LinearLayoutManager drawerManager = new LinearLayoutManager(this);
        mDrawer.setLayoutManager(drawerManager);
        //设置分割线
        RecyclerView.ItemDecoration divider = new SampleDivider(this);
        //mDrawer.addItemDecoration(divider);
        //设置适配器
        mDrawerAdapter = new DrawerAdapter(this);
        mDrawer.setAdapter(mDrawerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载menu文件里的menu布局
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //得到menu文件里search实例
        MenuItem searchItem = menu.findItem(R.id.search);
        //以前见到过TextView的Compat,是为了将一个icon和textview设置到一起
        //这些compat就是差不多是一个辅助类
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.search:
                SearchActivity.startActivity(MainActivity.this, "");
                return true;
            case R.id.message:
                MessageActivity.startActivity(MainActivity.this,"");

        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * 为了别的actiivty打开本Activity
     *
     * @param context 要启动活动的Activity
     * @param string  要传递的数据
     */
    public static void startMainActivity(Context context, String string) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("Data", string);
        context.startActivity(intent);
    }

    public static void closeDrawer(){
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }


}
