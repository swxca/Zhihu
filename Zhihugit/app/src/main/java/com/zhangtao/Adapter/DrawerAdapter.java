package com.zhangtao.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangtao.Utils.ListType;
import com.zhangtao.zhihuclient.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by zhangtao on 15/11/29.
 */
public class DrawerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater ;
    MyViewHolder myViewHolder;
    private final int mIconSize;


    //在所有方法的外部初始化一个List
    private List<ListType> Datas=new ArrayList<>(Arrays.asList(
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

    public DrawerAdapter(Context context){
        this.mContext=context;
        mInflater= LayoutInflater.from(context);
        //自己定义的item图片大小
        //还是忘记了px,dp,int之间的转换
        mIconSize=context.getResources().getDimensionPixelSize(R.dimen.drawer_icon_size);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType)
        {
            case ListType.TYPE_HEADER:
                View viewHeader=mInflater.inflate(R.layout.drawer_header,parent,false);
                return setTagViewholder(viewHeader,viewType);
            case ListType.TYPE_SEPARATOR:
                View Separator=mInflater.inflate(R.layout.separator,parent,false);

                return setTagViewholder(Separator,viewType);
            case ListType.TYPE_ICON:
                //这个图标的摆放有两种思路,
                // 一种是按照布局文件来摆放,那怎么布局,间隔多少,适配问题就都来了
                //第二种就是下面的
                //还是用第二种吧...虽然我搞不清其中的原理
                //步骤就是先建一个只有textview的布局文件,然后利用TextViewCompat类放
                // !!!!!!!!!!!!!!!!!!!!布局文件不懂!!!!!!!!!!!!!!!!!!!!!!!!
                View IconItem= (TextView) mInflater.inflate(R.layout.item_icon, parent, false);
                return setTagViewholder(IconItem,viewType);
            case ListType.TYPE_NO_ICON:
                View noIconItem=mInflater.inflate(R.layout.item_noicon,parent,false);
                return setTagViewholder(noIconItem,viewType);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            ListType listType=Datas.get(position);
            switch (getItemViewType(position))
            {
                case ListType.TYPE_HEADER:
                    Log.d("headerIcon",listType.getIcon()+holder.CircleimageView.getId()+"");
                    holder.CircleimageView.setImageResource(listType.getIcon());
                    holder.username.setText(listType.header);
                    holder.description.setText(listType.getName());
                    break;
                case ListType.TYPE_ICON:
                    Log.d("Icon",listType.getIcon()+"");
                    holder.iconItem.setText(listType.getName());
                    setIcon(listType.getIcon(), holder.iconItem);
                    break;
                case ListType.TYPE_NO_ICON:
                    Log.d("noIcon",listType.getIcon()+"");
                    holder.NoIconItem.setText(listType.getName());
                    break;
            }
    }

    @Override
    public int getItemCount() {
        return Datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return Datas.get(position).getType();
    }

    public MyViewHolder setTagViewholder(View view,int viewType){
        myViewHolder=new MyViewHolder(view,viewType);
        return myViewHolder;
    }

    public void setIcon(int iconResource,TextView itemName){

        Drawable icon=mContext.getResources().getDrawable(iconResource);
        //setIconColor(icon);
        if (icon!=null)
        {
            icon.setBounds(0, 0, mIconSize, mIconSize);
            TextViewCompat.setCompoundDrawablesRelative(itemName, icon, null, null, null);
        }
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    View separator;
    ImageView CircleimageView;

    TextView username,description,iconItem,NoIconItem;
    public MyViewHolder(View itemView,int itemType) {
        super(itemView);

        switch (itemType)
        {
            case ListType.TYPE_SEPARATOR:
                separator=itemView.findViewById(R.id.separator);
                break;
            case ListType.TYPE_HEADER:
                CircleimageView = (ImageView) itemView.findViewById(R.id.circle_avater);
                username= (TextView) itemView.findViewById(R.id.username);
                description= (TextView) itemView.findViewById(R.id.description);
                break;
            case ListType.TYPE_ICON:
                iconItem= (TextView) itemView.findViewById(R.id.icon_item);
                break;
            case ListType.TYPE_NO_ICON:
                NoIconItem= (TextView) itemView.findViewById(R.id.no_icon_item);
                break;
        }
    }
}
