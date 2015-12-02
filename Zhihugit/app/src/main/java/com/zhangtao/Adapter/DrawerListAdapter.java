package com.zhangtao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zhangtao.Utils.ListType;
import com.zhangtao.zhihuclient.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhangtao on 15/12/2.
 */
public class DrawerListAdapter extends BaseAdapter {

    private final int mIconSize;
    private LayoutInflater mInflater;
    private Context mContext;

    public DrawerListAdapter(Context context){
        mIconSize = context.getResources().getDimensionPixelSize(R.dimen.drawer_icon_size);
        this.mContext=context;
        mInflater=LayoutInflater.from(context);

    }

    private List<ListType> Datas = new ArrayList<>(Arrays.asList(
            new ListType("张同学", "一句话介绍自己", R.drawable.avater),
            new ListType("首页", R.drawable.main),
            new ListType("发现", R.drawable.discover),
            new ListType("关注", R.drawable.attention_someone),
            new ListType("收藏", R.drawable.collect),
            new ListType("草稿", R.drawable.draft),
            new ListType("提问", R.drawable.question),
            new ListType(),
            new ListType("切换主题"),
            new ListType("设置")
    ));
    @Override
    public int getCount() {
        return Datas.size();
    }

    @Override
    public Object getItem(int position) {
        return Datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListType item=Datas.get(position);
        switch (item.type)
        {

        }
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }
    @Override
    public int getItemViewType(int position)
    {
        return Datas.get(position).type;
    }
}
