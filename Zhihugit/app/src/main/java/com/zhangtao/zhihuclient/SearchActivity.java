package com.zhangtao.zhihuclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zhangtao on 15/11/30.
 */
public class SearchActivity extends AppCompatActivity{

    private TextView SearchText,SearchSomeone;
    private ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initDatas();

        initViews();

    }

    private void initDatas() {

    }

    private void initViews() {
        SearchText= (TextView) findViewById(R.id.search_content);
        SearchSomeone= (TextView) findViewById(R.id.search_someone);
        setTextBackgroundBule(SearchText);
        setTextBackgroundWhite(SearchSomeone);
        SearchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SearchText.setBackground(null);
                setTextBackgroundBule(SearchText);
                setTextBackgroundWhite(SearchSomeone);
                Toast.makeText(getApplicationContext(), "Click content", Toast.LENGTH_SHORT).show();
            }
        });

        SearchSomeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTextBackgroundBule(SearchSomeone);
                setTextBackgroundWhite(SearchText);
                Toast.makeText(getApplicationContext(),"Click someone",Toast.LENGTH_SHORT).show();
            }
        });
        back= (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setTextBackgroundBule(TextView textView){
        textView.setBackgroundResource(R.drawable.bule);
    }

    public void setTextBackgroundWhite(TextView textView){
        textView.setBackgroundResource(R.drawable.white);
    }

    /**
     * 首先解决的一个问题是Activity进入和退出时候的滑动效果,并不是一直要从底部滑出
     * 解决一:Android动画,用overridePendingTranslation();
     * 解二:不用代码都是在XML中进行.
     * 这里会全部演示,方便自己和别人学习
     */

    public static void startActivity(Context context,String string){
        Intent intent=new Intent(context,SearchActivity.class);
        intent.putExtra("Data", string);
        context.startActivity(intent);
    }


}
