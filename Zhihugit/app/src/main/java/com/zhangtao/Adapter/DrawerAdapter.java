package com.zhangtao.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangtao.Utils.ListType;
import com.zhangtao.circleavatar.CircleImageView;
import com.zhangtao.zhihuclient.MainActivity;
import com.zhangtao.zhihuclient.PersonalInformationActivity;
import com.zhangtao.zhihuclient.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.widget.LinearLayout.*;

/**
 * Created by zhangtao on 15/11/29.
 */
public class DrawerAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    MyViewHolder myViewHolder;
    //Item图片的大小
    private final int mIconSize;
    //除了header之外所有Item的集合
    public static List<View> IconItemList;
    //第一个Item的布局,用来设置leftmargin
    private android.support.v7.widget.RecyclerView.LayoutParams layoutParams;

    //监听接口
    public interface OnItemClickListener {

        void OnItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemOnClickListener(OnItemClickListener onItemOnClickListener) {
        this.onItemClickListener = onItemOnClickListener;
    }

    //在所有方法的外部初始化一个List
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

    public CircleImageView mCircleImageView;
    public TextView username, des;


    public DrawerAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        //自己定义的item图片大小
        //还是忘记了px,dp,int之间的转换
        mIconSize = context.getResources().getDimensionPixelSize(R.dimen.drawer_icon_size);
        IconItemList = new ArrayList<>();


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case ListType.TYPE_HEADER:
                View viewHeader = mInflater.inflate(R.layout.drawer_header, parent, false);
                return setTagViewholder(viewHeader, viewType);
            case ListType.TYPE_SEPARATOR:
                View Separator = mInflater.inflate(R.layout.separator, parent, false);

                return setTagViewholder(Separator, viewType);
            case ListType.TYPE_ICON:
                //这个图标的摆放有两种思路,
                // 一种是按照布局文件来摆放,那怎么布局,间隔多少,适配问题就都来了
                //第二种就是下面的
                //还是用第二种吧...虽然我搞不清其中的原理
                //步骤就是先建一个只有textview的布局文件,然后利用TextViewCompat类放
                // !!!!!!!!!!!!!!!!!!!!布局文件不懂!!!!!!!!!!!!!!!!!!!!!!!!
                View IconItem = (TextView) mInflater.inflate(R.layout.item_icon, parent, false);
                return setTagViewholder(IconItem, viewType);
            case ListType.TYPE_NO_ICON:
                View noIconItem = mInflater.inflate(R.layout.item_noicon, parent, false);
                return setTagViewholder(noIconItem, viewType);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListType listType = Datas.get(position);
        switch (getItemViewType(position)) {
            case ListType.TYPE_HEADER:
                holder.CircleimageView.setImageResource(listType.getIcon());
                holder.username.setText(listType.header);
                holder.description.setText(listType.getName());
                setHeader((CircleImageView) holder.CircleimageView);
                setUsername(holder.username);
                setDes(holder.description);
                initEvents();
                break;
            case ListType.TYPE_ICON:
                holder.iconItem.setText(listType.getName());
                //holder.iconItem.setBackgroundResource(R.color.itemview_background);
                setIcon(listType.getIcon(), holder.iconItem);
                IconItemList.add(holder.iconItem);
                if (position == 1) {
                    layoutParams = (RecyclerView.LayoutParams) holder.iconItem.getLayoutParams();
                    layoutParams.topMargin = 21;
                    holder.iconItem.setLayoutParams(layoutParams);
                }
                break;
            case ListType.TYPE_NO_ICON:
                holder.NoIconItem.setText(listType.getName());
                IconItemList.add(holder.NoIconItem);
                break;
        }

        setUpItemEvent(holder);
    }

    public void initEvents() {
        mCircleImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("ImagView", "Ssdsdadad");
                MainActivity.closeDrawer();
                PersonalInformationActivity.startPersonalInformation(mContext, "");

            }
        });
        username.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.closeDrawer();
                PersonalInformationActivity.startPersonalInformation(mContext, "");
            }
        });
        des.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.closeDrawer();
                PersonalInformationActivity.startPersonalInformation(mContext, "");
            }
        });
    }


    private void setUpItemEvent(final MyViewHolder holder) {
        //Log.d("是否进入setUpItemEvent","");

        if (onItemClickListener != null) {
            final int layoutPosition = holder.getLayoutPosition();
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(holder.description, layoutPosition);

                }
            });
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

    //将创建好的view和MyViewHolder绑定
    public MyViewHolder setTagViewholder(View view, int viewType) {
        myViewHolder = new MyViewHolder(view, viewType);
        return myViewHolder;
    }

    //为Item设置图片
    public void setIcon(int iconResource, TextView itemName) {

        Drawable icon = mContext.getResources().getDrawable(iconResource);
        //setIconColor(icon);
        if (icon != null) {
            icon.setBounds(0, 0, mIconSize, mIconSize);
            TextViewCompat.setCompoundDrawablesRelative(itemName, icon, null, null, null);
        }
    }

    //清楚所有的Item的background颜色
    public void ClearAllCheckedItem() {

        for (int i = 0; i < IconItemList.size(); i++) {
            IconItemList.get(i).setBackgroundResource(R.color.white);
        }
    }

    //设置某个Item的背景为checked样式
    public void setCheckedItem(int position) {
        switch (position) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                IconItemList.get(position - 1).setBackgroundResource(R.color.itemview_background);
                break;
            case 8:
            case 9:
                IconItemList.get(position - 2).setBackgroundResource(R.color.itemview_background);
                break;
        }
    }

    //得到CircleImageView的实例
    public void setHeader(CircleImageView circleImageView) {
        this.mCircleImageView = circleImageView;
    }

    public void setUsername(TextView username) {
        this.username = username;
    }

    public void setDes(TextView des) {
        this.des = des;
    }


}

class MyViewHolder extends RecyclerView.ViewHolder {
    View separator;
    ImageView CircleimageView;

    TextView username, description, iconItem, NoIconItem;

    public MyViewHolder(View itemView, int itemType) {
        super(itemView);

        switch (itemType) {
            case ListType.TYPE_SEPARATOR:
                separator = itemView.findViewById(R.id.separator);
                break;
            case ListType.TYPE_HEADER:
                CircleimageView = (ImageView) itemView.findViewById(R.id.circle_avater);
                username = (TextView) itemView.findViewById(R.id.username);
                description = (TextView) itemView.findViewById(R.id.description);
                break;
            case ListType.TYPE_ICON:
                iconItem = (TextView) itemView.findViewById(R.id.icon_item);
                break;
            case ListType.TYPE_NO_ICON:
                NoIconItem = (TextView) itemView.findViewById(R.id.no_icon_item);
                break;
        }
    }


}
