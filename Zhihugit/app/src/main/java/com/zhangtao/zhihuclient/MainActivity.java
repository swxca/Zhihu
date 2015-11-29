package com.zhangtao.zhihuclient;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.zhangtao.Adapter.DrawerAdapter;
import com.zhangtao.divider.SampleDivider;

import java.util.zip.Inflater;

/**
 * Created by zhangtao on 15/11/29.
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private RecyclerView mDrawer,mContent;
    private Toolbar MainToolbar;
    private DrawerAdapter mDrawerAdapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();

        initViews();
    }

    private void initViews() {
        mDrawer= (RecyclerView) findViewById(R.id.drawer);
        mContent= (RecyclerView) findViewById(R.id.maincontent);
        MainToolbar= (Toolbar) findViewById(R.id.maintoolbar);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        MainToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(MainToolbar);
        //切记是getSupportActionBar!!!!!!
        ActionBar ab=getSupportActionBar();

        //设置三条杠那个图标
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        //接下里是重头戏,实现drawer的header
        //设置列表的布局的布局管理器
        LinearLayoutManager drawerManager=new LinearLayoutManager(this);
        mDrawer.setLayoutManager(drawerManager);
        //设置分割线
        RecyclerView.ItemDecoration divider=new SampleDivider(this);
        //mDrawer.addItemDecoration(divider);
        //设置适配器
        mDrawerAdapter=new DrawerAdapter(this);
        mDrawer.setAdapter(mDrawerAdapter);


    }

    private void initDatas() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载menu文件里的menu布局
        getMenuInflater().inflate(R.menu.main_menu,menu);
        //得到menu文件里search实例
        MenuItem searchItem=menu.findItem(R.id.search);
        //以前见到过TextView的Compat,是为了将一个icon和textview设置到一起
        //这些compat就是差不多是一个辅助类
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(searchItem);
        //展开的监听
        MenuItemCompat.OnActionExpandListener expandListener=new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                //TODO
                //返回true表示展开了这个action view
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                //TODO
                //返回true表示合上此action View
                return true;
            }
        };

        MenuItemCompat.setOnActionExpandListener(searchItem,expandListener);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public static void startMainActivity(Context context,String string){
        Intent intent=new Intent(context,MainActivity.class);
        intent.putExtra("Data",string);
        context.startActivity(intent);
    }
}
