package com.zhangtao.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhangtao.zhihuclient.R;

/**
 * Created by zhangtao on 15/11/30.
 */
public class Searchfragment extends Fragment {
    TextView SearchText,SearchSomeone;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_search,container,false);
        SearchText= (TextView) view.findViewById(R.id.search_content);

        SearchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Click content",Toast.LENGTH_SHORT).show();
            }
        });
        SearchSomeone= (TextView) view.findViewById(R.id.search_someone);
        SearchSomeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Click someone",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }



}
